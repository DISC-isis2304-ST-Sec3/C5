CREATE TABLE consumos (
    idconsumo            INTEGER NOT NULL,
    descripcion          VARCHAR2(255 BYTE) NOT NULL,
    fecha                DATE NOT NULL,
    costo                NUMBER NOT NULL,
    servicios_idservicio INTEGER NOT NULL
);

ALTER TABLE consumos ADD CONSTRAINT consumos_pk PRIMARY KEY ( idconsumo );

CREATE TABLE empleados (
    idempleado             INTEGER NOT NULL,
    tiposusuarios_tipouser VARCHAR2(255 BYTE) DEFAULT 'Empleado' NOT NULL
);

ALTER TABLE empleados
    ADD CHECK ( tiposusuarios_tipouser IN ( 'Administrador', 'Empleado', 'Gerente', 'Recepcionista' ) );

ALTER TABLE empleados ADD CONSTRAINT empleados_pk PRIMARY KEY ( idempleado );

CREATE TABLE habitaciones (
    numero          INTEGER NOT NULL,
    tiposh_nombreth VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( numero );

CREATE TABLE planes (
    nombreplan             VARCHAR2(255 BYTE) NOT NULL,
    descuento              NUMBER(2, 2) NOT NULL,
    periodovigenciainicial DATE NOT NULL,
    periodovigenciafinal   DATE NOT NULL
);

ALTER TABLE planes ADD CONSTRAINT planes_pk PRIMARY KEY ( nombreplan );

CREATE TABLE planesservicios (
    servicios_idservicio INTEGER NOT NULL,
    planes_nombreplan    VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE planesservicios ADD CONSTRAINT planesservicios_pk PRIMARY KEY ( servicios_idservicio,
                                                                            planes_nombreplan );

CREATE TABLE reservas (
    idreserva           INTEGER NOT NULL,
    fechaentrada        DATE NOT NULL,
    fechasalida         DATE NOT NULL,
    cantidadpersonas    INTEGER NOT NULL,
    habitaciones_numero INTEGER NOT NULL,
    planes_nombreplan   VARCHAR2(255 BYTE) NOT NULL,
    usuarios_iduser     INTEGER NOT NULL
);

CREATE UNIQUE INDEX reservas__idx ON
    reservas (
        habitaciones_numero
    ASC );

ALTER TABLE reservas ADD CONSTRAINT reservas_pk PRIMARY KEY ( idreserva );

CREATE TABLE reservasservicios (
    idreservas           INTEGER NOT NULL,
    fecha                DATE NOT NULL,
    duracion             INTEGER NOT NULL,
    servicios_idservicio INTEGER NOT NULL,
    reservas_idreserva   INTEGER NOT NULL,
    empleados_idempleado INTEGER NOT NULL
);

ALTER TABLE reservasservicios ADD CONSTRAINT reservasservicios_pk PRIMARY KEY ( idreservas );

CREATE TABLE servicios (
    idservicio     INTEGER NOT NULL,
    nombreservicio VARCHAR2(255 BYTE) NOT NULL,
    descripcion    VARCHAR2(255 BYTE) NOT NULL,
    horario        VARCHAR2(255 BYTE) NOT NULL,
    capacidad      INTEGER NOT NULL,
    costo          NUMBER(7, 3) NOT NULL
);

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( idservicio );

CREATE TABLE tiposh (
    nombreth      VARCHAR2(255 BYTE) DEFAULT 'Familiar' NOT NULL,
    dotacion      VARCHAR2(255 BYTE) NOT NULL,
    capacidad     INTEGER NOT NULL,
    costopornoche NUMBER NOT NULL
);

ALTER TABLE tiposh
    ADD CHECK ( nombreth IN ( 'Doble', 'Familiar', 'Sencilla', 'Suite', 'SuitePresidencial' ) );

ALTER TABLE tiposh ADD CONSTRAINT tiposh_pk PRIMARY KEY ( nombreth );

CREATE TABLE tiposusuarios (
    tipouser VARCHAR2(255 BYTE) DEFAULT 'Cliente' NOT NULL
);

ALTER TABLE tiposusuarios
    ADD CHECK ( tipouser IN ( 'Administrador', 'Cliente', 'Empleado', 'Gerente', 'Recepcionista' ) );

ALTER TABLE tiposusuarios ADD CONSTRAINT tiposusuarios_pk PRIMARY KEY ( tipouser );

CREATE TABLE usuarios (
    iduser                 INTEGER NOT NULL,
    tipodocumento          VARCHAR2(255 BYTE) DEFAULT 'CC' NOT NULL,
    nombreusuario          VARCHAR2(255 BYTE) NOT NULL,
    correo                 VARCHAR2(255 BYTE),
    tiposusuarios_tipouser VARCHAR2(255 BYTE) DEFAULT 'Cliente' NOT NULL
);

ALTER TABLE usuarios ADD constraint tiposdocumento

check(tipodocumento IN('CC', 'PASAPORTE', 'TI'));

ALTER TABLE usuarios ADD CHECK ( tiposusuarios_tipouser IN ( 'Cliente' ) );

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( iduser );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_servicios_fk FOREIGN KEY ( servicios_idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE empleados
    ADD CONSTRAINT empleados_tiposusuarios_fk FOREIGN KEY ( tiposusuarios_tipouser )
        REFERENCES tiposusuarios ( tipouser );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_tiposh_fk FOREIGN KEY ( tiposh_nombreth )
        REFERENCES tiposh ( nombreth );

ALTER TABLE planesservicios
    ADD CONSTRAINT planesservicios_planes_fk FOREIGN KEY ( planes_nombreplan )
        REFERENCES planes ( nombreplan );

ALTER TABLE planesservicios
    ADD CONSTRAINT planesservicios_servicios_fk FOREIGN KEY ( servicios_idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE reservas
    ADD CONSTRAINT reservas_habitaciones_fk FOREIGN KEY ( habitaciones_numero )
        REFERENCES habitaciones ( numero );

ALTER TABLE reservas
    ADD CONSTRAINT reservas_planes_fk FOREIGN KEY ( planes_nombreplan )
        REFERENCES planes ( nombreplan );

ALTER TABLE reservas
    ADD CONSTRAINT reservas_usuarios_fk FOREIGN KEY ( usuarios_iduser )
        REFERENCES usuarios ( iduser );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_empleados_fk FOREIGN KEY ( empleados_idempleado )
        REFERENCES empleados ( idempleado );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_reservas_fk FOREIGN KEY ( reservas_idreserva )
        REFERENCES reservas ( idreserva );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_servicios_fk FOREIGN KEY ( servicios_idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_tiposusuarios_fk FOREIGN KEY ( tiposusuarios_tipouser )
        REFERENCES tiposusuarios ( tipouser );
   
   
   
//INDICES DE CREACION PROPIA

CREATE INDEX costo_servicio ON
SERVICIOS(costo); 

CREATE INDEX fecha_reserva_servicio ON 
RESERVASSERVICIOS(fecha);

CREATE INDEX reserva_id_user ON
RESERVAS(usuarios_iduser);

CREATE INDEX checkIn ON
RESERVAS(fechaentrada);

CREATE INDEX checkOut ON
RESERVAS(fechasalida);