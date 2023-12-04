package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "IDEMPLEADO")
    private Integer idEmpleado;

    @ManyToOne
    @JoinColumn(name = "TIPOSUSUARIOS_TIPOUSER", referencedColumnName = "TIPOUSER")
    private TipoUsuario TiposUsuarios_tipoUser;

    public Empleado(TipoUsuario TiposUsuarios_tipoUser){
        this.TiposUsuarios_tipoUser = TiposUsuarios_tipoUser;
    }

    public Empleado(){;}

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoUsuario getTiposUsuarios_tipoUser() {
        return TiposUsuarios_tipoUser;
    }

    public void setTiposUsuarios_tipoUser(TipoUsuario tiposUsuarios_tipoUser) {
        TiposUsuarios_tipoUser = tiposUsuarios_tipoUser;
    }
    
}
