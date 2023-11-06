package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion,Integer>{

    
    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM habitaciones WHERE numero= :numero", nativeQuery = true)
    Habitacion darHabitacion(@Param("numero") Integer numero);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (numero, TipoH_nombreTH) VALUES ( hotelAndes_sequence.nextval , :TipoH_nombreTH)", nativeQuery = true)
    void insertarHabitaciones(@Param("TipoH_nombreTH") String TipoH_nombreTH);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET TipoH_nombreTH = :TipoH_nombreTH WHERE numero = :numero", nativeQuery = true)
    void actualizarHabitaciones(@Param("numero") Integer numero, @Param("TipoH_nombreTH") String TipoH_nombreTH);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE numero = :numero", nativeQuery = true)
    void eliminarHabitacion(@Param("numero") Integer numero);
}
