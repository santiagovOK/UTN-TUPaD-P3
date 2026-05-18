package org.tpUnidad7.entities;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.tpUnidad7.enums.Rol;

@Getter
@Setter
// Como buena práctica de seguridad, directamente evitamos que password se imprima en el ToString. Antes había usado "****", pero creo que esto es mejor para el uso de Lombok.
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String password;
    private Rol rol;

}


