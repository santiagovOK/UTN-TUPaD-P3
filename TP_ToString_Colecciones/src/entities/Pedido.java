package entities;

/*
 *
 * @author Santiago Octavio Varela / @santiagovOK (GitHub)
 * <santiago.varela@tupad.utn.edu.ar>
 */

import enums.Estado;
import enums.FormaPago;
import interfaces.Calculable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// en el ejemplo del video, Calculable no está implementado, pero lo agrego para que se pueda calcular el total del pedido y es lo correcto hacerlo aquí en cuanto al UML

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles = new HashSet<>(); // siguiendo el ejemplo, incluyo un Set DetallePedido para establecer la relación de composición entre ambas clases

    public Pedido() {
        super();
    }

    public Pedido(LocalDate fecha, Estado estado, Double total, FormaPago formaPago) {
        super();
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Set<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    // Método para agregar detalles (Relación de Composición)

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        // Se sincroniza ambos lados de la relación de composición.
        nuevoDetalle.setPedido(this);
        this.detalles.add(nuevoDetalle);

        // cada vez que agrego un detalle, actualizo el total
        calcularTotal();
    }

    // en el ejemplo del video, la interface Calculable no está construida, es por eso que aquí uso @Override e implemento el método de forma similar a como se muestra en el video.

    @Override
    public void calcularTotal() {
        Double acumuladorTotal = 0.0;
        for (DetallePedido detalle : detalles) {
            // Validación para subtotal: si el detalle es null o su subtotal es null, se omite en el cálculo del total
            if (detalle != null && detalle.getSubtotal() != null) {
                acumuladorTotal += detalle.getSubtotal();
            }
        }
        this.total = acumuladorTotal;
    }

    // Los métodos `findeDetallePedidoByProducto()` y `deleteDetallePedidoByProducto()` que están en el UML no serían necesarios para resolver la consigna de este trabajo de momento.


    @Override
    public String toString() {
        // Se muestra cantidad de detalles en lugar del Set completo para evitar recursión y ruido en consola.
        return "Pedido{" +
                "fecha=" + fecha +
                ", estado=" + estado +
                ", total=" + total +
                ", formaPago=" + formaPago +
                ", cantidadDetalles=" + detalles.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(fecha, pedido.fecha) && estado == pedido.estado && Objects.equals(total, pedido.total) && formaPago == pedido.formaPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fecha, estado, total, formaPago);
    }
}

