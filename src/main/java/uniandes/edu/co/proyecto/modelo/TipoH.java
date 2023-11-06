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

    private String nombreTH;
    private String dotacion;
    private Integer capacidad;
    private double costoPorNoche;

    public TipoH(String dotacion, Integer capacidad, double costoPorNoche){

        this.dotacion = dotacion;
        this.capacidad = capacidad;
        this.costoPorNoche = costoPorNoche;

    }

    public TipoH(){;}

    public String getNombreTipo() {
        return nombreTH;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTH = nombreTipo;
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

    public double getCostoPorNoche() {
        return costoPorNoche;
    }

    public void setCostoPorNoche(double costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
    }

    
    
}
