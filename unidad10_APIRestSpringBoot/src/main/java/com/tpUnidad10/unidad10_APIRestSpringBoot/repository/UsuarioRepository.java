package com.tpUnidad10.unidad10_APIRestSpringBoot.repository;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import com.tpUnidad10.unidad10_APIRestSpringBoot.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar por campo `mail` utilizando Optional para prevenir errores por `null`
    Optional<Usuario> findByMail(String mail);
}
