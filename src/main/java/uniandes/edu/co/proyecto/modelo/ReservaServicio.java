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
@Table(name = "reservasServicios")
public class ReservaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idReservaServicio;
    private Date fecha;
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "Servicios_idServicio", referencedColumnName = "idServicio")
    private Servicio Servicios_idServicio;

    @ManyToOne
    @JoinColumn(name = "Reservas_idReserva", referencedColumnName = "idReserva")
    private Reserva Reservas_idReserva;

    @ManyToOne
    @JoinColumn(name = "Empleados_idEmpleado", referencedColumnName = "idEmpleado")
    private Empleado Empleados_idEmpleado;

    public ReservaServicio(Date fecha, Integer duracion, Servicio Servicios_idServicio, Reserva Reservas_idReserva, Empleado Empleados_idEmpleado){
        this.fecha = fecha;
        this.duracion = duracion;
        this.Empleados_idEmpleado = Empleados_idEmpleado;
        this.Reservas_idReserva = Reservas_idReserva;
        this.Servicios_idServicio = Servicios_idServicio;
    }

    public ReservaServicio(){;}

    public Integer getIdReservaServicio() {
        return idReservaServicio;
    }

    public void setIdReservaServicio(Integer idReservaServicio) {
        this.idReservaServicio = idReservaServicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicios_idServicio() {
        return Servicios_idServicio;
    }

    public void setServicios_idServicio(Servicio servicios_idServicio) {
        Servicios_idServicio = servicios_idServicio;
    }

    public Reserva getReservas_idReserva() {
        return Reservas_idReserva;
    }

    public void setReservas_idReserva(Reserva reservas_idReserva) {
        Reservas_idReserva = reservas_idReserva;
    }

    public Empleado getEmpleados_idEmpleado() {
        return Empleados_idEmpleado;
    }

    public void setEmpleados_idEmpleado(Empleado empleados_idEmpleado) {
        Empleados_idEmpleado = empleados_idEmpleado;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    
    
    
}
