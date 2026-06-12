package com.tpUnidad10.unidad10_APIRestSpringBoot.service;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.producto.ProductoCreate;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.producto.ProductoDto;
import com.tpUnidad10.unidad10_APIRestSpringBoot.dtos.producto.ProductoEdit;

import java.util.List;

public interface ProductoService {
    public ProductoDto save(ProductoCreate productoCreate);
    public ProductoDto findById(Long id);
    public List<ProductoDto> findAll();
    public ProductoDto update(ProductoEdit productoEdit, Long idProducto);
    public void deleteById(Long id);
}
