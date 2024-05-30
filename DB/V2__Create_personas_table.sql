CREATE TABLE IF NOT EXISTS personas (
    id_persona SERIAL PRIMARY KEY,
    numero_documento VARCHAR(50) UNIQUE,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);