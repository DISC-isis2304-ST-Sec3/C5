package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "consumosServicios")
public class ConsumoServicio {

    @Id
    private String id;
    @Field("descripcionConsumo")
    private String descripcionConsumo;
    @Field("fecha")
    private LocalDate fecha;
    @Field("costo")
    private Integer costo;
    
    private ServicioEmbedded servicios;

    public ConsumoServicio(String descripcionConsumo, LocalDate fecha, Integer costo, ServicioEmbedded servicios){
        this.descripcionConsumo=descripcionConsumo;
        this.fecha=fecha;
        this.costo=costo;
        this.servicios=servicios;
    }

    public ConsumoServicio(String descripcionConsumo, LocalDate fecha, Integer costo){
        this.descripcionConsumo=descripcionConsumo;
        this.fecha=fecha;
        this.costo=costo;
    }

    public ConsumoServicio(){}

    public String getIdConsumo() {
        return id;
    }

    public void setIdConsumo(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcionConsumo;
    }

    public void setDescripcion(String descripcionConsumo) {
        this.descripcionConsumo = descripcionConsumo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public ServicioEmbedded getServicios() {
        return servicios;
    }

    public void setServicios(ServicioEmbedded servicios) {
        this.servicios = servicios;
    }


    
    
}
