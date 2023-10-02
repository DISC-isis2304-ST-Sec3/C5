package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservasServicios")
public class ReservaServicio {
    
    @EmbeddedId
    private ReservaServicioPK pk;

    public ReservaServicio(Servicio Servicios_idServicio, Usuario Usuarios_cedula, Date fechaReservaServicio){
        
        this.pk = new ReservaServicioPK(Servicios_idServicio, Usuarios_cedula, fechaReservaServicio);
        
    }

    public ReservaServicio(){;}

    public ReservaServicioPK getPk() {
        return pk;
    }

    public void setPk(ReservaServicioPK pk) {
        this.pk = pk;
    }

    
}
