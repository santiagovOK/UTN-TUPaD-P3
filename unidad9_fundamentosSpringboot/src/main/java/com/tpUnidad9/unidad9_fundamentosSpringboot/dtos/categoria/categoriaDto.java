package com.tpUnidad9.unidad9_fundamentosSpringboot.dtos.categoria;

// Santiago Octavio Varela / @santiagovOK (GitHub) <santiago.varela@tupad.utn.edu.ar>

import com.tpUnidad9.unidad9_fundamentosSpringboot.entities.Categoria;

public record CategoriaDto (Long id,
                            String nombre,
                            String descripcion,) {
    public static toDto(Categoria categoria) {
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );)
    }

}