**Estas son las modificaciones realizadas en el proyecto respecto a cómo había quedado en la entrega anterior (unidad6_lombok-dto)**

Consigna Nº1:

### ¿Por qué refactorizar en vez de crear uno nuevo?
La consigna solicita textualmente: *"Desarrolle un método en clase Pedido que se encargue de calcular el total"*. Como la clase `Pedido` ya implementa la interfaz `Calculable` que obliga a poseer el método `calcularTotal()`, no tendría sentido crear un método adicional redundante. Lo correcto arquitectónicamente es refactorizar el método obligatorio utilizando el paradigma funcional que exige el Trabajo Práctico, cumpliendo con la interfaz y con la consigna en el mismo lugar.

Cambio del método calcularTotal()

Antes:

```java
    @Override
    public void calcularTotal() {
        Double acumuladorTotal = 0.0;
        if (detalles != null) {
            for (DetallePedido detalle : detalles) {
                // Ahora getSubtotal() hace el cálculo al vuelo (cambios en DetallePedido)
                acumuladorTotal += detalle.getSubtotal();
            }
        }
        this.total = acumuladorTotal;
    }
```

Después (Refactorizado con Streams y Optional):

```java
    @Override
    public void calcularTotal() {
        this.total = java.util.Optional.ofNullable(this.detalles)
                .map(det -> det.stream()
                        .mapToDouble(DetallePedido::getSubtotal)
                        .sum())
                .orElse(0.0);
    }
```

### Paso a paso del nuevo código
1. **`Optional.ofNullable(this.detalles):`** Envuelve la lista en un `Optional` seguro que permite manejar posibles valores nulos de forma declarativa sin usar condicionales `if` tradicionales ni operadores ternarios.
2. **`.map(...):`** Si el `Optional` contiene datos (la colección no es nula), ejecuta las operaciones del Stream. Transforma la colección `detalles` en un flujo continuo de datos (`.stream()`), permitiendo aplicar operaciones funcionales encadenadas.
3. **`.mapToDouble(DetallePedido::getSubtotal):`** Es una **operación intermedia**. Recorre cada `DetallePedido` del flujo y "mapea" (transforma) cada objeto extrayendo su subtotal como un dato primitivo tipo `double`, usando una referencia a método.
4. **`.sum():`** Es una **operación terminal**. Recibe el flujo de valores `double` generados por el paso anterior y los suma todos.
5. **`.orElse(0.0):`** En caso de que el `Optional` original estuviera vacío (si `detalles` era `null`), retorna `0.0` como valor por defecto, previniendo el `NullPointerException`.
