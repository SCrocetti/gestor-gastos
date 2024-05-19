package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_movimiento")
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "nombre_tipo_movimiento")
    private String nombreTipoMovimiento;

    @OneToMany(mappedBy = "tipoMovimiento")
    private List<Movimiento> movimientos;
}
