package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.ReservaServicio;

public interface ReservaServicioRepository extends JpaRepository<ReservaServicio,Integer>{

    
    @Query(value = "SELECT * FROM reservasServicios", nativeQuery = true)
    Collection<ReservaServicio> darReservasServicio();

    @Query(value = "SELECT * FROM reservasServicios WHERE Servicio_idServicio = :Servicio_idServicio AND Usuarios_idUsuario = :Usuarios_idUsuario AND fechaReservaServicio = :fechaReservaServicio", nativeQuery = true)
    ReservaServicio darReservaServicio(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Usuarios_idUsuario") Integer Usuarios_idUsuario, @Param("fechaReservaServicio") Date fechaReservaServicio);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservasServicios (Servicio_idServicio, Usuarios_idUsuario, fechaReservaServicio) VALUES (:Servicio_idServicio, :Usuarios_idUsuario, :fechaReservaServicio)", nativeQuery = true)
    void insertarFrecuentan(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Usuarios_idUsuario") Integer Usuarios_idUsuario, @Param("fechaReservaServicio") Date fechaReservaServicio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservasServicios SET Servicio_idServicio = :Servicio_idServicio_actualizado, Usuarios_idUsuario = :Usuarios_idUsuario_actualizado, fechaReservaServicio = :fechaReservaServicio_actualizado WHERE Servicio_idServicio = :Servicio_idServicio AND Usuarios_idUsuario = :Usuarios_idUsuario AND fechaReservaServicio = :fechaReservaServicio", nativeQuery = true)
    void actualizarFrecuentan(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Usuarios_idUsuario") Integer Usuarios_idUsuario, @Param("fechaReservaServicio") Date fechaReservaServicio, @Param("Servicio_idServicio_actualizado") Integer Servicio_idServicio_actualizado, @Param("Usuarios_idUsuario_actualizado") Integer Usuarios_idUsuario_actualizado, @Param("fechaReservaServicio_actualizado") Date fechaReservaServicio_actualizado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservasServicios WHERE Servicio_idServicio = :Servicio_idServicio AND Usuarios_idUsuario = :Usuarios_idUsuario", nativeQuery = true)
    void eliminarReservaServicio(@Param("Servicio_idServicio") Integer Servicio_idServicio, @Param("Usuarios_idUsuario") Integer Usuarios_idUsuario);

    
}
