package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.TipoH;

public interface TipoHRepository extends JpaRepository<TipoH,String>{

    @Query(value = "SELECT * FROM tiposH", nativeQuery = true)
    Collection<TipoH> darTiposH();

    @Query(value = "SELECT * FROM tiposH WHERE nombreTipo= :nombreTipo", nativeQuery = true)
    TipoH darTipoH(@Param("nombreTipo") String nombreTipo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposH (nombreTipo, dotacion, capacidad, costoNoche) VALUES ( hotelAndes_sequence.nextval , :dotacion, :capacidad, :costoNoche)", nativeQuery = true)
    void insertarTiposH(@Param("dotacion") String dotacion, @Param("capacidad") int capacidad, @Param("costoNoche") double costoNoche);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposH SET dotacion = :dotacion, capacidad = :capacidad, costoNoche = :costoNoche WHERE nombreTipo = :nombreTipo", nativeQuery = true)
    void actualizarTiposH(@Param("nombreTipo") String nombrePlan, @Param("dotacion") String dotacion, @Param("capacidad") int capacidad, @Param("costoNoche") double costoNoche);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposH WHERE nombreTipo = :nombreTipo", nativeQuery = true)
    void eliminarTipoH(@Param("nombreTipo") String nombreTipo);
    
}
