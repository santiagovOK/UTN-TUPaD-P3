package com.tpUnidad9.unidad9_fundamentosSpringboot.dtos.usuario;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

public record UsuarioDto(
        String nombre,
        String apellido,
        String mail,
        String celular
) {
}

// Gracias a esto, se oculta la información sensible (contraseña, rol) del usuario (punto 4. del TP Nº6). Además, al ser un record, se generan automáticamente los métodos equals, hashCode y toString, lo que facilita su uso en colecciones y depuración.