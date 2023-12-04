package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "habitaciones")
public class HabitacionEmbedded {
    @Id
    private String id;
    @Field("numeroHab")
    private Integer numeroHab;
    @Field("disponible")
    private String disponible;
    @Field("tipoHabitacion")
    private TipoHabitacionEmbedded tipoHabitacion;

    @DBRef
    private List<ConsumoServicio> consumoServicio_id;

    public HabitacionEmbedded(Integer numeroHab, TipoHabitacionEmbedded tipoHabitacion, String disponible, List<ConsumoServicio> consumoServicio_id){
        this.numeroHab = numeroHab;
        this.tipoHabitacion = tipoHabitacion;
        this.disponible = disponible;
        this.consumoServicio_id = consumoServicio_id;
    }

    public HabitacionEmbedded(Integer numeroHab, TipoHabitacionEmbedded tipoHabitacion, String disponible){
        this.numeroHab = numeroHab;
        this.tipoHabitacion = tipoHabitacion;
        this.disponible = disponible;
    }

    public HabitacionEmbedded(){}

    public Integer getNumero() {
        return numeroHab;
    }
    public void setNumero(Integer numeroHab) {
        this.numeroHab = numeroHab;
    }
    public TipoHabitacionEmbedded getTipoHabitacion() {
        return tipoHabitacion;
    }
    public void setTipoHabitacion(TipoHabitacionEmbedded tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String isDisponible() {
        return disponible;
    }
    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public List<ConsumoServicio> getConsumoServicio() {
        return consumoServicio_id;
    }

    public void setConsumoServicio(List<ConsumoServicio> consumoServicio_id) {
        this.consumoServicio_id = consumoServicio_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

}
