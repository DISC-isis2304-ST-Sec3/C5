package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    public interface RespuestaRFC7 {
        int getIDUser();
        String getNombreUsuario();
        double getInfoBuenCliente(); 
    }

    public interface RespuestaRFC9 {
        int getIDUser();
        String getTipoDocumento();
        String getCorreo();
        String getNombreUsuario();
        int getNumeroDeConsumos(); 
    }

    public interface RespuestaRFC10 {
        int getIDUser();
        String getTipoDocumento();
        String getCorreo();
        String getNombreUsuario();
    }

    public interface RespuestaRFC12 {
        int getIDUser();
        String getTipoDocumento();
        String getCorreo();
        String getNombreUsuario();
        String getRazon();
    }

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE idUser= :idUser", nativeQuery = true)
    Usuario darUsuario(@Param("idUser") Integer idUser);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (idUser, tipoDocumento, nombreUsuario, correo, TiposUsuarios_tipoUser) VALUES ( hotelAndes_sequence.nextval , :tipoDocumento, :nombreUsuario, :correo, :TiposUsuarios_tipoUser)", nativeQuery = true)
    void insertarUsuario(@Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET tipoDocumento = :tipoDocumento, nombreUsuario = :nombreUsuario, correo = :correo, TiposUsuarios_tipoUser = :TiposUsuarios_tipoUser WHERE idUser = :idUser", nativeQuery = true)
    void actualizarUsuario(@Param("idUser") Integer idUser, @Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE idUser = :idUser", nativeQuery = true)
    void eliminarUsuario(@Param("idUser") Integer idUser);

    @Query(value = "SELECT iduser, nombreusuario, 'Ha estado en el hotel por al menos dos semanas' AS \"Información de buen Cliente\"\n" + //
            "FROM usuarios u\n" + //
            "WHERE EXISTS (\n" + //
            "    SELECT 1\n" + //
            "    FROM reservas r\n" + //
            "    WHERE r.usuarios_iduser = u.iduser\n" + //
            "      AND r.fechasalida >= TO_DATE('2022-11-05', 'YYYY-MM-DD') - INTERVAL '1' YEAR\n" + //
            "    GROUP BY r.usuarios_iduser\n" + //
            "    HAVING SUM(r.fechasalida - r.fechaentrada) >= 14\n" + //
            ")\n" + //
            "UNION\n" + //
            "SELECT\n" + //
            "    iduser,\n" + //
            "    nombreusuario,\n" + //
            "    'Su consumo total es mayor a 15.000.000' AS \"Información de buen Cliente\"\n" + //
            "FROM (\n" + //
            "    SELECT\n" + //
            "        u.iduser,\n" + //
            "        u.nombreusuario,\n" + //
            "        COALESCE(SUM(c.costo), 0) as total_consumos,\n" + //
            "        COALESCE(SUM(th.costopornoche * (r.fechasalida - r.fechaentrada)), 0) as costo_habitacion,\n" + //
            "        COALESCE(SUM(s.costo), 0) as costo_servicios\n" + //
            "    FROM usuarios u\n" + //
            "    LEFT JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
            "    LEFT JOIN habitaciones h ON r.habitaciones_numero = h.numero\n" + //
            "    LEFT JOIN tiposh th ON h.tiposh_nombreth = th.nombreth\n" + //
            "    LEFT JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva\n" + //
            "    LEFT JOIN servicios s ON rs.servicios_idservicio = s.idservicio\n" + //
            "    LEFT JOIN consumos c ON s.idservicio = c.servicios_idservicio\n" + //
            "    GROUP BY u.iduser, u.nombreusuario\n" + //
            ") TotalConsumos\n" + //
            "WHERE (total_consumos + costo_habitacion + costo_servicios) > 15000000\n" + //
            "ORDER BY iduser", nativeQuery = true)
    Collection<RespuestaRFC7> darBuenosClientes();


    @Query(value = "SELECT u.iduser, u.tipodocumento, u.correo, u.nombreusuario, COUNT(*) AS \"Numero_de_Consumos\"\n" + //
            "FROM usuarios u\n" + //
            "INNER JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
            "INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva\n" + //
            "INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio\n" + //
            "WHERE s.nombreservicio = :nombreServicio\n" + //
            "  AND r.fechaentrada >= TO_DATE( :fechaInicio , 'YYYY-MM-DD')\n" + //
            "  AND r.fechaentrada <= TO_DATE( :fechaFin , 'YYYY-MM-DD')\n" + //
            "GROUP BY u.iduser, u.tipodocumento, u.correo, u.nombreusuario\n" + //
            "ORDER BY \"Numero_de_Consumos\" DESC", nativeQuery = true)
    Collection<RespuestaRFC9> consultarConsumo(@Param("nombreServicio") String nombreServicio, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);


    @Query(value = "SELECT u.iduser, u.tipodocumento, u.correo, u.nombreusuario\n" + //
            "FROM usuarios u\n" + //
            "WHERE u.iduser NOT IN (\n" + //
            "    SELECT DISTINCT u.iduser\n" + //
            "    FROM usuarios u\n" + //
            "    INNER JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
            "    INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva\n" + //
            "    INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio\n" + //
            "    WHERE s.nombreservicio = :nombreServicio\n" + //
            "      AND r.fechaentrada >= TO_DATE( :fechaInicio , 'YYYY-MM-DD')\n" + //
            "      AND r.fechaentrada <= TO_DATE( :fechaFin , 'YYYY-MM-DD')\n" + //
            ")\n" + //
            "ORDER BY u.nombreusuario", nativeQuery = true)
    Collection<RespuestaRFC10> consultarConsumov2(@Param("nombreServicio") String nombreServicio, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);


    @Query(value = "WITH ReservasPorTrimestre AS (\n" + //
            "    SELECT u.iduser, u.nombreusuario,\n" + //
            "           TO_CHAR(r.fechaentrada, 'YYYY') AS Anio,\n" + //
            "           TO_CHAR(r.fechaentrada, 'Q') AS Trimestre\n" + //
            "    FROM usuarios u\n" + //
            "    INNER JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
            ")\n" + //
            "\n" + //
            "SELECT u.iduser, u.nombreusuario, u.tipodocumento, u.correo,\n" + //
            "       CASE\n" + //
            "           WHEN COUNT(DISTINCT (Anio || Trimestre)) >= 4 THEN 'Se ha alojado al menos una vez por trimestre'\n" + //
            "           ELSE 'No'\n" + //
            "       END AS \"Razon de ser un Excelente Cliente\"\n" + //
            "FROM usuarios u\n" + //
            "LEFT JOIN ReservasPorTrimestre rt ON u.iduser = rt.iduser\n" + //
            "GROUP BY u.iduser, u.nombreusuario, u.tipodocumento, u.correo\n" + //
            "HAVING CASE\n" + //
            "           WHEN COUNT(DISTINCT (Anio || Trimestre)) >= 4 THEN 'Se ha alojado al menos una vez por trimestre'\n" + //
            "           ELSE 'No'\n" + //
            "       END = 'Se ha alojado al menos una vez por trimestre'\n" + //
            "UNION\n" + //
            "SELECT DISTINCT u.iduser, u.nombreusuario, u.tipodocumento, u.correo,\n" + //
            "    CASE\n" + //
            "        WHEN (s.nombreservicio = 'SPA' OR (s.nombreservicio = 'Salones de Reuniones' AND rs.duracion > 4)) THEN 'Cliente Excelente (Reservas de SPA o Salones de Reuniones con Duración > 4 horas)'\n" + //
            "        WHEN s.costo >= 300000 THEN 'Cliente Excelente (Reservas con Costo Total >= 300,000)'\n" + //
            "    END AS \"Razon de ser un Excelente Cliente\"\n" + //
            "FROM usuarios u\n" + //
            "INNER JOIN reservas r ON u.iduser = r.usuarios_iduser\n" + //
            "INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva\n" + //
            "INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio\n" + //
            "WHERE (s.nombreservicio = 'SPA' OR (s.nombreservicio = 'Salones de Reuniones' AND rs.duracion > 4))\n" + //
            "OR s.costo >= 300000", nativeQuery = true)
    Collection<RespuestaRFC12> darClientesExcelentes();
}



