package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "IDSERVICIO")

    private Integer idServicio;
    @Column(name = "NOMBRESERVICIO")
    private String nombreServicio;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "HORARIO")
    private String horario;
    @Column(name = "CAPACIDAD")
    private Integer capacidad;
    @Column(name = "COSTO")
    private double costo;



    public Servicio(String nombreServicio, String descripcion, String horario, Integer capacidad, double costo){
        
        this.nombreServicio=nombreServicio;
        this.descripcion=descripcion;
        this.horario=horario;
        this.capacidad=capacidad;
        this.costo=costo;

    }
    
    public Servicio(){;}

    public Integer getIdServicio() {
        return idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
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

    public double getCosto() {
        return costo;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
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

    public void setCosto(double costo) {
        this.costo = costo;
    }

    
    
}
