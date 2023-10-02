CREATE TABLE consumos (
    idconsumo            INTEGER GENERATED ALWAYS AS IDENTITY,
    descripcion          VARCHAR2(255 BYTE) NOT NULL,
    fecha                DATE NOT NULL,
    costo                NUMBER NOT NULL,
    servicios_idservicio INTEGER NOT NULL,
    reservas_idreserva   INTEGER NOT NULL
);

ALTER TABLE consumos ADD CONSTRAINT consumos_pk PRIMARY KEY ( idconsumo );

CREATE TABLE habitaciones (
    numero            INTEGER NOT NULL,
    tiposh_nombretipo VARCHAR2(255 BYTE) NOT NULL
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
    idreserva           INTEGER GENERATED ALWAYS AS IDENTITY,
    fechaentrada        DATE NOT NULL,
    fechasalida         DATE NOT NULL,
    cantidadpersonas    INTEGER NOT NULL,
    usuarios_cedula     INTEGER NOT NULL,
    planes_nombreplan   VARCHAR2(255 BYTE) NOT NULL,
    habitaciones_numero INTEGER NOT NULL,
    fechacheckin        DATE,
    fechacheckout       DATE
);


ALTER TABLE reservas ADD CONSTRAINT reservas_pk PRIMARY KEY ( idreserva );

CREATE TABLE reservasservicios (
    servicios_idservicio INTEGER NOT NULL,
    usuarios_cedula      INTEGER NOT NULL,
    fechareservaservicio DATE NOT NULL
);

ALTER TABLE reservasservicios ADD CONSTRAINT reservasservicios_pk PRIMARY KEY ( servicios_idservicio,
                                                                                usuarios_cedula );

CREATE TABLE servicios (
    idservicio     INTEGER GENERATED ALWAYS AS IDENTITY ,
    nombreservicio VARCHAR2(255 BYTE) NOT NULL,
    descripcion    VARCHAR2(255 BYTE) NOT NULL,
    horario        VARCHAR2(255 BYTE) NOT NULL,
    capacidad      INTEGER NOT NULL,
    costo          NUMBER(10, 3),
    menu           VARCHAR2(512 BYTE)
);

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( idservicio );

CREATE TABLE tiposh (
    nombretipo VARCHAR2(255 BYTE) DEFAULT 'Familiar' NOT NULL,
    dotacion   VARCHAR2(512 BYTE) NOT NULL,
    capacidad  INTEGER NOT NULL,
    costonoche NUMBER(8, 3) NOT NULL
);

ALTER TABLE tiposh
    ADD CHECK ( nombretipo IN ( 'Doble', 'Familiar', 'Sencilla', 'Suite', 'SuitePresidencial' ) );

ALTER TABLE tiposh ADD CONSTRAINT tiposh_pk PRIMARY KEY ( nombretipo );

CREATE TABLE tiposusuarios (
    nombre VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE tiposusuarios
    ADD CONSTRAINT tiposdeusuario CHECK ( nombre IN ( 'administrador', 'cliente', 'empleado', 'gerente', 'recepcionista' ) );

ALTER TABLE tiposusuarios ADD CONSTRAINT tiposusuarios_pk PRIMARY KEY ( nombre );

CREATE TABLE usuarios (
    cedula               INTEGER NOT NULL,
    tipodocumento        VARCHAR2(255 BYTE) DEFAULT 'CC' NOT NULL,
    nombreusuario        VARCHAR2(255 BYTE) NOT NULL,
    correo               VARCHAR2(255 BYTE),
    tiposusuarios_nombre VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE usuarios ADD constraint tiposdocumento
check(tipodocumento IN('CC', 'PASAPORTE', 'TI'));

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( cedula );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_reservas_fk FOREIGN KEY ( reservas_idreserva )
        REFERENCES reservas ( idreserva );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_servicios_fk FOREIGN KEY ( servicios_idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_tiposh_fk FOREIGN KEY ( tiposh_nombretipo )
        REFERENCES tiposh ( nombretipo );

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
    ADD CONSTRAINT reservas_usuarios_fk FOREIGN KEY ( usuarios_cedula )
        REFERENCES usuarios ( cedula );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_servicios_fk FOREIGN KEY ( servicios_idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE reservasservicios
    ADD CONSTRAINT reservasservicios_usuarios_fk FOREIGN KEY ( usuarios_cedula )
        REFERENCES usuarios ( cedula );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_tiposusuarios_fk FOREIGN KEY ( tiposusuarios_nombre )
        REFERENCES tiposusuarios ( nombre );
