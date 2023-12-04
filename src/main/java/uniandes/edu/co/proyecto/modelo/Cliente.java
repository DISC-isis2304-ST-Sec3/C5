package uniandes.edu.co.proyecto.modelo;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    @Field("acompañantes")
    private Integer acompañantes;
    @Field("cuenta")
    private Integer cuenta;
    @Field("reservaTerminada")
    private boolean reservaTerminada;
    @Field("habitacion")
    private HabitacionEmbedded habitacion;

    @DBRef
    private List<ReservaAlojamiento> reservaAlojamiento;
    
    
    public Cliente(String id, Integer acompañantes, Integer cuenta, boolean reservaTerminada, HabitacionEmbedded habitacion){
        
        this.id = id;
        this.acompañantes = acompañantes;
        this.cuenta = cuenta;
        this.reservaTerminada = reservaTerminada;
        this.habitacion = habitacion;
    }

    public Cliente(String id, Integer acompañantes, Integer cuenta, boolean reservaTerminada, HabitacionEmbedded habitacion, List<ReservaAlojamiento> reservaAlojamiento){
        
        this.id = id;
        this.acompañantes = acompañantes;
        this.cuenta = cuenta;
        this.reservaTerminada = reservaTerminada;
        this.habitacion = habitacion;
        this.reservaAlojamiento = reservaAlojamiento;
    }

    public Cliente(){}

    public String getidUser() {
        return id;
    }

    public void setIdUser(String id) {
        this.id = id;
    }

    public Integer getAcompañantes() {
        return acompañantes;
    }

    public void setAcompañantes(Integer acompañantes) {
        this.acompañantes = acompañantes;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isReservaTerminada() {
        return reservaTerminada;
    }

    public void setReservaTerminada(boolean reservaTerminada) {
        this.reservaTerminada = reservaTerminada;
    }

    public HabitacionEmbedded getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionEmbedded habitacion) {
        this.habitacion = habitacion;
    }

    public List<ReservaAlojamiento> getReservaAlojamiento() {
        return reservaAlojamiento;
    }

    public void setReservaAlojamiento(List<ReservaAlojamiento> reservaAlojamiento) {
        this.reservaAlojamiento = reservaAlojamiento;
    }

    
    

    
}
