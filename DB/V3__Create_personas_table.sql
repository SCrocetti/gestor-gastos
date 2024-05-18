CREATE TABLE IF NOT EXISTS personas (
    id_persona SERIAL PRIMARY KEY,
    numero_documento VARCHAR(50),
    id_tipo_documento INTEGER REFERENCES tipos_documento(id_tipo_documento), 
    nombres VARCHAR(100),
    apellidos VARCHAR(100)
);