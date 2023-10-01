package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

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

    private Integer idConsumo;
    private String descripcion;
    private Date fecha;
    private double costo;

    @ManyToOne
    @JoinColumn(name = "Servicios_idServicio", referencedColumnName = "idServicio")
    private Servicio Servicios_idServicio;

    @ManyToOne
    @JoinColumn(name = "Reserva_idReserva",referencedColumnName = "idReserva")
    private Reserva Reserva_idReserva;

    public Consumo(String descripcion, Date fecha, double costo, Servicio Servicios_idServicio, Reserva Reserva_idReserva){
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.costo=costo;
        this.Servicios_idServicio=Servicios_idServicio;
        this.Reserva_idReserva=Reserva_idReserva;
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

    public Reserva getReserva_idReserva() {
        return Reserva_idReserva;
    }

    public void setReserva_idReserva(Reserva reserva_idReserva) {
        Reserva_idReserva = reserva_idReserva;
    }

    
    
}
