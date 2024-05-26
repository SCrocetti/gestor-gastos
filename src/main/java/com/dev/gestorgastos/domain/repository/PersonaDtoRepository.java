package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.PersonaDto;

import java.util.List;
import java.util.Optional;

public interface PersonaDtoRepository {
    public Optional<PersonaDto> getByIdPersona(Integer idPersona);
    public Optional<PersonaDto> getByNumeroDocumento(String numeroDocumento);
    public List<PersonaDto> getAll() ;
    public PersonaDto save(PersonaDto personaDto);
    public boolean delete(Integer idPersona);
}
