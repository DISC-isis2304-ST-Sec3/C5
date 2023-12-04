package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "servicios")
public class ServicioEmbedded {
    @Id
    private String id;
    @Field("nombre")
    private String nombre;
    @Field("descripcionServicio")
    private String descripcionServicio;
    @Field("horario")
    private String horario;
    @Field("capacidadServicio")
    private Integer capacidadServicio;
    @Field("menu")
    private List<String> menu;
    

    public ServicioEmbedded(String nombre, String descripcionServicio, String horario, Integer capacidadServicio, List<String> menu){
        
        this.nombre=nombre;
        this.descripcionServicio=descripcionServicio;
        this.horario=horario;
        this.capacidadServicio=capacidadServicio;
        this.menu=menu;
    }
    
    public ServicioEmbedded(){}

    
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcionServicio;
    }

    public String getHorario() {
        return horario;
    }

    public Integer getCapacidad() {
        return capacidadServicio;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public void setDescripcion(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setCapacidad(Integer capacidadServicio) {
        this.capacidadServicio = capacidadServicio;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    
    
}
