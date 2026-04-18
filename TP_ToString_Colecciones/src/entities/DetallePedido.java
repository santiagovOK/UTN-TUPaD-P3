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

    public DetallePedido() {
        super();
    }

    public DetallePedido(int cantidad, Double subtotal, Pedido pedido, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.pedido = pedido;
        this.producto = producto;
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
        return "DetallePedido{" +
                "subtotal=" + subtotal +
                ", cantidad=" + cantidad +
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

