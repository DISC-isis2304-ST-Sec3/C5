package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "TipoH_nombreTipo", referencedColumnName = "nombreTipo")
    private TipoH TipoH_nombreTipo;

    public Habitacion(TipoH TipoH_nombreTipo){
        this.TipoH_nombreTipo = TipoH_nombreTipo;

    }
    public Habitacion(){;}
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public TipoH getTipoH_nombreTipo() {
        return TipoH_nombreTipo;
    }
    public void setTipoH_nombreTipo(TipoH tipoH_nombreTipo) {
        TipoH_nombreTipo = tipoH_nombreTipo;
    }

    

}
