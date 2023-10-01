package uniandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios")

public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Integer idServicio;
    private String nombre;
    private String descripcion;
    private String horario;
    private Integer capacidad;
    private Integer costo;

    public Integer getIdServicio() {
        return idServicio;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getHorario() {
        return horario;
    }
    public Integer getCapacidad() {
        return capacidad;
    }
    public Integer getCosto() {
        return costo;
    }

    public void setId(Integer idServicio) {
        this.idServicio = idServicio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    public void setCosto(Integer costo) {
        this.costo = costo;
    }

}
