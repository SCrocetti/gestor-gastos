CREATE TABLE IF NOT EXISTS movimientos (
    id_movimiento SERIAL PRIMARY KEY,
    monto BIGINT,
    fecha_hora TIMESTAMP WITHOUT TIME ZONE, 
    id_tipo_movimiento INTEGER REFERENCES tipos_movimiento(id_tipo_movimiento) , 
    id_cuenta INTEGER REFERENCES cuentas(id_cuenta),
    id_presupuesto INTEGER REFERENCES presupuestos(presupuesto_id)
);