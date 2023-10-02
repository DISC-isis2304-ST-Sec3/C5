package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="planesServicios")

public class PlanServicio {

    @EmbeddedId
    private PlanServicioPK pk;

    public PlanServicio(Plan Planes_nombrePlan, Servicio Servicios_idServicio){
        this.pk=new PlanServicioPK(Planes_nombrePlan, Servicios_idServicio);
    }
    public PlanServicio(){;}
    
    public PlanServicioPK getPk() {
        return pk;
    }
    public void setPk(PlanServicioPK pk) {
        this.pk = pk;
    }
    
}
