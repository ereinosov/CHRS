-- ROLES RESUELTO

-- roles de usuario
CREATE TABLE public.rol_usuario (
  id_rol bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre text NOT NULL UNIQUE
    CHECK (nombre = ANY (ARRAY[
      'ROLE_ADMIN'::character varying::text,
      'ROLE_EVALUATOR'::character varying::text,
	  'ROLE_USER'::character varying::text
    ])),
  CONSTRAINT rol_usuario_pkey PRIMARY KEY (id_rol)
);

-- roles de autoridades
CREATE TABLE public.rol_autoridad (
  id_rol bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre text NOT NULL UNIQUE
    CHECK (nombre = ANY (ARRAY[
      'MIEMBRO DE VICERECTORADO ACADEMICO'::character varying::text,
      'MIEMBRO DEL CONSEJO ACADEMICO'::character varying::text,
	  'DECANATO DE LA FACULTAD'::character varying::text,
      'COORDINADOR DE CARRERA'::character varying::text
    ])),
  CONSTRAINT roles_aut_pkey PRIMARY KEY (id_rol)
);


-- usuario y autoridad
CREATE TABLE public.usuario (
  id_usuario bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  usuario_bd character varying NOT NULL,
  clave_bd character varying NOT NULL,
  usuario_app character varying NOT NULL,
  clave_app character varying NOT NULL,
  activo boolean NOT NULL DEFAULT true,
  CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);
CREATE TABLE public.institucion (
  id_institucion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre character varying NOT NULL,
  direccion character varying NOT NULL,
  correo character varying NOT NULL,
  telefono character varying,
  CONSTRAINT institucion_pkey PRIMARY KEY (id_institucion)
);

CREATE TABLE public.autoridad_academica (
  id_autoridad bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombres character varying NOT NULL,
  apellidos character varying NOT NULL,
  correo character varying NOT NULL,
  fecha_nacimiento date NOT NULL,
  estado boolean NOT NULL DEFAULT true,
  id_usuario bigint NOT NULL,
  id_institucion bigint NOT NULL,
  CONSTRAINT autoridad_academica_pkey PRIMARY KEY (id_autoridad),
  CONSTRAINT fk_autoridad_usuario FOREIGN KEY (id_usuario)
    REFERENCES public.usuario(id_usuario) ON DELETE CASCADE,
  CONSTRAINT fk_autoridad_institucion FOREIGN KEY (id_institucion)
    REFERENCES public.institucion(id_institucion) ON DELETE RESTRICT
);

-- los usuarios pueden tener mas roles
CREATE TABLE public.usuario_rol (
  id_usuario bigint NOT NULL,
  id_rol bigint NOT NULL,
  CONSTRAINT usuario_rol_pkey PRIMARY KEY (id_usuario, id_rol),
  CONSTRAINT fk_usuario_rol_usuario FOREIGN KEY (id_usuario)
    REFERENCES public.usuario(id_usuario) ON DELETE CASCADE,
  CONSTRAINT fk_usuario_rol_rol FOREIGN KEY (id_rol)
    REFERENCES public.rol_usuario(id_rol) ON DELETE CASCADE
);



CREATE TABLE public.autoridad_rol (
  id_autoridad bigint NOT NULL,
  id_rol bigint NOT NULL,
  CONSTRAINT autoridad_rol_pkey PRIMARY KEY (id_autoridad, id_rol),
  CONSTRAINT fk_autoridad_rol_autoridad FOREIGN KEY (id_autoridad)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE CASCADE,
  CONSTRAINT fk_autoridad_rol_rol FOREIGN KEY (id_rol)
    REFERENCES public.rol_autoridad(id_rol) ON DELETE CASCADE
);




-- Cambios de postulacion
-- Área de conocimiento
CREATE TABLE public.area_conocimiento (
  id_area bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre_area character varying NOT NULL,
  CONSTRAINT area_conocimiento_pkey PRIMARY KEY (id_area)
);

-- Facultad
CREATE TABLE public.facultad (
  id_facultad bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre_facultad character varying NOT NULL,
  estado boolean NOT NULL,
  CONSTRAINT facultad_pkey PRIMARY KEY (id_facultad)
);

-- Carrera
CREATE TABLE public.carrera (
  id_carrera bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_facultad bigint NOT NULL,
  nombre_carrera character varying NOT NULL,
  modalidad character varying NOT NULL,
  estado boolean NOT NULL,
  CONSTRAINT carrera_pkey PRIMARY KEY (id_carrera),
  CONSTRAINT fk_carrera_facultad FOREIGN KEY (id_facultad)
    REFERENCES public.facultad(id_facultad) ON DELETE RESTRICT
);

-- Materia
CREATE TABLE public.materia (
  id_materia bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_carrera bigint NOT NULL,
  nombre_materia character varying NOT NULL,
  nivel bigint NOT NULL,
  CONSTRAINT materia_pkey PRIMARY KEY (id_materia),
  CONSTRAINT fk_materia_carrera FOREIGN KEY (id_carrera)
    REFERENCES public.carrera(id_carrera) ON DELETE RESTRICT
);

-- Relación autoridad-facultad
CREATE TABLE public.autoridad_facultad (
  id_autoridad bigint NOT NULL,
  id_facultad bigint NOT NULL,
  CONSTRAINT autoridad_facultad_pkey PRIMARY KEY (id_autoridad, id_facultad),
  CONSTRAINT fk_af_autoridad FOREIGN KEY (id_autoridad)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE CASCADE,
  CONSTRAINT fk_af_facultad FOREIGN KEY (id_facultad)
    REFERENCES public.facultad(id_facultad) ON DELETE CASCADE
);

-- Relación autoridad-carrera
CREATE TABLE public.autoridad_carrera (
  id_autoridad bigint NOT NULL,
  id_carrera bigint NOT NULL,
  CONSTRAINT autoridad_carrera_pkey PRIMARY KEY (id_autoridad, id_carrera),
  CONSTRAINT fk_ac_autoridad FOREIGN KEY (id_autoridad)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE CASCADE,
  CONSTRAINT fk_ac_carrera FOREIGN KEY (id_carrera)
    REFERENCES public.carrera(id_carrera) ON DELETE CASCADE
);

-- Tipo de documento
CREATE TABLE public.tipo_documento (
  id_tipo_documento bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre character varying NOT NULL,
  obligatorio boolean NOT NULL,
  estado boolean NOT NULL,
  CONSTRAINT tipo_documento_pkey PRIMARY KEY (id_tipo_documento)
);

-- ============================================
-- PROCESO DE SOLICITUD Y CONVOCATORIA
-- ============================================

-- Solicitud de docente (con perfil integrado)
CREATE TABLE public.solicitud_docente (
  id_solicitud bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_autoridad bigint NOT NULL,
  id_carrera bigint NOT NULL,
  id_materia bigint NOT NULL,
  id_area bigint NOT NULL,
  fecha_solicitud timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado_solicitud character varying NOT NULL DEFAULT 'pendiente',
  justificacion character varying NOT NULL,
  cantidad_docentes bigint NOT NULL,
  -- Campos del perfil docente integrados
  nivel_academico character varying NOT NULL,
  experiencia_profesional_min bigint NOT NULL,
  experiencia_docente_min bigint NOT NULL,
  observaciones character varying,
  CONSTRAINT solicitud_docente_pkey PRIMARY KEY (id_solicitud),
  CONSTRAINT fk_solicitud_autoridad FOREIGN KEY (id_autoridad)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT,
  CONSTRAINT fk_solicitud_carrera FOREIGN KEY (id_carrera)
    REFERENCES public.carrera(id_carrera) ON DELETE RESTRICT,
  CONSTRAINT fk_solicitud_materia FOREIGN KEY (id_materia)
    REFERENCES public.materia(id_materia) ON DELETE RESTRICT,
  CONSTRAINT fk_solicitud_area FOREIGN KEY (id_area)
    REFERENCES public.area_conocimiento(id_area) ON DELETE RESTRICT
);

-- Resolución (1:1 con solicitud)
CREATE TABLE public.resolucion (
  id_resolucion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_solicitud bigint NOT NULL UNIQUE,
  id_aprobador bigint NOT NULL,
  numero_resolucion character varying NOT NULL UNIQUE,
  fecha_emision date NOT NULL,
  estado character varying NOT NULL,
  observaciones character varying,
  CONSTRAINT resolucion_pkey PRIMARY KEY (id_resolucion),
  CONSTRAINT fk_resolucion_solicitud FOREIGN KEY (id_solicitud)
    REFERENCES public.solicitud_docente(id_solicitud) ON DELETE RESTRICT,
  CONSTRAINT fk_resolucion_aprobador FOREIGN KEY (id_aprobador)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT
);

-- Convocatoria (agrupa solicitudes aprobadas)
CREATE TABLE public.convocatoria (
  id_convocatoria bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  titulo character varying NOT NULL,
  descripcion character varying NOT NULL,
  fecha_publicacion date NOT NULL,
  fecha_inicio date NOT NULL,
  fecha_fin date NOT NULL,
  estado_convocatoria character varying NOT NULL DEFAULT 'abierta',
  CONSTRAINT convocatoria_pkey PRIMARY KEY (id_convocatoria)
);

-- Relación convocatoria-solicitud (1:N)
CREATE TABLE public.convocatoria_solicitud (
  id_convocatoria bigint NOT NULL,
  id_solicitud bigint NOT NULL,
  CONSTRAINT convocatoria_solicitud_pkey PRIMARY KEY (id_convocatoria, id_solicitud),
  CONSTRAINT fk_cs_convocatoria FOREIGN KEY (id_convocatoria)
    REFERENCES public.convocatoria(id_convocatoria) ON DELETE CASCADE,
  CONSTRAINT fk_cs_solicitud FOREIGN KEY (id_solicitud)
    REFERENCES public.solicitud_docente(id_solicitud) ON DELETE CASCADE
);

-- ============================================
-- PRE-POSTULACIÓN (TEMPORAL)
-- ============================================

-- Pre-postulación temporal (sin usuario)
CREATE TABLE public.prepostulacion (
  id_prepostulacion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombres character varying NOT NULL,
  apellidos character varying NOT NULL,
  identificacion character varying NOT NULL UNIQUE,
  correo character varying NOT NULL,
  telefono character varying NOT NULL,
  fecha_nacimiento date NOT NULL,
  fecha_envio timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado_revision character varying NOT NULL DEFAULT 'pendiente',
  id_revisor bigint,
  fecha_revision timestamp without time zone,
  observaciones_revision character varying,
  CONSTRAINT prepostulacion_pkey PRIMARY KEY (id_prepostulacion),
  CONSTRAINT fk_prepostulacion_revisor FOREIGN KEY (id_revisor)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE SET NULL
);

-- Relación pre-postulación con solicitudes (N:M)
CREATE TABLE public.prepostulacion_solicitud (
  id_prepostulacion bigint NOT NULL,
  id_solicitud bigint NOT NULL,
  CONSTRAINT prepostulacion_solicitud_pkey PRIMARY KEY (id_prepostulacion, id_solicitud),
  CONSTRAINT fk_ps_prepostulacion FOREIGN KEY (id_prepostulacion)
    REFERENCES public.prepostulacion(id_prepostulacion) ON DELETE CASCADE,
  CONSTRAINT fk_ps_solicitud FOREIGN KEY (id_solicitud)
    REFERENCES public.solicitud_docente(id_solicitud) ON DELETE CASCADE
);

-- Documentos temporales de pre-postulación
CREATE TABLE public.documento_temporal (
  id_documento_temporal bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_prepostulacion bigint NOT NULL,
  id_tipo_documento bigint NOT NULL,
  fecha_carga timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ruta_archivo text NOT NULL,
  CONSTRAINT documento_temporal_pkey PRIMARY KEY (id_documento_temporal),
  CONSTRAINT fk_doc_temp_prepostulacion FOREIGN KEY (id_prepostulacion)
    REFERENCES public.prepostulacion(id_prepostulacion) ON DELETE CASCADE,
  CONSTRAINT fk_doc_temp_tipo FOREIGN KEY (id_tipo_documento)
    REFERENCES public.tipo_documento(id_tipo_documento) ON DELETE RESTRICT
);

-- ============================================
-- POSTULACIÓN DEFINITIVA
-- ============================================

-- Postulante (creado al aprobar pre-postulación)
CREATE TABLE public.postulante (
  id_postulante bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_prepostulacion bigint UNIQUE,
  nombres_postulante character varying NOT NULL,
  apellidos_postulante character varying NOT NULL,
  identificacion character varying NOT NULL UNIQUE,
  correo_postulante character varying NOT NULL,
  telefono_postulante character varying NOT NULL,
  fecha_nacimiento date NOT NULL,
  id_usuario bigint NOT NULL,
  CONSTRAINT postulante_pkey PRIMARY KEY (id_postulante),
  CONSTRAINT fk_postulante_usuario FOREIGN KEY (id_usuario)
    REFERENCES public.usuario(id_usuario) ON DELETE RESTRICT,
  CONSTRAINT fk_postulante_prepostulacion FOREIGN KEY (id_prepostulacion)
    REFERENCES public.prepostulacion(id_prepostulacion) ON DELETE SET NULL
);

-- Postulación (a solicitud específica)
CREATE TABLE public.postulacion (
  id_postulacion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_postulante bigint NOT NULL,
  id_solicitud bigint NOT NULL,
  fecha timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado_postulacion character varying NOT NULL DEFAULT 'pendiente',
  preseleccion boolean,
  CONSTRAINT postulacion_pkey PRIMARY KEY (id_postulacion),
  CONSTRAINT fk_postulacion_postulante FOREIGN KEY (id_postulante)
    REFERENCES public.postulante(id_postulante) ON DELETE CASCADE,
  CONSTRAINT fk_postulacion_solicitud FOREIGN KEY (id_solicitud)
    REFERENCES public.solicitud_docente(id_solicitud) ON DELETE RESTRICT,
  CONSTRAINT unique_postulante_solicitud UNIQUE (id_postulante, id_solicitud)
);

-- Documentos de postulación (incluye migrados de pre-postulación)
CREATE TABLE public.documento (
  id_documento bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_postulacion bigint NOT NULL,
  id_tipo_documento bigint NOT NULL,
  id_documento_temporal bigint,
  fecha_carga timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado_validacion character varying NOT NULL DEFAULT 'pendiente',
  ruta_archivo text NOT NULL,
  CONSTRAINT documento_pkey PRIMARY KEY (id_documento),
  CONSTRAINT fk_documento_postulacion FOREIGN KEY (id_postulacion)
    REFERENCES public.postulacion(id_postulacion) ON DELETE CASCADE,
  CONSTRAINT fk_documento_tipo FOREIGN KEY (id_tipo_documento)
    REFERENCES public.tipo_documento(id_tipo_documento) ON DELETE RESTRICT,
  CONSTRAINT fk_documento_temporal FOREIGN KEY (id_documento_temporal)
    REFERENCES public.documento_temporal(id_documento_temporal) ON DELETE SET NULL
);
-- Resultados IA para DOCUMENTOS
CREATE TABLE public.resultados_ia_documento (
  id_resultado_ia_documento bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_documento bigint NOT NULL,
  resultado character varying NOT NULL,
  observaciones character varying,
  fecha_revision timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT resultados_ia_documento_pkey PRIMARY KEY (id_resultado_ia_documento),
  CONSTRAINT fk_resultado_ia_documento FOREIGN KEY (id_documento)
    REFERENCES public.documento(id_documento) ON DELETE CASCADE
);


-- ============================================
-- EVALUACIÓN Y ENTREVISTA
-- ============================================

-- Criterios de evaluación
CREATE TABLE public.criterio_evaluacion (
  id_criterio bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  nombre character varying NOT NULL,
  puntaje_maximo numeric NOT NULL,
  estado boolean NOT NULL DEFAULT true,
  id_area bigint NOT NULL,
  CONSTRAINT criterio_evaluacion_pkey PRIMARY KEY (id_criterio),
  CONSTRAINT fk_criterio_area FOREIGN KEY (id_area)
    REFERENCES public.area_conocimiento(id_area) ON DELETE RESTRICT
);

-- Evaluación de méritos
CREATE TABLE public.evaluacion_meritos (
  id_evaluacion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_postulacion bigint NOT NULL,
  id_decano bigint NOT NULL,
  id_coordinador bigint NOT NULL,
  puntaje numeric,
  observaciones character varying,
  fecha_evaluacion date,
  CONSTRAINT evaluacion_meritos_pkey PRIMARY KEY (id_evaluacion),
  CONSTRAINT fk_eval_postulacion FOREIGN KEY (id_postulacion)
    REFERENCES public.postulacion(id_postulacion) ON DELETE CASCADE,
  CONSTRAINT fk_eval_decano FOREIGN KEY (id_decano)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT,
  CONSTRAINT fk_eval_coordinador FOREIGN KEY (id_coordinador)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT
);

-- Calificación por criterio
CREATE TABLE public.calificacion_evaluacion (
  id_calificacion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_evaluacion bigint NOT NULL,
  id_criterio bigint NOT NULL,
  puntaje_obtenido numeric NOT NULL,
  observaciones character varying,
  CONSTRAINT calificacion_evaluacion_pkey PRIMARY KEY (id_calificacion),
  CONSTRAINT fk_calificacion_evaluacion FOREIGN KEY (id_evaluacion)
    REFERENCES public.evaluacion_meritos(id_evaluacion) ON DELETE CASCADE,
  CONSTRAINT fk_calificacion_criterio FOREIGN KEY (id_criterio)
    REFERENCES public.criterio_evaluacion(id_criterio) ON DELETE RESTRICT
);

-- Entrevista
CREATE TABLE public.entrevista (
  id_entrevista bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_postulacion bigint NOT NULL,
  id_decano bigint NOT NULL,
  id_coordinador bigint NOT NULL,
  
  -- Información de modalidad y logística
  modalidad character varying NOT NULL CHECK (modalidad IN ('presencial', 'virtual')),
  fecha_entrevista timestamp without time zone,
  lugar character varying,  -- para modalidad presencial
  enlace character varying,  -- para modalidad virtual (Zoom, Meet, etc.)
  
  -- Evaluación
  puntaje numeric,
  observaciones character varying,
  
  CONSTRAINT entrevista_pkey PRIMARY KEY (id_entrevista),
  CONSTRAINT fk_entrevista_postulacion FOREIGN KEY (id_postulacion)
    REFERENCES public.postulacion(id_postulacion) ON DELETE CASCADE,
  CONSTRAINT fk_entrevista_decano FOREIGN KEY (id_decano)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT,
  CONSTRAINT fk_entrevista_coordinador FOREIGN KEY (id_coordinador)
    REFERENCES public.autoridad_academica(id_autoridad) ON DELETE RESTRICT
);
-- Resultados IA para ENTREVISTAS
CREATE TABLE public.resultados_ia_entrevista (
  id_resultado_ia_entrevista bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_entrevista bigint NOT NULL,
  analisis_gestos text,
  analisis_comunicacion text,
  analisis_lenguaje_corporal text,
  confianza_detectada numeric,
  resumen_general text,
  observaciones character varying,
  fecha_analisis timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT resultados_ia_entrevista_pkey PRIMARY KEY (id_resultado_ia_entrevista),
  CONSTRAINT fk_resultado_ia_entrevista FOREIGN KEY (id_entrevista)
    REFERENCES public.entrevista(id_entrevista) ON DELETE CASCADE
);
-- Informe final
CREATE TABLE public.informe_final (
  id_informe bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  id_postulacion bigint NOT NULL,
  fecha_emision timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  resultado_final character varying NOT NULL,
  observaciones character varying NOT NULL,
  CONSTRAINT informe_final_pkey PRIMARY KEY (id_informe),
  CONSTRAINT fk_informe_postulacion FOREIGN KEY (id_postulacion)
    REFERENCES public.postulacion(id_postulacion) ON DELETE CASCADE
);


