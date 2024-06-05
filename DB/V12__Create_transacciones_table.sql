CREATE TABLE IF NOT EXISTS transacciones (
    id_transaccion SERIAL PRIMARY KEY,
    monto_egreso BIGINT, 
    monto_ingreso BIGINT, 
    id_cuenta_egreso INTEGER REFERENCES cuentas(id_cuenta) , 
    id_cuenta_ingreso INTEGER REFERENCES cuentas(id_cuenta),
    fecha_hora TIMESTAMP WITHOUT TIME ZONE,
    id_tipo_transaccion  INTEGER REFERENCES tipos_transaccion(id_tipo_transaccion),
    id_presupuesto_transaccion INTEGER  REFERENCES presupuestos_transacciones(id_presupuesto_transaccion),
    activo BOOLEAN DEFAULT TRUE
);