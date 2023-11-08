//RF1 Completada
SELECT h.numero AS Numero_Habitacion, s.nombreservicio AS Servicio, 
       SUM(s.costo) AS Dinero_Recolectado
FROM habitaciones h 
INNER JOIN reservas r ON h.numero = r.habitaciones_numero
INNER JOIN reservasservicios rss ON r.idreserva = rss.reservas_idreserva
INNER JOIN servicios s ON s.idservicio = rss.servicios_idservicio
WHERE r.fechaentrada >= TRUNC(SYSDATE, 'YYYY') - INTERVAL '1' YEAR
GROUP BY h.numero, s.nombreservicio
ORDER BY h.numero, s.nombreservicio;

//RF2 Completada
SELECT s.nombreservicio, COUNT(rs.servicios_idservicio) AS total_consumos
FROM servicios s
LEFT JOIN reservasservicios rs ON s.idservicio = rs.servicios_idservicio
WHERE rs.fecha BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-11-02', 'YYYY-MM-DD')
GROUP BY s.nombreservicio
ORDER BY total_consumos DESC
FETCH FIRST 20 ROWS ONLY;

//RF3 Completada
SELECT h.numero AS numero_habitacion, 
       ROUND((COUNT(r.idreserva) / 365) * 100, 2) AS ocupacion_porcentaje
FROM habitaciones h
LEFT JOIN reservas r ON h.numero = r.habitaciones_numero
WHERE r.fechasalida >= (SYSDATE - 365)
GROUP BY h.numero
ORDER BY h.numero;

//RF4 
SELECT s.nombreservicio, rs.fecha, rs.duracion, s.costo
FROM servicios s
JOIN reservasservicios rs ON s.idservicio = rs.servicios_idservicio
WHERE s.costo BETWEEN 0 AND 16000000
AND rs.fecha BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-11-05', 'YYYY-MM-DD')
AND s.nombreservicio = 'Gimnasio';


//RF5 Completado
SELECT u.iduser, u.nombreusuario, SUM(c.costo) AS "Total_Gastado_En_Consumos"
FROM usuarios u
LEFT JOIN reservas r ON u.iduser = r.usuarios_iduser
LEFT JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva
LEFT JOIN servicios s ON rs.servicios_idservicio = s.idservicio
LEFT JOIN consumos c ON s.idservicio = c.servicios_idservicio
WHERE u.nombreusuario = 'Esteban'
  AND c.fecha >= TO_DATE('2023-01-01', 'YYYY-MM-DD')
  AND c.fecha <= TO_DATE('2023-11-05', 'YYYY-MM-DD') -- Cambia la fecha final si es diferente
GROUP BY u.iduser, u.nombreusuario
ORDER BY "Total_Gastado_En_Consumos" DESC;

//RF6 Completado
WITH Ocupacion AS (
    SELECT fechaentrada AS fecha_ocupacion, COUNT(*) AS cantidad_ocupaciones
    FROM reservas
    GROUP BY fechaentrada
    ORDER BY cantidad_ocupaciones DESC
    FETCH FIRST 1 ROW ONLY
),
Ingresos AS (
    SELECT fecha AS fecha_consumo, SUM(costo) AS ingresos_totales
    FROM consumos
    GROUP BY fecha
    ORDER BY ingresos_totales DESC
    FETCH FIRST 1 ROW ONLY
),
Demanda AS (
    SELECT fechaentrada AS fecha_demanda, COUNT(*) AS cantidad_ocupaciones
    FROM reservas
    GROUP BY fechaentrada
    ORDER BY cantidad_ocupaciones
    FETCH FIRST 1 ROW ONLY
)
SELECT
    Ocupacion.fecha_ocupacion AS fecha_maxima_ocupacion,
    Ingresos.fecha_consumo AS fecha_maximos_ingresos,
    Demanda.fecha_demanda AS fecha_minima_demanda
FROM Ocupacion, Ingresos, Demanda;

//RF7
SELECT iduser, nombreusuario, 'Ha estado en el hotel por al menos dos semanas' AS "Información de buen Cliente"
FROM usuarios u
WHERE EXISTS (
    SELECT 1
    FROM reservas r
    WHERE r.usuarios_iduser = u.iduser
      AND r.fechasalida >= TO_DATE('2022-11-05', 'YYYY-MM-DD') - INTERVAL '1' YEAR
    GROUP BY r.usuarios_iduser
    HAVING SUM(r.fechasalida - r.fechaentrada) >= 14
)
UNION
SELECT
    iduser,
    nombreusuario,
    'Su consumo total es mayor a 15.000.000' AS "Información de buen Cliente"
FROM (
    SELECT
        u.iduser,
        u.nombreusuario,
        COALESCE(SUM(c.costo), 0) as total_consumos,
        COALESCE(SUM(th.costopornoche * (r.fechasalida - r.fechaentrada)), 0) as costo_habitacion,
        COALESCE(SUM(s.costo), 0) as costo_servicios
    FROM usuarios u
    LEFT JOIN reservas r ON u.iduser = r.usuarios_iduser
    LEFT JOIN habitaciones h ON r.habitaciones_numero = h.numero
    LEFT JOIN tiposh th ON h.tiposh_nombreth = th.nombreth
    LEFT JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva
    LEFT JOIN servicios s ON rs.servicios_idservicio = s.idservicio
    LEFT JOIN consumos c ON s.idservicio = c.servicios_idservicio
    GROUP BY u.iduser, u.nombreusuario
) TotalConsumos
WHERE (total_consumos + costo_habitacion + costo_servicios) > 15000000
ORDER BY iduser;


//RF8 Completada
WITH ServiciosSolicitados AS (
    SELECT
        s.idservicio,
        s.nombreservicio,
        COUNT(*) AS solicitudes_semanales
    FROM
        servicios s
    JOIN
        reservasservicios rs ON s.idservicio = rs.servicios_idservicio
    WHERE
        rs.fecha >= (SYSDATE - 365) 
    GROUP BY
        s.idservicio, s.nombreservicio
)
SELECT
    ss.idservicio,
    ss.nombreservicio,
    ss.solicitudes_semanales
FROM
    ServiciosSolicitados ss
WHERE
    ss.solicitudes_semanales < 3;

//RF9
SELECT u.iduser, u.tipodocumento, u.correo, u.nombreusuario, COUNT(*) AS "Numero_de_Consumos"
FROM usuarios u
INNER JOIN reservas r ON u.iduser = r.usuarios_iduser
INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva
INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio
WHERE s.nombreservicio = 'SPA'
  AND r.fechaentrada >= TO_DATE('2023-01-01', 'YYYY-MM-DD')
  AND r.fechaentrada <= TO_DATE('2023-11-04', 'YYYY-MM-DD')
GROUP BY u.iduser, u.tipodocumento, u.correo, u.nombreusuario
ORDER BY "Numero_de_Consumos" DESC;

//RF10 Completado
SELECT u.iduser, u.tipodocumento, u.correo, u.nombreusuario
FROM usuarios u
WHERE u.iduser NOT IN (
    SELECT DISTINCT u.iduser
    FROM usuarios u
    INNER JOIN reservas r ON u.iduser = r.usuarios_iduser
    INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva
    INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio
    WHERE s.nombreservicio = 'Piscina'
      AND r.fechaentrada >= TO_DATE('2023-01-01', 'YYYY-MM-DD')
      AND r.fechaentrada <= TO_DATE('2023-11-04', 'YYYY-MM-DD')
)
ORDER BY u.nombreusuario;

//RF11


//RF12 Completado
WITH ReservasPorTrimestre AS (
    SELECT u.iduser, u.nombreusuario,
           TO_CHAR(r.fechaentrada, 'YYYY') AS Anio,
           TO_CHAR(r.fechaentrada, 'Q') AS Trimestre
    FROM usuarios u
    INNER JOIN reservas r ON u.iduser = r.usuarios_iduser
)

SELECT u.iduser, u.nombreusuario, u.tipodocumento, u.correo,
       CASE
           WHEN COUNT(DISTINCT (Anio || Trimestre)) >= 4 THEN 'Se ha alojado al menos una vez por trimestre'
           ELSE 'No'
       END AS "Razon de ser un Excelente Cliente"
FROM usuarios u
LEFT JOIN ReservasPorTrimestre rt ON u.iduser = rt.iduser
GROUP BY u.iduser, u.nombreusuario, u.tipodocumento, u.correo
HAVING CASE
           WHEN COUNT(DISTINCT (Anio || Trimestre)) >= 4 THEN 'Se ha alojado al menos una vez por trimestre'
           ELSE 'No'
       END = 'Se ha alojado al menos una vez por trimestre'
UNION
SELECT DISTINCT u.iduser, u.nombreusuario, u.tipodocumento, u.correo,
    CASE
        WHEN (s.nombreservicio = 'SPA' OR (s.nombreservicio = 'Salones de Reuniones' AND rs.duracion > 4)) THEN 'Cliente Excelente (Reservas de SPA o Salones de Reuniones con Duración > 4 horas)'
        WHEN s.costo >= 300000 THEN 'Cliente Excelente (Reservas con Costo Total >= 300,000)'
    END AS "Razon de ser un Excelente Cliente"
FROM usuarios u
INNER JOIN reservas r ON u.iduser = r.usuarios_iduser
INNER JOIN reservasservicios rs ON r.idreserva = rs.reservas_idreserva
INNER JOIN servicios s ON rs.servicios_idservicio = s.idservicio
WHERE (s.nombreservicio = 'SPA' OR (s.nombreservicio = 'Salones de Reuniones' AND rs.duracion > 4))
OR s.costo >= 300000;