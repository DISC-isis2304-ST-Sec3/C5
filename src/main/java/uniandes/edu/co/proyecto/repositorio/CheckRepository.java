package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Check;


public interface CheckRepository extends JpaRepository<Check, Integer>{

    @Query(value = "SELECT * FROM checks", nativeQuery = true)
    Collection<Check> darChecks();

    @Query(value = "SELECT * FROM checks WHERE Reservas_idReserva= :Reservas_idReserva", nativeQuery = true)
    Check darCheck(@Param("Reservas_idReserva") int Reservas_idReserva);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO checks (Reservas_idReserva, fechaIn, fechaOut) VALUES ( hotelAndes_sequence.nextval , :fechaIn, :fechaOut)", nativeQuery = true)
    void insertarChecks(@Param("fechaIn") Date fechaIn,@Param("fechaOut") Date fechaOut);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE checks SET fechaIn = :fechaIn, fechaOut = : fechaOut WHERE Reservas_idReserva = :Reservas_idReserva", nativeQuery = true)
    void actualizarChecks(@Param("Reservas_idReserva") int Reservas_idReserva, @Param("fechaIn") Date fechaIn,@Param("fechaOut") Date fechaOut);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM checks WHERE Reservas_idReserva = :Reservas_idReserva", nativeQuery = true)
    void eliminarCheck(@Param("Reservas_idReserva") int Reservas_idReserva);

    
}
