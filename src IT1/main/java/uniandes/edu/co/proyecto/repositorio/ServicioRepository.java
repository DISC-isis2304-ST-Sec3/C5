package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

    public interface RespuestaRFC2 {
        int gettotal_consumos();
        String getServicio();       
    }
    public interface RespuestaRFC4 {
        String getNombreServicio();
        Date getfecha(); 
        int getDuracion();
        double getCosto();      
    }

    public interface RespuestaRFC8 {
        int getIDServicio();
        String getNombreServicio(); 
        int getSolicitudesSemanales();   
    }

    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM servicios WHERE idServicio= :idServicio", nativeQuery = true)
    Servicio darServicio(@Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (idServicio, nombreServicio, descripcion, horario, capacidad, costo) VALUES ( hotelAndes_sequence.nextval , :nombreServicio, :descripcion, :horario, :capacidad, :costo)", nativeQuery = true)
    void insertarServicios(@Param("nombreServicio") String nombreServicio, @Param("descripcion") String descripcion, @Param("horario") String horario, @Param("capacidad") Integer capacidad, @Param("costo") double costo);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET nombreServicio = :nombreServicio, descripcion = :descripcion, horario = :horario, capacidad = :capacidad, costo = :costo WHERE idServicio = :idServicio", nativeQuery = true)
    void actualizarServicios(@Param("idServicio") Integer idServicio, @Param("nombreServicio") String nombreServicio, @Param("descripcion") String descripcion, @Param("horario") String horario, @Param("capacidad") Integer capacidad, @Param("costo") double costo);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE idServicio = :idServicio", nativeQuery = true)
    void eliminarServicio(@Param("idServicio") Integer idServicio);


    @Query(value = "SELECT s.nombreservicio, COUNT(rs.servicios_idservicio) AS total_consumos\n" + //
                    "FROM servicios s\n" + //
                    "LEFT JOIN reservasservicios rs ON s.idservicio = rs.servicios_idservicio\n" + //
                    "WHERE rs.fecha BETWEEN TO_DATE( :fechaInicio , 'YYYY-MM-DD') AND TO_DATE( :fechaFin , 'YYYY-MM-DD')\n" + //
                    "GROUP BY s.nombreservicio\n" + //
                    "ORDER BY total_consumos DESC\n" + //
                    "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    Collection<RespuestaRFC2> dar20MasPopulares(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin );


    @Query(value = "SELECT s.nombreservicio, rs.fecha, rs.duracion, s.costo\n" + //
            "FROM servicios s\n" + //
            "JOIN reservasservicios rs ON s.idservicio = rs.servicios_idservicio\n" + //
            "WHERE s.costo BETWEEN :costoInicial AND :costoFinal\n" + //
            "AND rs.fecha BETWEEN TO_DATE( :fechaInicio , 'YYYY-MM-DD') AND TO_DATE( :fechaFin, 'YYYY-MM-DD')\n" + //
            "AND s.nombreservicio = :nameServicio", nativeQuery = true)
    Collection<RespuestaRFC4> darServiciosQueCumplenConCaracteristica(@Param("costoInicial")int costoInicial, @Param("costoFinal") int costoFinal, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, @Param("nameServicio") String nameServicio);


    @Query(value = "WITH ServiciosSolicitados AS (\n" + //
            "    SELECT\n" + //
            "        s.idservicio,\n" + //
            "        s.nombreservicio,\n" + //
            "        COUNT(*) AS solicitudes_semanales\n" + //
            "    FROM\n" + //
            "        servicios s\n" + //
            "    JOIN\n" + //
            "        reservasservicios rs ON s.idservicio = rs.servicios_idservicio\n" + //
            "    WHERE\n" + //
            "        rs.fecha >= (SYSDATE - 365) \n" + //
            "    GROUP BY\n" + //
            "        s.idservicio, s.nombreservicio\n" + //
            ")\n" + //
            "SELECT\n" + //
            "    ss.idservicio,\n" + //
            "    ss.nombreservicio,\n" + //
            "    ss.solicitudes_semanales\n" + //
            "FROM\n" + //
            "    ServiciosSolicitados ss\n" + //
            "WHERE\n" + //
            "    ss.solicitudes_semanales < 3", nativeQuery = true)
    Collection<RespuestaRFC8> darServiciosPocaDemanda();
}
