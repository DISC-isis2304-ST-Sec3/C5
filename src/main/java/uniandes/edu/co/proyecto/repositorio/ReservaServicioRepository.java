package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.modelo.ReservaServicio;

public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, Integer>{
   
    @Query(value = "SELECT * FROM reservasServicios", nativeQuery = true)
    Collection<Reserva> darReservasServicios();

    @Query(value = "SELECT * FROM reservasServicios WHERE idReservaServicio= :idReservaServicio", nativeQuery = true)
    ReservaServicio darReservaServicio(@Param("idReservaServicio") Integer idReservaServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservasServicios (idReservaServicio, fecha, duracion, Servicios_idServicio, Reserva_idReserva, Empleado_idEmpleado) VALUES ( hotelAndes_sequence.nextval , :fecha, :duracion, :Servicios_idServicio, :Reserva_idReserva, :Empleado_idEmpleado)", nativeQuery = true)
    void insertarReservasServicio(@Param("fecha") Date fecha, @Param("duracion") Integer duracion, @Param("Servicios_idServicio") Integer Servicios_idServicio, @Param("Reserva_idReserva") Integer Reserva_idReserva, @Param("Empleado_idEmpleado") Integer Empleado_idEmpleado);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservasServicios SET fecha = :fecha, duracion = :duracion, Servicios_idServicio = :Servicios_idServicio, Reserva_idReserva = :Reserva_idReserva, Empleado_idEmpleado = :Empleado_idEmpleado WHERE idReservaServicio = :idReservaServicio", nativeQuery = true)
    void actualizarReservasServicio(@Param("idReservaServicio") Integer idReservaServicio, @Param("fecha") Date fecha, @Param("duracion") Integer duracion, @Param("Servicios_idServicio") Integer Servicios_idServicio, @Param("Reserva_idReserva") Integer Reserva_idReserva, @Param("Empleado_idEmpleado") Integer Empleado_idEmpleado);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservasServicios WHERE idReservaServicio = :idReservaServicio", nativeQuery = true)
    void eliminarReservaServicio(@Param("idReservaServicio") Integer idReservaServicio);
    
}
