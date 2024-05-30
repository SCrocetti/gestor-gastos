package com.dev.gestorgastos.persistence;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.crud.PersonaCrudRepository;
import com.dev.gestorgastos.domain.repository.PersonaDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository implements PersonaDtoRepository {
    @Autowired
    PersonaCrudRepository personaCrudRepository;


    @Override
    public Optional<PersonaDto> getByIdPersona(Integer idPersona) {
        return personaCrudRepository.findByIdPersona(idPersona).map(PersonaMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<PersonaDto> getByNumeroDocumento(String numeroDocumento) {
        return personaCrudRepository.findByNumeroDocumento(numeroDocumento).map(PersonaMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<PersonaDto>> getAll() {
        return personaCrudRepository.findAllByActivoTrueOrderByApellidosAscNombresAsc().map(PersonaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PersonaDto>> getAllDeleted() {
        return personaCrudRepository.findAllByActivoFalseOrderByApellidosAscNombresAsc().map(PersonaMapper.INSTANCE::toDtos);
    }

    @Override
    public PersonaDto save(PersonaDto personaDto) {
        return PersonaMapper.INSTANCE.toDto(personaCrudRepository.save(PersonaMapper.INSTANCE.toEntity(personaDto)));
    }

    @Override
    public boolean delete(Integer personaId) {
        return getByIdPersona(personaId).map(persona -> {
            personaCrudRepository.setActivoByIdPersona(personaId,false);
            return true;
        }).orElse(false);
    }

    @Override
    public  boolean unDelete(Integer personaId){
        return getByIdPersona(personaId).map(persona -> {
            personaCrudRepository.setActivoByIdPersona(personaId,true);
            return true;
        }).orElse(false);
    }
}
