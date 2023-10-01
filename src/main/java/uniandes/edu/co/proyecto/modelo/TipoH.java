package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiposH")
public class TipoH {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String nombreTipo;
    private String dotacion;
    private Integer capacidad;
    private double costoNoche;

    public TipoH(String dotacion, Integer capacidad, double costoNoche){

        this.dotacion = dotacion;
        this.capacidad = capacidad;
        this.costoNoche = costoNoche;

    }

    public TipoH(){;}

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDotacion() {
        return dotacion;
    }

    public void setDotacion(String dotacion) {
        this.dotacion = dotacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public double getCostoNoche() {
        return costoNoche;
    }

    public void setCostoNoche(double costoNoche) {
        this.costoNoche = costoNoche;
    }

    
    
}
