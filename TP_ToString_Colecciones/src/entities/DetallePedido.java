package entities;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import java.util.Objects;

public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;
    private Pedido pedido;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;

        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }

    public DetallePedido(int cantidad, Double subtotal, Pedido pedido, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.pedido = pedido;
        this.producto = producto;

        // Validación para subtotal: si el producto es null o su precio es null, el subtotal se establece en 0.0
        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        // Se imprime solo el nombre del producto para evitar toString recursivos.
        String nombreProducto = "sin producto";
        if (producto != null) {
            nombreProducto = producto.getNombre();
        }

        return "DetallePedido{" +
                "cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", producto='" + nombreProducto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DetallePedido that = (DetallePedido) o;
        return cantidad == that.cantidad && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cantidad, subtotal);
    }
}

