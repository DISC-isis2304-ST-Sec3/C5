package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Reserva;

public interface ReservasRepository extends JpaRepository<Reserva,Integer>{

    @Query(value = "SELECT * FROM reservas", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Query(value = "SELECT * FROM reservas WHERE idReserva= :idReserva", nativeQuery = true)
    Reserva darReserva(@Param("idReserva") int idReserva);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (idReserva, fechaEntrada, fechaSalida, cantidadPersonas, Usuarios_cedula, Planes_nombrePlan, Habitaciones_numero, fechaCheckIn, fechaCheckOut) VALUES ( hotelAndes_sequence.nextval , :fechaEntrada, :fechaSalida, :cantidadPersonas, :Usuarios_cedula, :Planes_nombrePlan, :Habitaciones_numero, :fechaCheckIn, :fechaCheckOut)", nativeQuery = true)
    void insertarReservas(@Param("fechaEntrada") Date fechaEntrada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") int cantidadPersonas, @Param("Usuarios_cedula") Integer Usuarios_cedula, @Param("Planes_nombrePlan") String Planes_nombrePlan, @Param("Habitaciones_numero") Integer Habitaciones_numero, @Param("fechaCheckIn") Date fechaCheckIn, @Param("fechaCheckOut") Date fechaCheckOut);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET fechaEntrada = :fechaEntrada, fechaSalida = :fechaSalida, cantidadPersonas = :cantidadPersonas, Usuarios_cedula = :Usuarios_cedula, Planes_nombrePlan = :Planes_nombrePlan, Habitaciones_numero = :Habitaciones_numero, fechaCheckIn = :fechaCheckIn, fechaCheckOut = :fechaCheckOut WHERE idReserva = :idReserva", nativeQuery = true)
    void actualizarReservas(@Param("idReserva") int idReserva, @Param("fechaEntrada") Date fechaEntrada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") int cantidadPersonas, @Param("Usuarios_cedula") Integer Usuarios_cedula, @Param("Planes_nombrePlan") String Planes_nombrePlan, @Param("Habitaciones_numero") Integer Habitaciones_numero, @Param("fechaCheckIn") Date fechaCheckIn, @Param("fechaCheckOut") Date fechaCheckOut);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE idReserva = :idReserva", nativeQuery = true)
    void eliminarReserva(@Param("idReserva") int idReserva);
    
}
