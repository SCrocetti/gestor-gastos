package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Denominacion;
import com.dev.gestorgastos.persistence.entity.Proveedor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProveedorCrudRepository extends CrudRepository<Proveedor,Integer> {

    Optional<Proveedor> findByIdProveedor(Integer idProveedor);
    Optional<List<Proveedor>> findByNombreProveedorContaining(String nombreProveedor);
    List<Proveedor> findAllByOrderByNombreProveedorAsc();
}