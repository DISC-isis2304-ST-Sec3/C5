package uniandes.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    private String tipoH;

    public Integer getNumeroHabitacion(Integer numero) {
        this.numero = numero;
    }
    public String getTipoHabitacion() {
        return tipoH;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public void setTipo(String tipoH) {
        this.tipoH = tipoH;
    }
    
}
