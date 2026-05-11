package org.tpUnidad6.entities;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode; // no es incluido en el video, pero si queremos mantener lo que hicimos en la unidad 5, tendría que ir.
import lombok.experimental.SuperBuilder; // si no uso experimental, me lanza error.

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder



public class Base {
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
}






