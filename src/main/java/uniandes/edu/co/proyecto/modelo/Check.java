package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "checks")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne
    @JoinColumn(name = "Reservas_idReserva", referencedColumnName = "idReserva")
    private Reserva Reservas_idReserva;
    private Date fechaIn;
    private Date fechaOut;

    public Check(Date fechaIn, Date fechaOut){
        this.fechaIn=fechaIn;
        this.fechaOut=fechaOut;
    }

    public Check(){;}

    public Reserva getReservas_idReserva() {
        return Reservas_idReserva;
    }

    public void setReservas_idReserva(Reserva reservas_idReserva) {
        Reservas_idReserva = reservas_idReserva;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    
    
}
