package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.PersonaDto;

import java.util.List;
import java.util.Optional;

public interface DenominacionDtoRepository {
    public Optional<DenominacionDto> getByIdDenominacion(Integer idDenominacion);
    public Optional<List<DenominacionDto>> getActivosByNombreDenominacionContains(String nombreDenominacion);
    public Optional<List<DenominacionDto>> getInactivosByNombreDenominacionContains(String nombreDenominacion);
    public Optional<List<DenominacionDto>>  getAll() ;
    public Optional<List<DenominacionDto>> getAllDeleted();
    public DenominacionDto save(DenominacionDto denominacionDto);
    public boolean delete(Integer idDenominacion);
    public boolean unDelete(Integer idDenominacion);
}
