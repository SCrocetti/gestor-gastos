CREATE TABLE IF NOT EXISTS planes (
    id_plan SERIAL PRIMARY KEY,
    descripcion VARCHAR(100),
    fecha_inicio DATE, 
    fecha_fin DATE,
    activo BOOLEAN DEFAULT TRUE
);