package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.PersonaDto;

import java.util.List;

public interface PersonaDtoRepository {
    public List<PersonaDto> getAll() ;
    public PersonaDto save(PersonaDto pais);
    public boolean delete(Integer paisId);
}
