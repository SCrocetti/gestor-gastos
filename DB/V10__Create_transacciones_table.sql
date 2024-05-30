CREATE TABLE IF NOT EXISTS transacciones (
    id_transaccion SERIAL PRIMARY KEY,
    descripcion VARCHAR(100),
    id_egreso INTEGER REFERENCES movimientos(id_movimiento) , 
    id_ingreso INTEGER REFERENCES movimientos(id_movimiento),
    activo BOOLEAN DEFAULT TRUE
);