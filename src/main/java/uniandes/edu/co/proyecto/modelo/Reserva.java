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
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDRESERVA")
    private Integer idReserva;
    @Column(name = "FECHAENTRADA")
    private Date fechaEntrada;
    @Column(name = "FECHASALIDA")
    private Date fechaSalida;
    @Column(name = "CANTIDADPERSONAS")
    private Integer cantidadPersonas;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_IDUSER", referencedColumnName = "IDUSER")
    private Usuario Usuario_idUser;

    @ManyToOne
    @JoinColumn(name = "PLANES_NOMBREPLAN", referencedColumnName = "NOMBREPLAN")
    private Plan Planes_nombrePlan;

    @ManyToOne
    @JoinColumn(name = "HABITACIONES_NUMERO",referencedColumnName = "NUMERO")
    private Habitacion Habitaciones_numero;

    public Reserva(Date fechaEntrada, Date fechaSalida, Integer cantidadPersonas, Usuario Usuario_idUser, Plan Planes_nombrePlan, Habitacion Habitaciones_numero){

        this.fechaEntrada=fechaEntrada;
        this.fechaSalida=fechaSalida;
        this.cantidadPersonas=cantidadPersonas;
        this.Usuario_idUser=Usuario_idUser;
        this.Habitaciones_numero=Habitaciones_numero;
        this.Planes_nombrePlan=Planes_nombrePlan;
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
    public Usuario getUsuario_idUser() {
        return Usuario_idUser;
    }
    public void setUsuario_idUser(Usuario usuario_idUser) {
        Usuario_idUser = usuario_idUser;
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

    
    
    
}
