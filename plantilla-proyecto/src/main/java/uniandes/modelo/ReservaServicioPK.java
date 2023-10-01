package uniandes.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Embeddable
public class ReservaServicioPK implements Serializable{
    //Servicios *-* Usuarios
    @ManyToMany
    @JoinColumn(name="idSerivicio", referencedColumnName="servicios_IdServicio")
    private Servicio idServicio;


}
