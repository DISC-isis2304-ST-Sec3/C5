package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Plan;


public interface PlanRepository extends JpaRepository<Plan, String> {
    
    @Query(value = "SELECT * FROM planes", nativeQuery = true)
    Collection<Plan> darPlanes();

    @Query(value = "SELECT * FROM planes WHERE nombrePlan= :nombreePlan", nativeQuery = true)
    Plan darPlan(@Param("nombrePlan") String nombrePlan);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planes (nombrePlan, descuento, periodoVigenciaInicial, periodoVigenciaFinal) VALUES ( hotelAndes_sequence.nextval , :descuento, :periodoVigenciaInicial, :periodoVigenciaFinal)", nativeQuery = true)
    void insertarPlanes(@Param("descuento") double descuento, @Param("periodoVigenciaInicial") Date periodoVigenciaInicial, @Param("periodoVigenciaFinal") Date periodoVigenciaFinal);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE planes SET descuento = :descuento, periodoVigenciaInicial = :periodoVigenciaInicial, periodoVigenciaFinal = :periodoVigenciaFinal WHERE nombrePlan = :nombrePlan", nativeQuery = true)
    void actualizarPlanes(@Param("nombrePlan") String nombrePlan, @Param("descuento") double descuento, @Param("periodoVigenciaInicial") Date periodoVigenciaInicial, @Param("periodoVigenciaFinal") Date periodoVigenciaFinal);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planes WHERE nombrePlan = :nombrePlan", nativeQuery = true)
    void eliminarPlan(@Param("nombrePlan") String nombrePlan);
}
