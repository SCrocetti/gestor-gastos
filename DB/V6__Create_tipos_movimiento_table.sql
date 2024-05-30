CREATE TABLE IF NOT EXISTS tipos_movimiento (
    id_tipo_movimiento SERIAL PRIMARY KEY,
    nombre_tipo_movimiento VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);