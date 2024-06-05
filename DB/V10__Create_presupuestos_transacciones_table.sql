CREATE TABLE IF NOT EXISTS presupuestos_transacciones (
    id_presupuesto_transaccion SERIAL PRIMARY KEY,
    monto_debitar BIGINT,
    monto_debitado BIGINT,
    monto_acreditar BIGINT,
    monto_acreditado BIGINT,
    id_cuenta_egreso INTEGER REFERENCES cuentas(id_cuenta) , 
    id_cuenta_ingreso INTEGER REFERENCES cuentas(id_cuenta),
    id_tipo_transaccion INTEGER REFERENCES tipos_transaccion(id_tipo_transaccion) ,
    id_plan INTEGER REFERENCES planes(id_plan),
    activo BOOLEAN DEFAULT TRUE
);