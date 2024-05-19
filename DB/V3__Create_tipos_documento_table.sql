CREATE TABLE IF NOT EXISTS tipos_documento (
    id_tipo_documento SERIAL PRIMARY KEY,
    nombre_tipo_documento VARCHAR(50),
    id_pais INTEGER REFERENCES paises(id_pais)
);