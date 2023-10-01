package uniandes.modelo;

import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idUser;
    private String nombreUsuario;
    private String correo;
    private TipoDocumento tipoDocumento;
    enum TipoDocumento{
        CC,TI,PA
    }    
    
    public Integer getIdUser() {
        return idUser;
    }
    public String getNombre() {
        return nombreUsuario;
    }
    public String correo() {
        return correo;
    }
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    public void setidUser(Integer idUser) {
        this.idUser = idUser;
    }
    public void setNombre(String nombre) {
        this.nombreUsuario = nombre;
    }
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public void setcorreo(String correo) {
        this.correo = correo;
    }
    
    

}