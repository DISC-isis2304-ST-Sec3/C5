package uniandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idReserva;
    private String fechaEntrada;
    private String fechaSalida;
    private Integer cantidadPersonas;


    public Integer getIdReserva() {
        return idReserva;
    }
    public String getFechaEntrada() {
        return fechaEntrada;
    }
    public String getFechaSalida() {
        return fechaSalida;
    }
    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
}
