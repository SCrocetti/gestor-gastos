package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "nombre_tipo_documento")
    private String nombreTipoDocumento;
}
