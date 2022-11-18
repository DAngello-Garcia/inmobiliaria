create table propiedad
(
    id serial primary key unique,
    direccion varchar(255) not null,
    disponible boolean default TRUE,
    precio decimal not null,
    fecha_creacion timestamp,
    area decimal not null,
    unidades_area varchar(10) not null,
    disposicion_propiedad varchar(55) default 'VENTA',
    tipo_propiedad varchar(55) not null
);

create table vivienda
(
    id serial primary key unique,
    numero_habitaciones integer,
    numero_banos integer,
    material varchar(255),
    id_propiedad integer not null
        constraint vivenda_propiedad_id_fk
            references propiedad
);

create table apartamento
(
    id varchar(55) not null primary key unique,
    balcon boolean,
    ascensor boolean,
    piso integer,
    valor_administracion decimal,
    numero_parqueaderos integer,
    id_vivienda integer not null
        constraint apartamento_vivenda_id_fk
            references vivienda
);

create table casa
(
    id varchar(55) not null primary key unique,
    numero_pisos integer,
    id_vivienda integer not null
        constraint casa_vivenda_id_fk
            references vivienda
);

create table chalet
(
    id varchar(55) not null primary key unique,
    agua_potable boolean,
    alcantarillado boolean,
    pozo_septico boolean,
    internet boolean,
    energia_electrica boolean,
    gas_domiciliario boolean,
    id_casa varchar(55) not null
        constraint chalet_casa_id_fk
            references casa
);

create table edificio
(
    id varchar(55) not null primary key unique,
    numero_pisos integer,
    id_propiedad integer not null
        constraint edificio_propiedad_id_fk
            references propiedad
);

create table parqueadero
(
    id varchar(55) not null primary key unique,
    id_propiedad integer not null
        constraint parqueadero_propiedad_id_fk
            references propiedad
);

create table lote
(
    id varchar(55) not null primary key unique,
    tipo varchar(55),
    id_propiedad integer not null
        constraint lote_propiedad_id_fk
            references propiedad
);

create table bodega
(
    id varchar(55) not null primary key unique,
    tipo varchar(55),
    id_propiedad integer not null
        constraint bodega_propiedad_id_fk
            references propiedad
);

create table rol
(
    id serial primary key unique,
    tipo_rol varchar(255) not null unique
);

create table usuario
(
    id serial primary key unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    estado boolean default TRUE,
    frase varchar(255) not null,
    id_rol integer not null
        constraint usuario_rol_id_fk
            references rol
);
-- --------------------------------------------------------------
create table empleado
(
    id integer not null primary key unique,
    nombre  varchar(255) not null,
    celular varchar(55)  not null,
    id_usuario integer not null
        constraint empleado_usuario_id_fk
            references usuario
);

create table propietario
(
    id integer not null primary key unique,
    nombre  varchar(255) not null,
    celular varchar(55)  not null
);

create table cliente
(
    id integer not null primary key unique,
    nombre  varchar(255) not null,
    celular varchar(55)  not null
);
-- --------------------------------------------------------------

create table historial_propiedad
(
    id serial primary key unique,
    fecha_modificacion timestamp,
    id_propiedad integer not null
        constraint historial_propiedad_propiedad_id_fk
            references propiedad,
    id_empleado integer not null
        constraint historial_propiedad_empleado_id_fk
            references empleado,
    id_propietario integer
        constraint historial_propiedad_propietario_id_fk
            references propietario,
    id_cliente integer
        constraint historial_propiedad_cliente_id_fk
            references cliente
);


insert into rol (tipo_rol)
values ('Admin');

insert into rol (tipo_rol)
values ('Empleado');

insert into usuario (email, password, id_rol, frase)
VALUES ('admin@inmo.com', 'password_admin', 1, 'frase_seguridad');