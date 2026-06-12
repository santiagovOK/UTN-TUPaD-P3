package com.tpUnidad10.unidad10_APIRestSpringBoot.service;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.categoria.CategoriaCreate;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.categoria.CategoriaDto;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.categoria.CategoriaEdit;

import java.util.List;

public interface CategoriaService {
    public CategoriaDto save(CategoriaCreate categoriaCreate);
    public CategoriaDto findById(Long id);
    public List<CategoriaDto> findAll();
    public CategoriaDto update(CategoriaEdit categoriaEdit, Long idCategoria);
    public void deleteById(Long id);
}
