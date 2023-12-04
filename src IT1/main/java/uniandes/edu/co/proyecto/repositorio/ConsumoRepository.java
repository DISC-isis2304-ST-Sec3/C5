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
    
    public interface RespuestaRFC5 {
        int getIDUser();
        String getNombreUsuario();
        double getTotal_Gastado_En_Consumos(); 
    }

    @Query(value = "SELECT * FROM consumos", nativeQuery = true)
    Collection<Consumo> darConsumos();

    @Query(value = "SELECT * FROM consumos WHERE idConsumo= :idConsumo", nativeQuery = true)
    Consumo darConsumo(@Param("idConsumo") Integer idConsumo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumos (idConsumo, descripcion, fecha, costo, Servicios_idServicio) VALUES ( hotelAndes_sequence.nextval , :descripcion, :fecha, :costo, :Servicios_idServicio)", nativeQuery = true)
    void insertarConsumos(@Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("costo") double costo, @Param("Servicios_idServicio") Integer Servicios_idServicio);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE consumos SET descripcion = :descripcion, fecha = :fecha, costo = :costo, Servicios_idServicio = :Servicios_idServicio WHERE idConsumo = :idConsumo", nativeQuery = true)
    void actualizarConsumos(@Param("idConsumo") Integer idConsumo, @Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("costo") double costo, @Param("Servicios_idServicio") Integer Servicios_idServicio);
   
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM consumos WHERE idConsumo = :idConsumo", nativeQuery = true)
    void eliminarConsumo(@Param("idConsumo") Integer idConsumo);


    @Query(value = "SELECT u.iduser, u.nombreusuario, SUM(c.costo) AS \"Total_Gastado_En_Consumos\"\n" + //
                    "FROM usuarios u\n" + //
                    "LEFT JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
                    "LEFT JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva\n" + //
                    "LEFT JOIN servicios s ON rs.servicios_idservicio = s.idservicio\n" + //
                    "LEFT JOIN consumos c ON s.idservicio = c.servicios_idservicio\n" + //
                    "WHERE u.nombreusuario = :nombreUsuario\n" + //
                    "AND c.fecha >= TO_DATE( :fechaInicio , 'YYYY-MM-DD')\n" + //
                    "AND c.fecha <= TO_DATE( :fechaFin, 'YYYY-MM-DD') -- Cambia la fecha final si es diferente\n" + //
                    "GROUP BY u.iduser, u.nombreusuario\n" + //
                    "ORDER BY \"Total_Gastado_En_Consumos\" DESC", nativeQuery = true)
    Collection<RespuestaRFC5> darConsumoPorUsuarioYFechas(@Param("nombreUsuario") String nombreUsuario, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
