package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE idServicio= :idServicio", nativeQuery = true)
    Servicio darServicio(@Param("idServicio") int idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (idServicio, nombreServicio, descripcion, horario, capacidad, costo, menu) VALUES ( hotelAndes_sequence.nextval , :nombreServicio, :descripcion, :horario, :capacidad, :costo, :menu)", nativeQuery = true)
    void insertarServicios(@Param("nombreServicio") String nombreServicio, @Param("descripcion") String descripcion, @Param("horario") String horario, @Param("capacidad") int capacidad, @Param("costo") double costo, @Param("menu") String menu);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET nombreServicio = :nombreCerveza, descripcion = :descripcion, horario = :horario, capacidad = :capacidad, costo = :costo, menu = :menu WHERE idServicio = :idServicio", nativeQuery = true)
    void actualizarServicios(@Param("idServicio") int idServicio, @Param("nombreServicio") String nombreServicio, @Param("descripcion") String descripcion, @Param("horario") String horario, @Param("capacidad") int capacidad, @Param("costo") double costo, @Param("menu") String menu);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE idServicio = :idServicio", nativeQuery = true)
    void eliminarServicio(@Param("idServicio") int idServicio);
}
