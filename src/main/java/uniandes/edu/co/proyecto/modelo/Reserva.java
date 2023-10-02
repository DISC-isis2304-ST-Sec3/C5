package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Integer cantidadPersonas;
    private Date fechaCheckIn;
    private Date fechaCheckOut;

    @OneToOne
    @JoinColumn(name = "Usuario_cedula", referencedColumnName = "cedula")
    private Usuario Usuario_cedula;

    @OneToOne
    @JoinColumn(name = "Planes_nombrePlan", referencedColumnName = "nombrePlan")
    private Plan Planes_nombrePlan;

    @OneToOne
    @JoinColumn(name = "Habitaciones_numero",referencedColumnName = "numero")
    private Habitacion Habitaciones_numero;

    public Reserva(Date fechaEntrada, Date fechaSalida, Integer cantidadPersonas, Usuario Usuario_cedula, Plan Planes_nombrePlan, Habitacion Habitaciones_numero, Date fechaCheckIn, Date fechaCheckOut){

        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.cantidadPersonas=cantidadPersonas;
        this.Usuario_cedula=Usuario_cedula;
        this.Habitaciones_numero=Habitaciones_numero;
        this.Planes_nombrePlan=Planes_nombrePlan;
        this.fechaCheckIn=fechaCheckIn;
        this.fechaCheckOut=fechaCheckOut;
    }
    public Reserva(){;}
    public Integer getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public Date getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }
    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    public Usuario getUsuario_cedula() {
        return Usuario_cedula;
    }
    public void setUsuario_cedula(Usuario usuario_cedula) {
        Usuario_cedula = usuario_cedula;
    }
    public Plan getPlanes_nombrePlan() {
        return Planes_nombrePlan;
    }
    public void setPlanes_nombrePlan(Plan planes_nombrePlan) {
        Planes_nombrePlan = planes_nombrePlan;
    }
    public Habitacion getHabitaciones_numero() {
        return Habitaciones_numero;
    }
    public void setHabitaciones_numero(Habitacion habitaciones_numero) {
        Habitaciones_numero = habitaciones_numero;
    }
    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }
    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }
    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }
    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }
    
    
}
