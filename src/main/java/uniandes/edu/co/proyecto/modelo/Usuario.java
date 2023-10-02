package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer cedula;
    private String tipoDocumento;
    private String nombreUsuario;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "TiposUsuarios_nombre", referencedColumnName = "nombre")
    private TipoUsuario TiposUsuarios_nombre;
    
    public Usuario(String tipoDocumento, String nombreUsuario, String correo, TipoUsuario TiposUsuarios_nombre){
        
        this.tipoDocumento = tipoDocumento;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.TiposUsuarios_nombre = TiposUsuarios_nombre;
    }

    public Usuario(){;}

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoUsuario getTipoUsuario() {
        return TiposUsuarios_nombre;
    }

    public void setTipoUsuario(TipoUsuario TiposUsuarios_nombre) {
        this.TiposUsuarios_nombre = TiposUsuarios_nombre;
    }

    
}
