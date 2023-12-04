package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion,Integer>{


    public interface RespuestaRFC1 {
        int getNumero_Habitacion();
        String getServicio();
        double getDinero_Recolectado(); 
    }
    public interface RespuestaRFC3 {
        int getnumero_habitacion();
        double getocupacion_porcentaje(); 
    }
    
    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM habitaciones WHERE numero= :numero", nativeQuery = true)
    Habitacion darHabitacion(@Param("numero") Integer numero);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (numero, TiposH_nombreTH) VALUES ( hotelAndes_sequence.nextval , :TiposH_nombreTH)", nativeQuery = true)
    void insertarHabitaciones(@Param("TiposH_nombreTH") String TiposH_nombreTH);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET TiposH_nombreTH = :TiposH_nombreTH WHERE numero = :numero", nativeQuery = true)
    void actualizarHabitaciones(@Param("numero") Integer numero, @Param("TiposH_nombreTH") String TiposH_nombreTH);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE numero = :numero", nativeQuery = true)
    void eliminarHabitacion(@Param("numero") Integer numero);

    @Query(value = "SELECT h.numero AS Numero_Habitacion, s.nombreservicio AS Servicio, \n" + //
                    "SUM(s.costo) AS Dinero_Recolectado\n" + //
                    "FROM habitaciones h \n" + //
                    "INNER JOIN reservas r ON h.numero = r.habitaciones_numero\n" + //
                    "INNER JOIN reservasservicios rss ON r.idreserva = rss.reservas_idreserva\n" + //
                    "INNER JOIN servicios s ON s.idservicio = rss.servicios_idservicio\n" + //
                    "WHERE r.fechaentrada >= TRUNC(SYSDATE, 'YYYY') - INTERVAL '1' YEAR\n" + //
                    "GROUP BY h.numero, s.nombreservicio\n" + //
                    "ORDER BY h.numero, s.nombreservicio", nativeQuery = true)
    Collection<RespuestaRFC1> darDineroRecolectadoPorServiciosPorHabitacionEnUltimoAño();


    @Query(value = "SELECT h.numero AS numero_habitacion, \n" + //
                    "ROUND((COUNT(r.idreserva) / 365) * 100, 2) AS ocupacion_porcentaje\n" + //
                    "FROM habitaciones h\n" + //
                    "LEFT JOIN reservas r ON h.numero = r.habitaciones_numero\n" + //
                    "WHERE r.fechasalida >= (SYSDATE - 365)\n" + //
                    "GROUP BY h.numero\n" + //
                    "ORDER BY h.numero", nativeQuery = true)
    Collection<RespuestaRFC3> darIndiceDeOcupacionEnUltimoAño();

}
