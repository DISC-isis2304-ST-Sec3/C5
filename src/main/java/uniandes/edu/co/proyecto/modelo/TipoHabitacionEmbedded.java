package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "TipoHabitacion")
public class TipoHabitacionEmbedded {

    @Id
    private String id;
    @Field("nombreTipo")
    private String nombreTipo;
    @Field("dotacion")
    private String dotacion;
    @Field("capacidadHabitacion")
    private Integer capacidadHabitacion;
    @Field("costoPorNoche")
    private Integer costoPorNoche;

    public TipoHabitacionEmbedded(String nombreTipo, String dotacion, Integer capacidadHabitacion, Integer costoPorNoche){
        this.nombreTipo = nombreTipo;
        this.dotacion = dotacion;
        this.capacidadHabitacion = capacidadHabitacion;
        this.costoPorNoche = costoPorNoche;

    }

    public TipoHabitacionEmbedded(){}

    public String getDotacion() {
        return dotacion;
    }

    public void setDotacion(String dotacion) {
        this.dotacion = dotacion;
    }

    public Integer getCapacidad() {
        return capacidadHabitacion;
    }

    public void setCapacidad(Integer capacidadHabitacion) {
        this.capacidadHabitacion = capacidadHabitacion;
    }

    public Integer getCostoPorNoche() {
        return costoPorNoche;
    }

    public void setCostoPorNoche(Integer costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    
    
}
