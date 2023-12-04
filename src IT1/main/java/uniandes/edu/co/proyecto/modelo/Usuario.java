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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "IDUSER")

    private Integer idUser;
    @Column(name = "TIPODOCUMENTO")
    private String tipoDocumento;
    @Column(name = "NOMBREUSUARIO")
    private String nombreUsuario;
    @Column(name = "CORREO")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "TIPOSUSUARIOS_TIPOUSER", referencedColumnName = "TIPOUSER")
    private TipoUsuario TiposUsuarios_tipoUser;
    
    public Usuario(String tipoDocumento, String nombreUsuario, String correo, TipoUsuario TiposUsuarios_tipoUser){
        
        this.tipoDocumento = tipoDocumento;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.TiposUsuarios_tipoUser = TiposUsuarios_tipoUser;
    }

    public Usuario(){;}

    public Integer getidUser() {
        return idUser;
    }

    public void setIdUser(Integer cedula) {
        this.idUser = cedula;
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
        return TiposUsuarios_tipoUser;
    }

    public void setTipoUsuario(TipoUsuario TiposUsuarios_tipoUser) {
        this.TiposUsuarios_tipoUser = TiposUsuarios_tipoUser;
    }

    
}
