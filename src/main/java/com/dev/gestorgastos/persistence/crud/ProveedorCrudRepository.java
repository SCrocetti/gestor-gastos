package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Proveedor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProveedorCrudRepository extends CrudRepository<Proveedor,Integer> {

    Optional<Proveedor> findByIdProveedor(Integer idProveedor);
    Optional<List<Proveedor>> findByNombreProveedorContainingAndActivoTrueOrderByNombreProveedorAsc(String nombreProveedor);
    Optional<List<Proveedor>> findByNombreProveedorContainingAndActivoFalseOrderByNombreProveedorAsc(String nombreProveedor);
    Optional<List<Proveedor>> findAllByActivoTrueOrderByNombreProveedorAsc();
    Optional<List<Proveedor>> findAllByActivoFalseOrderByNombreProveedorAsc();
    @Transactional
    @Modifying
    @Query("UPDATE Proveedor p SET p.activo = :activo WHERE p.idProveedor = :idProveedor")
    void setActivoByIdProveedor(Integer idProveedor, boolean activo);
}