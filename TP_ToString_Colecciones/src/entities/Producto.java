package entities;


/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import java.util.Objects;

public class Producto extends Base {
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria; // Atributo faltante en el UML, pero que marca la relación de agregación 1 a muchos con Categoria

    public Producto() {
        super();
    }

    public Producto(String nombre, Double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria; // asociamos el producto a una categoría preexistente
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "disponible=" + disponible +
                ", imagen='" + imagen + '\'' +
                ", stock=" + stock +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        // Se compara por campos propios del producto para permitir detectar duplicados lógicos en Set.
        Producto producto = (Producto) o;
        return stock == producto.stock && disponible == producto.disponible && Objects.equals(nombre, producto.nombre) && Objects.equals(precio, producto.precio) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(imagen, producto.imagen);
    }

    @Override
    public int hashCode() {
        // Debe usar los mismos campos que equals para mantener el contrato equals/hashCode.
        return Objects.hash(nombre, precio, descripcion, stock, imagen, disponible);
    }
}

