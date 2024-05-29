package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.DenominacionRepository;
import com.dev.gestorgastos.persistence.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenominacionService {
    @Autowired
    DenominacionRepository denominacionRepository;

    public Optional<DenominacionDto> getByIdDenominacion(Integer idDenominacion){
        return denominacionRepository.getByIdDenominacion(idDenominacion);
    }
    public Optional<List<DenominacionDto>> getByNombreDenominacionContains(String nombreDenominacion){
        return denominacionRepository.getByNombreDenominacionContains(nombreDenominacion);
    }
    public List<DenominacionDto> getAll() {
        return denominacionRepository.getAll();
    }
    public DenominacionDto save(DenominacionDto denominacionDto){
        return denominacionRepository.save(denominacionDto);
    }
    public boolean delete(Integer idDenominacion){
        return denominacionRepository.delete(idDenominacion);
    }
}