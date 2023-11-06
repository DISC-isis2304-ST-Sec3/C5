package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva,Integer>{

    @Query(value = "SELECT * FROM reservas", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Query(value = "SELECT * FROM reservas WHERE idReserva= :idReserva", nativeQuery = true)
    Reserva darReserva(@Param("idReserva") Integer idReserva);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (idReserva, fechaEntrada, fechaSalida, cantidadPersonas, Usuarios_idUser, Planes_nombrePlan, Habitaciones_numero) VALUES ( hotelAndes_sequence.nextval , :fechaEntrada, :fechaSalida, :cantidadPersonas, :Usuarios_idUser, :Planes_nombrePlan, :Habitaciones_numero)", nativeQuery = true)
    void insertarReservas(@Param("fechaEntrada") Date fechaEntrada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") Integer cantidadPersonas, @Param("Usuarios_idUser") Integer Usuarios_idUser, @Param("Planes_nombrePlan") String Planes_nombrePlan, @Param("Habitaciones_numero") Integer Habitaciones_numero);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET fechaEntrada = :fechaEntrada, fechaSalida = :fechaSalida, cantidadPersonas = :cantidadPersonas, Usuarios_idUser = :Usuarios_idUser, Planes_nombrePlan = :Planes_nombrePlan, Habitaciones_numero = :Habitaciones_numero WHERE idReserva = :idReserva", nativeQuery = true)
    void actualizarReservas(@Param("idReserva") Integer idReserva, @Param("fechaEntrada") Date fechaEntrada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") Integer cantidadPersonas, @Param("Usuarios_idUser") Integer Usuarios_idUser, @Param("Planes_nombrePlan") String Planes_nombrePlan, @Param("Habitaciones_numero") Integer Habitaciones_numero);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE idReserva = :idReserva", nativeQuery = true)
    void eliminarReserva(@Param("idReserva") Integer idReserva);
    
}
