**Estas son las modificaciones realizadas en el proyecto respecto a cómo había quedado en la entrega anterior (unidad7_programacion-funcional)**

# Consigna 1 Utilizar Anotaciones correspondientes de JPA para marcar relaciones, ids y entidades

**Creación del archivo persistence**
Se creó la carpeta `META-INF` dentro de `src/main/resources/` y el archivo `persistence.xml` (plantilla proveniente de las consignas del TP Nº8) correspondiente para que JPA funcione correctamente.

**Agregar dependencias en build.gradle.kts**
Se incorporaron y actualizaron las dependencias de **Hibernate Core** (proveedor oficial de JPA, actualizado a `6.4.10.Final` y el conector para **H2 Database** `2.4.240` en el archivo `build.gradle.kts`.

**a) Mapear la Clase Base**
Se agregó la anotación `@MappedSuperclass` a la clase `Base` para que sus propiedades (`id`, `eliminado`, `createdAt`) se hereden en las tablas de las entidades hijas. Se importó `jakarta.persistence.*` y se configuró el atributo `id` como clave primaria autoincremental utilizando `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`.

**b) Mapear Entidades Simples (`Usuario` y `Categoria`)**
Configuración de las entidades `Usuario` y `Categoria` con la anotación `@Entity` **a nivel de clase** para que JPA las reconozca como tablas de la base de datos (y hereden automáticamente el id de `Base`). En `Usuario`, se aseguró que el atributo `mail` sea único utilizando `@Column(unique = true)` y se mapeó el enum `Rol` al formato de base de datos correspondiente mediante `@Enumerated(EnumType.STRING)` para que se persista el valor en texto y no de forma numérica.

**c) Mapear Entidad Producto y su Relación ManyToOne**
A la clase `Producto` se le añadió la anotación `@Entity` a nivel de clase. Para configurar la relación con la categoría a la que pertenece, se agregó `@ManyToOne` en el atributo `categoria` y se especificó la columna foránea con `@JoinColumn(name = "categoria_id")`. De igual manera, se actualizó la clase `Categoria`, añadiéndole `@OneToMany(mappedBy = "categoria")` en su colección de `productos` para que ambas clases mantengan la relación bidireccional correctamente y no se generen tablas intermedias innecesarias.

**d) Mapear Entidades `Pedido` y `DetallePedido` (Relación de Composición)**
A ambas clases se les colocó la etiqueta `@Entity`. En `Pedido`, para mantener la composición fuerte con sus detalles, se utilizó `@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)` sobre la colección de `DetallePedido`. Del otro lado, en la clase `DetallePedido`, se configuró `@ManyToOne` en el atributo `pedido` con `@JoinColumn(name = "pedido_id")` para declarar quién sostiene la clave foránea en la base de datos (relación bidireccional donde JPA entiende que la tabla de detalles lleva el peso de la FK, aunque lógicamente `Pedido` domine el ciclo de vida). También se le agregó la relación con la entidad `Producto` a través de otro `@ManyToOne` y un respectivo `@JoinColumn(name = "producto_id")`.

**e) EntityManagerFactor en Main**
Se instanció `EntityManagerFactory` llamando a la unidad `unidad8_jpa` (definida en `persistence.xml`), obteniéndose un `EntityManager`. Dentro de un **bloque transaccional** (`em.getTransaction().begin() ... commit`), se persistieron todos los objetos creados anteriormente: 2 Usuarios, 3 Categorías, 10 Productos y 3 Pedidos. El guardado de estos últimos implicó automáticamente a sus respectivos `DetallePedido` debido al uso de `CascadeType.ALL`.

# Consigna 3 a 6 Operaciones CRUD
**Operaciones extra con la DB**
Luego de la persistencia inicial, en un segundo bloque transaccional se utilizó el `EntityManager` para cumplir los siguientes requerimientos:
- **3) Actualizar al menos 2 productos**: Se utilizaron `em.find()` para obtener dos entidades `Producto` (con ID 1 y 2). A través de sus métodos setters, se modificaron los valores de `precio` y `stock`.
- **4) Buscar Usuario por id**: Se utilizó `em.find(Usuario.class, 1L)` para buscar exitosamente el primer usuario y mostrarlo por consola.
- **5) Buscar Usuario por mail**: Se preparó una consulta con JPQL (`em.createQuery(...)`), donde se buscó un usuario cuyo atributo mail coincidiera con `"santiago@email.com"` pasando el argumento a través de `.setParameter("mail", mailABuscar)`.
- **6) Borrar 1 producto**: Por último, se hizo otro `em.find()` para localizar un producto. Se seleccionó de forma estratégica el producto de ID 10 ("Pizza Especial") porque no contaba con registros cargados de detalle en transacciones de `Pedido`, evitando problemas de integridad referencial (tengo que reever esto). Una vez obtenido, se lo eliminó de la base de datos usando `em.remove(productoABorrar)`. Se cerró la transacción con `.commit()` y se finalizó correctamente el `EntityManagerFactory`.

# Validación de la persistencia con H2
- **Saved Settings:** `Generic H2 (Embedded)`
- **Setting Name:** `Generic H2 (Embedded)`
- **Driver Class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:file:/<RUTA_ABSOLUTA_DEL_PROYECTO>/unidad8_jpa/data/jpa_db`
- **User Name:** `sa`
- **Password:** `<VACÍO>`

Con esos datos se puede abrir la consola web de H2 y revisar las tablas generadas por JPA, junto con los registros persistidos en la carpeta `data/` del proyecto.
