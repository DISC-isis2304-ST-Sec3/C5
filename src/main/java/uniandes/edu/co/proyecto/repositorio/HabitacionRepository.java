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
    Habitacion darHabitacion(@Param("numero") int numero);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (numero, TiposH_nombreTipo) VALUES ( hotelAndes_sequence.nextval , :TiposH_nombreTipo)", nativeQuery = true)
    void insertarHabitaciones(@Param("TiposH_nombreTipo") String TiposH_nombreTipo);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET TiposH_nombreTipo = :TiposH_nombreTipo WHERE numero = :numero", nativeQuery = true)
    void actualizarHabitaciones(@Param("numero") int numero, @Param("TiposH_nombreTipo") String TiposH_nombreTipo);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE numero = :numero", nativeQuery = true)
    void eliminarHabitacion(@Param("numero") int numero);
}
