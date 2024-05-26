CREATE TABLE IF NOT EXISTS cuentas (
    id_cuenta SERIAL PRIMARY KEY,
    descripcion  VARCHAR(100),
    numero_cuenta VARCHAR(100),
    fondos BIGINT,
    id_proveedor INTEGER REFERENCES proveedores(id_proveedor),
    id_denominacion INTEGER REFERENCES  denominaciones(id_denominacion),
    id_persona INTEGER REFERENCES personas(id_persona) 
);