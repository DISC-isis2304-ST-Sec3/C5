package uniandes.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsumo;
    private String descripcion;
    private String fecha;
    private Number costo;
    private Integer Servicios_idsServicios;
    private Integer Reservas_idReserva;


    public Integer getId() {
        return idConsumo;
    }
    public Number getCosto() {
        return costo;
    }
    public String getFecha() {
        return fecha;
    }


    public void setId(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }
    public void setCosto(Number costo) {
        this.costo = costo;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
