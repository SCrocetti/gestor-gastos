package com.dev.gestorgastos.domain.repository;

import com.dev.gestorgastos.domain.PaisDto;

import java.util.List;

public interface PaisDtoRepository {
    public List<PaisDto> getAll() ;
    public PaisDto save(PaisDto pais);
    public boolean delete(Integer paisId);
}
