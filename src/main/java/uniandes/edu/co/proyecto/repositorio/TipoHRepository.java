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

    @Query(value = "SELECT * FROM tiposH WHERE nombreTH= :nombreTH", nativeQuery = true)
    TipoH darTipoH(@Param("nombreTH") String nombreTipo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposH (nombreTH, dotacion, capacidad, costoPorNoche) VALUES ( hotelAndes_sequence.nextval , :dotacion, :capacidad, :costoPorNoche)", nativeQuery = true)
    void insertarTiposH(@Param("dotacion") String dotacion, @Param("capacidad") Integer capacidad, @Param("costoPorNoche") double costoPorNoche);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposH SET dotacion = :dotacion, capacidad = :capacidad, costoPorNoche = :costoPorNoche WHERE nombreTH = :nombreTH", nativeQuery = true)
    void actualizarTiposH(@Param("nombreTH") String nombreTH, @Param("dotacion") String dotacion, @Param("capacidad") Integer capacidad, @Param("costoPorNoche") double costoPorNoche);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposH WHERE nombreTH = :nombreTH", nativeQuery = true)
    void eliminarTipoH(@Param("nombreTH") String nombreTH);
    
}
