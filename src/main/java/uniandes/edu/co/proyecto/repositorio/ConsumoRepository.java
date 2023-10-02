package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Consumo;


public interface ConsumoRepository extends JpaRepository<Consumo, Integer>{
    
    @Query(value = "SELECT * FROM consumos", nativeQuery = true)
    Collection<Consumo> darConsumos();

    @Query(value = "SELECT * FROM consumos WHERE idConsumo= :idConsumo", nativeQuery = true)
    Consumo darConsumo(@Param("idConsumo") int idConsumo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumos (idConsumo, descripcion, fecha, costo, Servicios_idServicio, Reservas_idReserva) VALUES ( hotelAndes_sequence.nextval , :descripcion, :fecha, :costo, :Servicios_idServicio, :Reservas_idReserva)", nativeQuery = true)
    void insertarConsumos(@Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("costo") double costo, @Param("Servicios_idServicio") Integer Servicios_idServicio, @Param("Reservas_idReserva") Integer Reservas_idReserva);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE consumos SET descripcion = :descripcion, fecha = :fecha, costo = :costo, Servicios_idServicio = :Servicios_idServicio, Reservas_idReserva = :Reservas_idReserva WHERE idConsumo = :idConsumo", nativeQuery = true)
    void actualizarConsumos(@Param("idConsumo") int idConsumo, @Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("costo") double costo, @Param("Servicios_idServicio") Integer Servicios_idServicio, @Param("Reservas_idReserva") Integer Reservas_idReserva);
   
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM consumos WHERE idConsumo = :idConsumo", nativeQuery = true)
    void eliminarConsumo(@Param("idConsumo") int idConsumo);
}
