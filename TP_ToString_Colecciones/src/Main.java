// Santiago Octavio Varela / @santiagovOK (GitHub) <santiago.varela@tupad.utn.edu.ar>

import entities.Categoria;
import entities.DetallePedido;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import enums.Rol;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Main {
    public static void main(String[] args) {

        // 1) Instanciar 2 Usuarios

        Usuario usuarioUno = new Usuario("Santiago", "Varela", "santiago@email.com", "123456789", "password123", Rol.ADMIN);
        Usuario usuarioDos = new Usuario("Pedro", "González", "pedro@email.com", "32456782", "password321", Rol.USUARIO);

        // 3) Instanciar 3 Categorías

        Categoria categoriaUno = new Categoria("Bebidas", "Bebidas frías y calientes");

        Categoria categoriaDos = new Categoria("Hamburguesas","Hamburguesas clásicas, vegetarianas y veganas");

        Categoria categoriaTres = new Categoria("Pizzas","Pizzas clásicas, vegetarianas y veganas");

        // 4) Instanciar 10 productos


        Producto prod1 = new Producto("Coca Cola 500ml", 1800.0, "Gaseosa cola", 50, "coca500.png", true, categoriaUno);
        Producto prod2 = new Producto("Agua Mineral 500ml", 1200.0, "Agua sin gas", 60, "agua500.png", true, categoriaUno);
        Producto prod3 = new Producto("Jugo Naranja", 1600.0, "Jugo natural", 40, "jugo_naranja.png", true, categoriaUno);

        Producto prod4 = new Producto("Hamburguesa Clasica", 6500.0, "Carne, queso y huevo", 30, "hamb_clasica.png", true, categoriaDos);
        Producto prod5 = new Producto("Hamburguesa Doble", 8200.0, "Doble carne y doble queso", 25, "hamb_doble.png", true, categoriaDos);
        Producto prod6 = new Producto("Hamburguesa Veggie", 7000.0, "Medallon vegetal con queso", 20, "hamb_veggie.png", true, categoriaDos);
        Producto prod7 = new Producto("Hamburguesa Vegana", 7300.0, "Sin ingredientes de origen animal", 18, "hamb_vegana.png", true, categoriaDos);

        Producto prod8 = new Producto("Pizza Muzzarella", 13000.0, "Pizza clasica de muzzarella", 15, "pizza_muzza.png", true, categoriaTres);
        Producto prod9 = new Producto("Pizza Napolitana", 15000.0, "Tomate, ajo y oregano", 12, "pizza_napo.png", true, categoriaTres);
        Producto prod10 = new Producto("Pizza Especial", 15500.0, "Jamon, morron y huevo", 10, "pizza_especial.png", true, categoriaTres);

        // Relacion categoria -> productos

        categoriaUno.getProductos().add(prod1);
        categoriaUno.getProductos().add(prod2);
        categoriaUno.getProductos().add(prod3);

        categoriaDos.getProductos().add(prod4);
        categoriaDos.getProductos().add(prod5);
        categoriaDos.getProductos().add(prod6);
        categoriaDos.getProductos().add(prod7);

        categoriaTres.getProductos().add(prod8);
        categoriaTres.getProductos().add(prod9);
        categoriaTres.getProductos().add(prod10);

        // 2) Instanciar 3 Pedidos

        Pedido pedido1 = new Pedido(LocalDate.now(), Estado.PENDIENTE, 0.0, FormaPago.EFECTIVO);
        pedido1.addDetallePedido(2, prod1);
        pedido1.addDetallePedido(1, prod2);

        Pedido pedido2 = new Pedido(LocalDate.now(), Estado.PENDIENTE, 0.0, FormaPago.TARJETA);
        pedido2.addDetallePedido(3, prod3);
        pedido2.addDetallePedido(2, prod4);

        Pedido pedido3 = new Pedido(LocalDate.now(), Estado.TERMINADO, 0.0, FormaPago.TRANSFERENCIA);
        pedido3.addDetallePedido(1, prod5);
        pedido3.addDetallePedido(4, prod6);

        // Consignas posteriores a las solicitudes para instanciar:

        // Coleccion general de productos cargados
        Set<Producto> productos = new HashSet<>();
        productos.add(prod1);
        productos.add(prod2);
        productos.add(prod3);
        productos.add(prod4);
        productos.add(prod5);
        productos.add(prod6);
        productos.add(prod7);
        productos.add(prod8);
        productos.add(prod9);
        productos.add(prod10);

        // Asignación de pedidos por usuario para resolver la consigna del usuario con mas pedidos
        Map<Usuario, Set<Pedido>> pedidosPorUsuario = new HashMap<>();
        pedidosPorUsuario.put(usuarioUno, new HashSet<>());
        pedidosPorUsuario.put(usuarioDos, new HashSet<>());

        pedidosPorUsuario.get(usuarioUno).add(pedido1);
        pedidosPorUsuario.get(usuarioUno).add(pedido2);
        pedidosPorUsuario.get(usuarioDos).add(pedido3);

        // Mostrar por consola un producto
        System.out.println("Muestro un producto:");
        System.out.println(prod1);

        // Mostrar el listado de productos cargados
        System.out.println("\n=== Listado de productos cargados ===");
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // Obtener y mostrar pedidos del usuario con mas pedidos
        Usuario usuarioConMasPedidos = null;
        int maxPedidos = -1;

        // Recorremos el mapa usuario -> set de pedidos para encontrar el mayor tamaño de colección.
        for (Map.Entry<Usuario, Set<Pedido>> entrada : pedidosPorUsuario.entrySet()) {
            int cantidadPedidos = entrada.getValue().size();

            // Si el usuario actual supera el máximo encontrado, actualizamos referencia y contador.
            if (cantidadPedidos > maxPedidos) {
                maxPedidos = cantidadPedidos;
                usuarioConMasPedidos = entrada.getKey();
            }
        }

        System.out.println("\n=== Usuario con mas pedidos ===");
        System.out.println(usuarioConMasPedidos);
        System.out.println("Cantidad de pedidos: " + maxPedidos);

        // Si se encontró usuario válido, mostramos todos sus pedidos y luego el detalle de cada pedido.
        if (usuarioConMasPedidos != null) {
            Set<Pedido> pedidosDelUsuario = pedidosPorUsuario.get(usuarioConMasPedidos);
            for (Pedido pedido : pedidosDelUsuario) {
                System.out.println(pedido);

                // Cada pedido contiene un Set<DetallePedido>; se imprime para ver producto, cantidad y subtotal.
                for (DetallePedido detalle : pedido.getDetalles()) {
                    System.out.println("  " + detalle);
                }
            }
        }

        // Instanciar un producto nuevo con los mismos campos comparados por equals y comparar con toda la coleccion
        Producto productoDuplicado = new Producto(
                "Coca Cola 500ml",
                1800.0,
                "Gaseosa cola",
                50,
                "coca500.png",
                true,
                categoriaUno
        );

        System.out.println("\n=== Comparacion de producto nuevo vs coleccion ===");
        for (Producto producto : productos) {
            System.out.println("Duplicado equals " + producto.getNombre() + " -> " + productoDuplicado.equals(producto));
        }
        System.out.println("Set contiene producto duplicado: " + productos.contains(productoDuplicado));
    }
}