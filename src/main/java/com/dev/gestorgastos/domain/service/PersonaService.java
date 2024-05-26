package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.PersonaMapper;
import com.dev.gestorgastos.persistence.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public Optional<PersonaDto> getByIdPersona(Integer idPersona) {
        return personaRepository.getByIdPersona(idPersona);
    }

    public Optional<PersonaDto> getByNumeroDocumento(String numeroDocumento) {
        return personaRepository.getByNumeroDocumento(numeroDocumento);
    }

    public List<PersonaDto> getAll() {
        return personaRepository.getAll();
    }

    public PersonaDto save(PersonaDto personaDto) {
        return personaRepository.save(personaDto);
    }

    public boolean delete(Integer personaId) {
        return personaRepository.delete(personaId);
    }
}
