INSERT INTO tiposusuarios VALUES ('administrador');
INSERT INTO tiposusuarios VALUES ('cliente');
INSERT INTO tiposusuarios VALUES ('empleado');
INSERT INTO tiposusuarios VALUES ('gerente');
INSERT INTO tiposusuarios VALUES ('recepcionista');


INSERT INTO usuarios VALUES ('0000000001', 'CC', 'Natalia', 'natalia@uniandes.edu.co', 'administrador');
INSERT INTO usuarios VALUES ('0000000002', 'CC', 'Paulina', 'paulina@uniandes.edu.co', 'gerente');
INSERT INTO usuarios VALUES ('0000000003', 'CC', 'Enrique', 'enrique@uniandes.edu.co', 'recepcionista');
INSERT INTO usuarios VALUES ('0000000004', 'CC', 'Antonio', 'antonio@uniandes.edu.co', 'recepcionista');
INSERT INTO usuarios VALUES ('0000000005', 'CC', 'Esteban', 'esteban@uniandes.edu.co', 'cliente');
INSERT INTO usuarios VALUES ('0000000006', 'CC', 'Carlos', 'carlos@uniandes.edu.co', 'cliente');
INSERT INTO usuarios VALUES ('0000000007', 'CC', 'Mariantonia', 'mariantonia@uniandes.edu.co', 'cliente');
INSERT INTO usuarios VALUES ('0000000008', 'CC', 'Fernando', 'fernando@uniandes.edu.co', 'cliente');
INSERT INTO usuarios VALUES ('0000000009', 'CC', 'Cruz', 'cruz@uniandes.edu.co', 'empleado');
INSERT INTO usuarios VALUES ('0000000010', 'CC', 'Pablo', 'pablo@uniandes.edu.co', 'empleado');
INSERT INTO usuarios VALUES ('0000000011', 'CC', 'Maria', 'maria@uniandes.edu.co', 'empleado');
INSERT INTO usuarios VALUES ('0000000012', 'CC', 'Ana', 'ana@uniandes.edu.co', 'empleado');


INSERT INTO tiposh VALUES ('Familiar', 'Tiene 1 minifridge, 2 camas king, 2 banos, 1 televisor', 5, 50000);
INSERT INTO tiposh VALUES ('Suite', 'Tiene 1 minifridge, 1 cama king, 1 banos, 1 televisor, 1 jacuzzi', 2, 60000);
INSERT INTO tiposh VALUES ('SuitePresidencial', 'Tiene 1 minifridge, 1 cama king, 2 banos, 1 televisor, 1 jacuzzi, 1 tina', 2, 70000);
INSERT INTO tiposh VALUES ('Sencilla', 'Tiene 1 cama secilla, 1 bano, 1 televisor', 1, 30000);
INSERT INTO tiposh VALUES ('Doble', 'Tiene 1 cama doble, 1 bano, 1 televisor', 2, 40000);


INSERT INTO habitaciones VALUES (701, 'SuitePresidencial');
INSERT INTO habitaciones VALUES (702, 'SuitePresidencial');
INSERT INTO habitaciones VALUES (703, 'SuitePresidencial');
INSERT INTO habitaciones VALUES (704, 'SuitePresidencial');
INSERT INTO habitaciones VALUES (705, 'SuitePresidencial');
INSERT INTO habitaciones VALUES (601, 'Suite');
INSERT INTO habitaciones VALUES (602, 'Suite');
INSERT INTO habitaciones VALUES (603, 'Suite');
INSERT INTO habitaciones VALUES (604, 'Suite');
INSERT INTO habitaciones VALUES (605, 'Suite');
INSERT INTO habitaciones VALUES (501, 'Familiar');
INSERT INTO habitaciones VALUES (502, 'Familiar');
INSERT INTO habitaciones VALUES (503, 'Familiar');
INSERT INTO habitaciones VALUES (504, 'Familiar');
INSERT INTO habitaciones VALUES (505, 'Familiar');
INSERT INTO habitaciones VALUES (401, 'Doble');
INSERT INTO habitaciones VALUES (402, 'Doble');
INSERT INTO habitaciones VALUES (403, 'Doble');
INSERT INTO habitaciones VALUES (404, 'Doble');
INSERT INTO habitaciones VALUES (405, 'Doble');
INSERT INTO habitaciones VALUES (301, 'Sencilla');
INSERT INTO habitaciones VALUES (302, 'Sencilla');
INSERT INTO habitaciones VALUES (303, 'Sencilla');
INSERT INTO habitaciones VALUES (304, 'Sencilla');
INSERT INTO habitaciones VALUES (305, 'Sencilla');


INSERT INTO servicios VALUES (DEFAULT, 'Piscina', 'Piscina', '6:00 AM - 11:00 PM', 100, 5000, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Gimnacio', 'Gimnacio', '6:00 AM - 11:00 PM', 100, 2000, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Internet', 'Internet', '24 horas', 1000, 0, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Bar', 'Bar', 'Nocturno', 50, 0, 'Jugos Naturales, Pescados del Atlantico, Chontaduros Maduros');
INSERT INTO servicios VALUES (DEFAULT, 'Restaurante', 'Restaurante', 'Diurno', 50, 0, 'Jugos Naturales, Cortes de Carne de Res, Huevos Pochados');
INSERT INTO servicios VALUES (DEFAULT, 'Supermercado', 'Supermercado', '6:00 AM - 11:00 PM', 100, 0, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Tienda', 'Tienda', '6:00 AM - 11:00 PM', 20, 0, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Spa', 'Spa', '6:00 AM - 11:00 PM', 30, 150000, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Lavanderia', 'Lavanderia', '6:00 AM - 11:00 PM', 100, 40, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'Prestamo', 'Prestamo', '6:00 AM - 11:00 PM', 20, 1000, 'Computadores, Celulares, Consolas de Juegos');
INSERT INTO servicios VALUES (DEFAULT, 'SalonReuniones', 'SalonReuniones', '6:00 AM - 11:00 PM', 20, 3000, NULL);
INSERT INTO servicios VALUES (DEFAULT, 'SalonConferencias', 'SalonConferencias', '6:00 AM - 11:00 PM', 50, 5000, NULL);

INSERT INTO planes VALUES ('LargaEstadia', .10, TO_DATE('0001-01-01', 'YYYY-MM-DD'), TO_DATE('9999-12-31', 'YYYY-MM-DD'));
INSERT INTO planes VALUES ('TiempoCompartido', .5, TO_DATE('0001-01-01', 'YYYY-MM-DD'), TO_DATE('9999-12-31', 'YYYY-MM-DD'));
INSERT INTO planes VALUES ('TodoIncluido', .50, TO_DATE('0001-01-01', 'YYYY-MM-DD'), TO_DATE('9999-12-31', 'YYYY-MM-DD'));
INSERT INTO planes VALUES ('OneDolar', .99, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'));


INSERT INTO planesservicios VALUES (1, 'LargaEstadia');
INSERT INTO planesservicios VALUES (2, 'LargaEstadia');
INSERT INTO planesservicios VALUES (3, 'LargaEstadia');


INSERT INTO reservasservicios VALUES (8, '0000000005', TO_DATE('2023-01-01', 'YYYY-MM-DD'));
INSERT INTO reservasservicios VALUES (9, '0000000006', TO_DATE('2023-01-01', 'YYYY-MM-DD'));
INSERT INTO reservasservicios VALUES (10, '0000000006', TO_DATE('2023-01-01', 'YYYY-MM-DD'));


INSERT INTO reservas VALUES (DEFAULT, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 2, '0000000005', 'LargaEstadia', 701, NULL, NULL);
INSERT INTO reservas VALUES (DEFAULT, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 2, '0000000006', 'LargaEstadia', 703, NULL, NULL);


INSERT INTO consumos VALUES (DEFAULT, 'Carne muy cara en el restaurante', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 200, 5, 1);
INSERT INTO consumos VALUES (DEFAULT, 'Pescado muy caro en el bar', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 200, 4, 1);
