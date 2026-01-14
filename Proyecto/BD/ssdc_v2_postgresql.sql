-- =========================
-- base de datos (opcional)
-- =========================
-- CREATE DATABASE ssdc_v2;
-- \c ssdc_v2;

-- =========================
-- tablas base
-- =========================
create table area_conocimiento (
    id_area integer generated always as identity primary key,
    nombre_area varchar(100) not null
);

create table facultad (
    id_facultad integer generated always as identity primary key,
    nombre_facultad varchar(100) not null,
    estado boolean not null
);

create table institucion (
    id_institucion integer generated always as identity primary key,
    nombre varchar(150) not null,
    direccion varchar(255) not null,
    correo varchar(100) not null,
    telefono varchar(30)
);

create table usuario (
    id_usuario integer generated always as identity primary key,
    usuario_bd varchar(100) not null,
    clave_bd text not null,
    usuario_app varchar(100) not null,
    clave_app text not null
);



create table carrera (
    id_carrera integer generated always as identity primary key,
    id_facultad integer not null,
    nombre_carrera varchar(50) not null,
    modalidad varchar(50) not null,
    estado boolean not null,
    constraint fk_carrera_facultad
        foreign key (id_facultad)
        references facultad (id_facultad)
);

create table materia (
    id_materia integer generated always as identity primary key,
    id_carrera integer not null,
    nombre_materia varchar(100) not null,
    estado boolean not null,
    nivel integer not null,
    constraint fk_materia_carrera
        foreign key (id_carrera)
        references carrera (id_carrera)
);




create table rol_autoridad (
    id_rol integer generated always as identity primary key,
    codigo varchar(30) not null unique,
    descripcion varchar(100) not null
);



create table autoridad_academica (
    id_autoridad integer generated always as identity primary key,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    correo varchar(100) not null,
    fecha_nacimiento date not null,
    estado boolean not null,
    id_usuario integer not null,
    id_institucion integer not null,
    id_rol integer not null,
    constraint fk_autoridad_usuario
        foreign key (id_usuario) references usuario (id_usuario),
    constraint fk_autoridad_institucion
        foreign key (id_institucion) references institucion (id_institucion),
    constraint fk_autoridad_rol
        foreign key (id_rol) references rol_autoridad (id_rol)
);



create table autoridad_facultad (
    id_autoridad integer not null,
    id_facultad integer not null,
    primary key (id_autoridad, id_facultad),
    constraint fk_af_autoridad
        foreign key (id_autoridad) references autoridad_academica (id_autoridad),
    constraint fk_af_facultad
        foreign key (id_facultad) references facultad (id_facultad)
);



create table autoridad_carrera (
    id_autoridad integer not null,
    id_carrera integer not null,
    primary key (id_autoridad, id_carrera),
    constraint fk_ac_autoridad
        foreign key (id_autoridad) references autoridad_academica (id_autoridad),
    constraint fk_ac_carrera
        foreign key (id_carrera) references carrera (id_carrera)
);



create table postulante (
    id_postulante integer generated always as identity primary key,
    nombres_postulante varchar(50) not null,
    apellidos_postulante varchar(50) not null,
    identificacion varchar(20) not null unique,
    correo_postulante varchar(100) not null,
    telefono_postulante varchar(30) not null,
    fecha_nacimiento date not null,
    id_usuario integer not null,
    constraint fk_postulante_usuario
        foreign key (id_usuario) references usuario (id_usuario)
);

create table solicitud_docente (
    id_solicitud integer generated always as identity primary key,
    id_autoridad integer not null,
    fecha_solicitud date not null,
    estado_solicitud varchar(50) not null,
    justificacion varchar(255) not null,
    cantidad_docentes integer not null,
    constraint fk_solicitud_autoridad
        foreign key (id_autoridad) references autoridad_academica (id_autoridad)
);



create table perfil_docente (
    id_perfil integer generated always as identity primary key,
    id_solicitud integer not null,
    nivel_academico varchar(100) not null,
    observaciones varchar(255),
    id_area integer not null,
    experiencia_profesional_min integer not null,
    experiencia_docente_min integer not null,
    constraint fk_perfil_solicitud
        foreign key (id_solicitud) references solicitud_docente (id_solicitud),
    constraint fk_perfil_area
        foreign key (id_area) references area_conocimiento (id_area)
);

create table convocatoria (
    id_convocatoria integer generated always as identity primary key,
    id_solicitud integer not null,
    titulo varchar(100) not null,
    descripcion varchar(255) not null,
    numero_vacantes integer not null,
    fecha_publicacion date not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    estado_convocatoria varchar(50) not null,
    constraint fk_convocatoria_solicitud
        foreign key (id_solicitud) references solicitud_docente (id_solicitud)
);

create table convocatoria_materia (
    id_convocatoria_materia integer generated always as identity primary key,
    id_convocatoria integer not null,
    id_materia integer not null,
    constraint fk_cm_convocatoria
        foreign key (id_convocatoria) references convocatoria (id_convocatoria),
    constraint fk_cm_materia
        foreign key (id_materia) references materia (id_materia)
);





create table postulacion (
    id_postulacion integer generated always as identity primary key,
    id_postulante integer not null,
    id_convocatoria integer not null,
    fecha timestamp not null,
    estado_postulacion varchar(50) not null default 'pendiente',
    constraint fk_postulacion_postulante
        foreign key (id_postulante) references postulante (id_postulante),
    constraint fk_postulacion_convocatoria
        foreign key (id_convocatoria) references convocatoria (id_convocatoria)
);

create table tipo_documento (
    id_tipo_documento integer generated always as identity primary key,
    nombre varchar(50) not null,
    obligatorio boolean not null,
    estado boolean not null
);

create table documento (
    id_documento integer generated always as identity primary key,
    id_postulacion integer not null,
    id_tipo_documento integer not null,
    fecha_carga timestamp not null,
    estado_validacion varchar(50) not null,
    ruta_archivo text not null,
    constraint fk_documento_postulacion
        foreign key (id_postulacion) references postulacion (id_postulacion),
    constraint fk_documento_tipo
        foreign key (id_tipo_documento) references tipo_documento (id_tipo_documento)
);





create table criterio_evaluacion (
    id_criterio integer generated always as identity primary key,
    nombre varchar(100) not null,
    puntaje_maximo numeric(5,2) not null,
    estado boolean not null
);

create table evaluacion_meritos (
    id_evaluacion integer generated always as identity primary key,
    id_postulacion integer not null,
    id_decano integer not null,
    id_coordinador integer not null,
    puntaje numeric(5,2),
    observaciones varchar(255),
    fecha_evaluacion date,
    constraint fk_eval_postulacion
        foreign key (id_postulacion) references postulacion (id_postulacion),
    constraint fk_eval_decano
        foreign key (id_decano) references autoridad_academica (id_autoridad),
    constraint fk_eval_coordinador
        foreign key (id_coordinador) references autoridad_academica (id_autoridad)
);

create table calificacion_evaluacion (
    id_calificacion integer generated always as identity primary key,
    id_evaluacion integer not null,
    id_criterio integer not null,
    puntaje_obtenido numeric(5,2) not null,
    observaciones varchar(255),
    constraint fk_calificacion_evaluacion
        foreign key (id_evaluacion) references evaluacion_meritos (id_evaluacion),
    constraint fk_calificacion_criterio
        foreign key (id_criterio) references criterio_evaluacion (id_criterio)
);

create table entrevista (
    id_entrevista integer generated always as identity primary key,
    id_postulacion integer not null,
    id_decano integer not null,
    id_coordinador integer not null,
    fecha_entrevista timestamp,
    puntaje numeric(5,2),
    observaciones varchar(255),
    constraint fk_entrevista_postulacion
        foreign key (id_postulacion) references postulacion (id_postulacion),
    constraint fk_entrevista_decano
        foreign key (id_decano) references autoridad_academica (id_autoridad),
    constraint fk_entrevista_coordinador
        foreign key (id_coordinador) references autoridad_academica (id_autoridad)
);

create table informe_final (
    id_informe integer generated always as identity primary key,
    id_postulacion integer not null,
    fecha_emision timestamp not null,
    resultado_final varchar(50) not null,
    observaciones varchar(255) not null,
    constraint fk_informe_postulacion
        foreign key (id_postulacion) references postulacion (id_postulacion)
);

create table resolucion (
    id_resolucion integer generated always as identity primary key,
    id_solicitud integer not null,
    id_aprobador integer not null,
    numero_resolucion varchar(50) not null,
    fecha_emision date not null,
    observaciones varchar(255),
    estado_solicitud varchar(50),
    constraint fk_resolucion_solicitud
        foreign key (id_solicitud) references solicitud_docente (id_solicitud),
    constraint fk_resolucion_aprobador
        foreign key (id_aprobador) references autoridad_academica (id_autoridad)
);

create table resultados_ia (
    id_resultado_ia integer generated always as identity primary key,
    id_documento integer not null,
    resultado varchar(50) not null,
    observaciones varchar(255),
    fecha_revision timestamp not null,
    constraint fk_resultado_documento
        foreign key (id_documento) references documento (id_documento)
);
