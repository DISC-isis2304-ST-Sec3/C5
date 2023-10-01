package uniandes.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String nombreTipo;
    private String dotacion;
    private Integer capacidad;
    private Number costoNoche;

    public String getNombreTipo() {
        return nombreTipo;
    }
    public String getDotacion() {
        return dotacion;
    }
    public Integer getCapacidad() {
        return capacidad;
    }
    public Number getCostoNoche() {
        return costoNoche;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
    public void setDotacion(String dotacion) {
        this.dotacion = dotacion;
    }
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    public void setCostoNoche(Integer costoNoche) {
        this.costoNoche = costoNoche;
    }

}
