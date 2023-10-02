package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PlanServicioPK implements Serializable{

    @ManyToOne
    @JoinColumn(name="Planes_nombrePlan", referencedColumnName = "nombrePlan")
    private Plan Planes_nombrePlan;

    @ManyToOne
    @JoinColumn(name = "Servicios_idServicio", referencedColumnName = "idServicio")
    private Servicio Servicios_idServicio;

    public PlanServicioPK(Plan Planes_nombrePlan, Servicio Servicios_idServicio){
        super();
        this.Planes_nombrePlan = Planes_nombrePlan;
        this.Servicios_idServicio = Servicios_idServicio;
    }
    
    public void setPlanes_nombrePlan(Plan Planes_nombrePlan){
        this.Planes_nombrePlan = Planes_nombrePlan;
    }

    public void setServicios_idServicio(Servicio Servicios_idServicio){
        this.Servicios_idServicio = Servicios_idServicio;
    }

    public Plan getPlanes_nombrePlan(){
        return Planes_nombrePlan;
    }

    public Servicio getServicios_idServicios(){
        return Servicios_idServicio;
    }
}
