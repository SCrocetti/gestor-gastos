CREATE TABLE IF NOT EXISTS tipos_transaccion (
    id_tipo_transaccion SERIAL PRIMARY KEY,
    nombre_tipo_transaccion VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);