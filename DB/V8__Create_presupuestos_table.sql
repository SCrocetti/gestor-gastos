CREATE TABLE IF NOT EXISTS presupuestos (
    id_presupuesto SERIAL PRIMARY KEY,
    monto_asignado BIGINT,
    monto_ejecutado BIGINT,
    id_tipo_movimiento INTEGER REFERENCES tipos_movimiento(id_tipo_movimiento) , 
    id_cuenta INTEGER REFERENCES cuentas(id_cuenta),
    id_plan INTEGER REFERENCES planes(id_plan),
    activo BOOLEAN DEFAULT TRUE
);