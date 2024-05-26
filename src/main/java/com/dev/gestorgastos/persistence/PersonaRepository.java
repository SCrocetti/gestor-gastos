package com.dev.gestorgastos.persistence;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.crud.PersonaCrudRepository;
import com.dev.gestorgastos.domain.repository.PersonaDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaRepository implements PersonaDtoRepository {
    @Autowired
    PersonaCrudRepository personaCrudRepository;

    @Override
    public List<PersonaDto> getAll() {
        return PersonaMapper.INSTANCE.toDtos(personaCrudRepository.findAllByOrderByApellidosAscNombresAsc());
    }

    @Override
    public PersonaDto save(PersonaDto personaDto) {
        return PersonaMapper.INSTANCE.toDto(personaCrudRepository.save(PersonaMapper.INSTANCE.toEntity(personaDto)));
    }

    @Override
    public boolean delete(Integer personaId) {
        personaCrudRepository.deleteById(personaId);
        return personaCrudRepository.existsById(personaId);
    }
}
