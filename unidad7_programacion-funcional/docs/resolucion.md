**Estas son las modificaciones realizadas en el proyecto respecto a cómo había quedado en la entrega anterior (unidad6_lombok-dto)**

Consigna Nº1:

¿Por qué refactorizar en vez de crear uno nuevo?
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

**Explicación - Paso a paso del nuevo código**

1. **`Optional.ofNullable(this.detalles):`** Envuelve la lista en un `Optional` seguro que permite manejar posibles valores nulos de forma declarativa sin usar condicionales `if` tradicionales ni operadores ternarios.
2. **`.map(...):`** Si el `Optional` contiene datos (la colección no es nula), ejecuta las operaciones del Stream. Transforma la colección `detalles` en un flujo continuo de datos (`.stream()`), permitiendo aplicar operaciones funcionales encadenadas.
3. **`.mapToDouble(DetallePedido::getSubtotal):`** Es una **operación intermedia**. Recorre cada `DetallePedido` del flujo y "mapea" (transforma) cada objeto extrayendo su subtotal como un dato primitivo tipo `double`, usando una referencia a método.
4. **`.sum():`** Es una **operación terminal**. Recibe el flujo de valores `double` generados por el paso anterior y los suma todos.
5. **`.orElse(0.0):`** En caso de que el `Optional` original estuviera vacío (si `detalles` era `null`), retorna `0.0` como valor por defecto, previniendo el `NullPointerException`.


Consigna Nº 2: ("Mostrar por consola productos disponibles")

```java
    productos.stream()
            .filter(Producto::isDisponible)
            .forEach(p -> System.out.println("- " + p.getNombre()));
```

**Explicación:**
Utilizando la colección `productos` que ya contenía todos los artículos instanciados, se la convierte en un flujo de datos mediante `.stream()`. Luego, se aplica el método intermedio `.filter()` el cual procesa una expresión lambda (en este caso simplificada a la referencia de método `Producto::isDisponible`) para descartar aquellos productos cuya disponibilidad sea falsa. Finalmente, la operación terminal `.forEach()` imprime iterativamente cada elemento que superó el filtro.


Consigna Nº 3: ("Mostrar por consola la cantidad de ítems que tiene un pedido")

```java
    int cantidadItems = pedido1.getDetalles().stream()
            .mapToInt(DetallePedido::getCantidad)
            .sum();
```

**Explicación:**
Al obtener los detalles de un pedido (por ejemplo `pedido1`), se obtiene una colección que pasamos a Stream. El método `.mapToInt()` es capaz de reducir el objeto de tipo `DetallePedido`, extrayendo únicamente su atributo `cantidad` como un valor primitivo entero. Al finalizar la transformación de objetos a números enteros, se ejecuta la operación terminal `.sum()` para realizar la adición total de dichas cantidades.


Consigna Nº 4: ("Detectar productos que tengan menos de 5 como valor en stock")

```java
    productos.stream()
            .filter(p -> p.getStock() < 5)
            .forEach(p -> System.out.println("ALERTA - " + p.getNombre() + " (Stock: " + p.getStock() + ")"));
```

**Explicación:**
Similar al Punto 2, se aplica `.stream()` en el conjunto principal. El criterio dentro de `.filter()` usa una lambda para comparar el retorno del método `getStock()` contra el valor `5`. De este modo, únicamente los productos que posean valor 4 o inferior continuarán en el pipeline hasta llegar al método `.forEach()`, donde serán logueados alertando sobre su estado por consola.
