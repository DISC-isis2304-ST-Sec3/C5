package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.PlanServicio;


public interface PlanServicioRepository extends JpaRepository<PlanServicio,Integer>{
    
    @Query(value = "SELECT * FROM planesServicios", nativeQuery = true)
    Collection<PlanServicio> darPlanesServicio();

    @Query(value = "SELECT * FROM planesServicios WHERE Servicio_idServicio = :Servicio_idServicio AND Planes_nombrePlan = :Planes_nombrePlan", nativeQuery = true)
    PlanServicio darPlanServicio(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Planes_nombrePlan") String Planes_nombrePlan);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesServicios (Servicio_idServicio, Planes_nombrePlan) VALUES (:Servicio_idServicio, :Planes_nombrePlan)", nativeQuery = true)
    void insertarFrecuentan(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Planes_nombrePlan") String Planes_nombrePlan);

    @Modifying
    @Transactional
    @Query(value = "UPDATE planesServicios SET Servicio_idServicio = :Servicio_idServicio_actualizado, Planes_nombrePlan = :Planes_nombrePlan_actualizado WHERE Servicio_idServicio = :Servicio_idServicio AND Planes_nombrePlan = :Planes_nombrePlan", nativeQuery = true)
    void actualizarFrecuentan(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Planes_nombrePlan") String Planes_nombrePlan, @Param("Servicio_idServicio_actualizado") Integer Servicio_idServicio_actualizado, @Param("Planes_nombrePlan_actualizado") String Planes_nombrePlan_actualizado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesServicios WHERE Servicio_idServicio = :Servicio_idServicio AND Planes_nombrePlan = :Planes_nombrePlan", nativeQuery = true)
    void eliminarPlanServicio(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Planes_nombrePlan") String Planes_nombrePlan);
}
