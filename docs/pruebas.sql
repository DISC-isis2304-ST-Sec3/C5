-- Las tuplas que se deberian insertar exitosamente fueron probadas
-- exitosamente en el script de insercion de datos

-- Por lo tanto las sentencias que deben pasar estan en otro script
-- y las sentencias que deberian fallar se encuentran a continuacion

-- Vale la pena aclarar que es necesario correr todas las sentencias
-- del otro script para poder correr estas o sino puede que algunas
-- de estas sentencias corran exitosamente




-- Pruebas unicidad de tuplas

-- Tabla tiposusuarios
INSERT INTO tiposusuarios VALUES ('administrador');

-- Tabla usuarios
INSERT INTO usuarios VALUES ('0000000005', 'CC', 'Esteban', 'esteban@uniandes.edu.co', 'cliente');

-- Tabla tiposh
INSERT INTO tiposh VALUES ('Familiar', 'Tiene 1 minifridge, 2 camas king, 2 banos, 1 televisor', 5, 50000);

-- Tabla habitaciones
INSERT INTO habitaciones VALUES (701, 'SuitePresidencial');

-- Tabla servicios
INSERT INTO servicios VALUES (1, 'Piscina', 'Piscina', '6:00 AM - 11:00 PM', 100, 5000, NULL);

-- Tabla planes
INSERT INTO planes VALUES ('LargaEstadia', .10, TO_DATE('0001-01-01', 'YYYY-MM-DD'), TO_DATE('9999-12-31', 'YYYY-MM-DD'));

-- Tabla consumos
INSERT INTO consumos VALUES (1, 'xxxxxxx', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 1, 1, 1);

-- Tabla planesservicios
INSERT INTO planesservicios VALUES (1, 'LargaEstadia');

-- Tabla reservas
INSERT INTO reservas VALUES (1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 1, '0000000001', 'LargaEstadia', 301, NULL, NULL);

-- Tabla reservasservicios
INSERT INTO reservasservicios VALUES (8, '0000000005', TO_DATE('2023-01-01', 'YYYY-MM-DD'));




-- Prubas de integridad con FK

-- Tabla usuarios
INSERT INTO usuarios VALUES ('0000000005', 'CC', 'Esteban', 'esteban@uniandes.edu.co', 'cliente');

-- Tabla consumos
INSERT INTO consumos VALUES (1, 'xxxxxxx', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 1, 1, 1);

-- Tabla planesservicios
INSERT INTO planesservicios VALUES (1, 'LargaEstadia');

-- Tabla reservas
INSERT INTO reservas VALUES (1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 1, '0000000001', 'LargaEstadia', 301, NULL, NULL);

-- Tabla reservasservicios
INSERT INTO reservasservicios VALUES (8, '0000000005', TO_DATE('2023-01-01', 'YYYY-MM-DD'));




-- Pruebas de integridad de acuerdo con restricciones de chequeo

-- Tabla planes
INSERT INTO planes VALUES ('OneDolar', 100000, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'));

-- Tabla servicios
INSERT INTO servicios VALUES (1, 'Piscina', 'Piscina', '6:00 AM - 11:00 PM', 100, 50000000000000, NULL);
