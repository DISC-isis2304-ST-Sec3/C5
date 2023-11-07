package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumos")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCONSUMO")


    private Integer idConsumo;
    private String descripcion;
    private Date fecha;
    private double costo;

    @ManyToOne
    @JoinColumn(name = "SERVICIOS_IDSERVICIO", referencedColumnName = "IDSERVICIO")
    private Servicio Servicios_idServicio;

    

    public Consumo(String descripcion, Date fecha, double costo, Servicio Servicios_idServicio){
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.costo=costo;
        this.Servicios_idServicio=Servicios_idServicio;
    }

    public Consumo(){;}

    public Integer getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Servicio getServicios_idServicio() {
        return Servicios_idServicio;
    }

    public void setServicios_idServicio(Servicio servicios_idServicio) {
        Servicios_idServicio = servicios_idServicio;
    }


    
    
}
