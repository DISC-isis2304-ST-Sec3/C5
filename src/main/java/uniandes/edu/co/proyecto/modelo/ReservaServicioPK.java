package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ReservaServicioPK implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "Servicio_idServicio", referencedColumnName="idServicio")
    private Servicio Servicio_idServicio;

    @ManyToOne
    @JoinColumn(name = "Usuario_cedula", referencedColumnName = "cedula")
    private Usuario Usuario_cedula;

    private Date fechaReservaServicio;

    public ReservaServicioPK(Servicio Servicio_idServicio, Usuario Usuario_cedula, Date fechaReservaServicio){

        super();
        this.Servicio_idServicio=Servicio_idServicio;
        this.Usuario_cedula = Usuario_cedula;
        this.fechaReservaServicio=fechaReservaServicio;

    }

    public Servicio getServicio_idServicio() {
        return Servicio_idServicio;
    }

    public void setServicio_idServicio(Servicio servicio_idServicio) {
        Servicio_idServicio = servicio_idServicio;
    }

    public Usuario getUsuario_cedula() {
        return Usuario_cedula;
    }

    public void setUsuario_cedula(Usuario usuario_cedula) {
        Usuario_cedula = usuario_cedula;
    }

    public Date getFechaReservaServicio() {
        return fechaReservaServicio;
    }

    public void setFechaReservaServicio(Date fechaReservaServicio) {
        this.fechaReservaServicio = fechaReservaServicio;
    }
    
    
}
