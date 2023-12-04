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
@Table(name = "habitaciones")
public class Habitacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NUMERO")
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "TIPOSH_NOMBRETH", referencedColumnName = "NOMBRETH")
    private TipoH TiposH_nombreTH;

    public Habitacion(TipoH TiposH_nombreTH){
        this.TiposH_nombreTH = TiposH_nombreTH;

    }
    public Habitacion(){;}
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public TipoH getTiposH_nombreTipo() {
        return TiposH_nombreTH;
    }
    public void setTiposH_nombreTipo(TipoH tiposH_nombreTipo) {
        TiposH_nombreTH = tiposH_nombreTipo;
    }

    

}
