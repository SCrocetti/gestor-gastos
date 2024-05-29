package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.PersonaDto;

import java.util.List;
import java.util.Optional;

public interface DenominacionDtoRepository {
    public Optional<DenominacionDto> getByIdDenominacion(Integer idDenominacion);
    public Optional<List<DenominacionDto>> getByNombreDenominacionContains(String nombreDenominacion);
    public List<DenominacionDto> getAll() ;
    public DenominacionDto save(DenominacionDto denominacionDto);
    public boolean delete(Integer idDenominacion);
}
