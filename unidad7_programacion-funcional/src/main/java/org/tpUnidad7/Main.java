package org.tpUnidad7;

// Santiago Octavio Varela / @santiagovOK (GitHub) <santiago.varela@tupad.utn.edu.ar>

import org.tpUnidad7.dtos.UsuarioDTO;
import org.tpUnidad7.entities.Categoria;
import org.tpUnidad7.entities.DetallePedido;
import org.tpUnidad7.entities.Pedido;
import org.tpUnidad7.entities.Producto;
import org.tpUnidad7.entities.Usuario;
import org.tpUnidad7.enums.Estado;
import org.tpUnidad7.enums.FormaPago;
import org.tpUnidad7.enums.Rol;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // 1) Instanciar 2 Usuarios usando Builder
        Usuario usuarioUno = Usuario.builder()
                .nombre("Santiago")
                .apellido("Varela")
                .mail("santiago@email.com")
                .celular("123456789")
                .password("password123")
                .rol(Rol.ADMIN)
                .build();

        Usuario usuarioDos = Usuario.builder()
                .nombre("Pedro")
                .apellido("González")
                .mail("pedro@email.com")
                .celular("32456782")
                .password("password321")
                .rol(Rol.USUARIO)
                .build();

        // 3) Instanciar 3 Categorías usando Builder
        Categoria categoriaUno = Categoria.builder()
                .nombre("Bebidas")
                .descripcion("Bebidas frías y calientes")
                .build();

        Categoria categoriaDos = Categoria.builder()
                .nombre("Hamburguesas")
                .descripcion("Hamburguesas clásicas, vegetarianas y veganas")
                .build();

        Categoria categoriaTres = Categoria.builder()
                .nombre("Pizzas")
                .descripcion("Pizzas clásicas, vegetarianas y veganas")
                .build();

        // 4) Instanciar 10 productos usando Builder
        Producto prod1 = Producto.builder().nombre("Coca Cola 500ml").precio(1800.0).descripcion("Gaseosa cola").stock(50).imagen("coca500.png").disponible(true).categoria(categoriaUno).build();
        Producto prod2 = Producto.builder().nombre("Agua Mineral 500ml").precio(1200.0).descripcion("Agua sin gas").stock(60).imagen("agua500.png").disponible(true).categoria(categoriaUno).build();
        Producto prod3 = Producto.builder().nombre("Jugo Naranja").precio(1600.0).descripcion("Jugo natural").stock(40).imagen("jugo_naranja.png").disponible(true).categoria(categoriaUno).build();

        Producto prod4 = Producto.builder().nombre("Hamburguesa Clasica").precio(6500.0).descripcion("Carne, queso y huevo").stock(30).imagen("hamb_clasica.png").disponible(true).categoria(categoriaDos).build();
        Producto prod5 = Producto.builder().nombre("Hamburguesa Doble").precio(8200.0).descripcion("Doble carne y doble queso").stock(25).imagen("hamb_doble.png").disponible(true).categoria(categoriaDos).build();
        Producto prod6 = Producto.builder().nombre("Hamburguesa Veggie").precio(7000.0).descripcion("Medallon vegetal con queso").stock(20).imagen("hamb_veggie.png").disponible(true).categoria(categoriaDos).build();
        Producto prod7 = Producto.builder().nombre("Hamburguesa Vegana").precio(7300.0).descripcion("Sin ingredientes de origen animal").stock(18).imagen("hamb_vegana.png").disponible(true).categoria(categoriaDos).build();

        Producto prod8 = Producto.builder().nombre("Pizza Muzzarella").precio(13000.0).descripcion("Pizza clasica de muzzarella").stock(15).imagen("pizza_muzza.png").disponible(true).categoria(categoriaTres).build();
        Producto prod9 = Producto.builder().nombre("Pizza Napolitana").precio(15000.0).descripcion("Tomate, ajo y oregano").stock(12).imagen("pizza_napo.png").disponible(true).categoria(categoriaTres).build();
        Producto prod10 = Producto.builder().nombre("Pizza Especial").precio(15500.0).descripcion("Jamon, morron y huevo").stock(10).imagen("pizza_especial.png").disponible(true).categoria(categoriaTres).build();

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

        // 2) Instanciar 3 Pedidos usando Builder
        Pedido pedido1 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.EFECTIVO).build();
        pedido1.addDetallePedido(2, prod1);
        pedido1.addDetallePedido(1, prod2);

        Pedido pedido2 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.TARJETA).build();
        pedido2.addDetallePedido(3, prod3);
        pedido2.addDetallePedido(2, prod4);

        Pedido pedido3 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.TERMINADO).formaPago(FormaPago.TRANSFERENCIA).build();
        pedido3.addDetallePedido(1, prod5);
        pedido3.addDetallePedido(4, prod6);

        // Coleccion general de productos cargados (esto queda igual que antes)
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

        // Asignación de pedidos por usuario (esto queda igual que antes)
        Map<Usuario, Set<Pedido>> pedidosPorUsuario = new HashMap<>();
        pedidosPorUsuario.put(usuarioUno, new HashSet<>());
        pedidosPorUsuario.put(usuarioDos, new HashSet<>());

        pedidosPorUsuario.get(usuarioUno).add(pedido1);
        pedidosPorUsuario.get(usuarioUno).add(pedido2);
        pedidosPorUsuario.get(usuarioDos).add(pedido3);

        // --- SALIDAS POR CONSOLA SOLICITADAS ---

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

        for (Map.Entry<Usuario, Set<Pedido>> entrada : pedidosPorUsuario.entrySet()) {
            int cantidadPedidos = entrada.getValue().size();
            if (cantidadPedidos > maxPedidos) {
                maxPedidos = cantidadPedidos;
                usuarioConMasPedidos = entrada.getKey();
            }
        }

        System.out.println("\n=== Usuario con mas pedidos ===");
        System.out.println(usuarioConMasPedidos);
        System.out.println("Cantidad de pedidos: " + maxPedidos);

        if (usuarioConMasPedidos != null) {
            Set<Pedido> pedidosDelUsuario = pedidosPorUsuario.get(usuarioConMasPedidos);
            for (Pedido pedido : pedidosDelUsuario) {
                System.out.println(pedido);
                for (DetallePedido detalle : pedido.getDetalles()) {
                    System.out.println("  " + detalle);
                }
            }
        }

        // Instanciar un producto nuevo con los mismos campos comparados por equals
        Producto productoDuplicado = Producto.builder()
                .nombre("Coca Cola 500ml")
                .precio(1800.0)
                .descripcion("Gaseosa cola")
                .stock(50)
                .imagen("coca500.png")
                .disponible(true)
                .categoria(categoriaUno)
                .build();

        System.out.println("\n=== Comparacion de producto nuevo vs coleccion ===");
        for (Producto producto : productos) {
            System.out.println("Duplicado equals " + producto.getNombre() + " -> " + productoDuplicado.equals(producto));
        }
        System.out.println("Set contiene producto duplicado: " + productos.contains(productoDuplicado));

        // Prueba final: Mostrar el UsuarioDTO
        System.out.println("\n=== Demostración de Usuario DTO ===");
        UsuarioDTO dto = new UsuarioDTO(
                usuarioUno.getNombre(),
                usuarioUno.getApellido(),
                usuarioUno.getMail(),
                usuarioUno.getCelular()
        );
        System.out.println("Usuario DTO generado: " + dto);
    }
}