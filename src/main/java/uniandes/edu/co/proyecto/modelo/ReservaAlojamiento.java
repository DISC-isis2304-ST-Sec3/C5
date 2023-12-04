package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "reservasAlojamientos")
public class ReservaAlojamiento {

    @Id
    private String id;
    @Field("fechaEntrada")
    private LocalDate fechaEntrada;
    @Field("fechaSalida")
    private LocalDate fechaSalida;
    @Field("cantidadPersonas")
    private Integer cantidadPersonas;
    @Field("numeroNoches")
    private Integer numeroNoches;
    

    public ReservaAlojamiento(LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantidadPersonas, Integer numeroNoches){

        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.cantidadPersonas=cantidadPersonas;
        this.numeroNoches=numeroNoches;
    }
    public ReservaAlojamiento(){}
    public String getIdReserva() {
        return id;
    }
    public void setIdReserva(String id) {
        this.id = id;
    }
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }
    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    public Integer getNumeroNoches() {
        return numeroNoches;
    }
    public void setNumeroNoches(Integer numeroNoches) {
        this.numeroNoches = numeroNoches;
    }
    
}
