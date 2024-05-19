package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "nombre_tipo_documento")
    private String nombreTipoDocumento;

    @Column(name = "id_pais")
    private Integer idPais;

    @OneToMany(mappedBy = "tipoDocumento")
    private List<Persona> personas;

    @ManyToOne
    @JoinColumn(name = "id_pais",insertable = false,updatable = false)
    private Pais pais;
}
