package org.tpUnidad6.entities;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import java.time.LocalDateTime;
import java.util.Objects;

public class Base {
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    // Primera versión del constructor `Base()`, que inicializa los valores comunes (createAt = now, eliminado = false). Sirve cuando aún no está el `id`

    public Base() {
        this.createdAt = LocalDateTime.now();
        this.eliminado = false;
    }

    // Segundo `Base()`, que hace lo mismo que el anterior y asigna un `id`. Sirve cuando ya se conoce la identidad del objeto a crear.

    public Base(Long id, boolean eliminado, LocalDateTime createdAt) {
        this(); // reutiliza el constructor sin parámetros para inicializar createAt y eliminado
        this.id = id; // asigna el id pasado como parámetro
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Creo el método `toString` como base reutilizable para el resto de las clases que hereden de `Base`. En caso de que se necesite agregar campos propios, deberá sobreescribirse.

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", eliminado=" + eliminado +
                ", createdAt=" + createdAt +
                '}';
    }


    // Lo mismo que para toString, para `equals` y `hashCode`, aunque probablemente haya que sobreescribirlos en otras clases.


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return eliminado == base.eliminado && Objects.equals(id, base.id) && Objects.equals(createdAt, base.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eliminado, createdAt);
    }
}






