package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento",insertable = false,updatable = false)
    private TipoDocumento tipoDocumento;
}
