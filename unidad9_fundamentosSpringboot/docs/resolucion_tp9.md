# Resolución TP9: Fundamentos de Spring Boot

En este documento se detallará el proceso paso a paso de la resolución del Trabajo Práctico de la Unidad 9.

## 1. Migración del Proyecto Anterior (Unidad 8 - JPA)
Dado que las anotaciones de Hibernate y la estructura de las entidades son compatibles con Spring Data JPA, se procedió a reciclar el modelo de dominio del trabajo anterior, que ya contaba con un análisis del UML y una implementación funcional.

Se importaron al nuevo proyecto de Spring Boot los siguientes paquetes:
- **`entities`**: `Base`, `Usuario`, `Pedido`, `DetallePedido`, `Producto`, `Categoria`.
- **`enums`**: `Rol`, `Estado`, `FormaPago`.
- **`interfaces`**: `Calculable`.

*(Los repositorios manuales y archivos como `persistence.xml` y `Main.java` fueron descartados, ya que Spring Boot gestiona el contexto de persistencia a través de `application.properties` y las interfaces de `JpaRepository`).*

## 2. Configuración y Corrección de Packages
Al migrar los archivos, se actualizaron todas las declaraciones de `package` y las importaciones (`import`) en las clases Java para reflejar la estructura de directorios del nuevo proyecto: `com.tpUnidad9.unidad9_fundamentosSpringboot`. Esto solucionó los errores de sintaxis del IDE y dejó la base lista para trabajar en la nueva arquitectura.

## 3. Creación de la capa DTO (Data Transfer Object)
Siguiendo las buenas prácticas y las consignas del trabajo práctico, se comenzó a estructurar la capa DTO para separar el modelo de base de datos de los datos expuestos en la API. Se utilizó la característica nativa `record` de Java por su inmutabilidad y concisión.

### 3.1. DTOs de Categoría
Se desarrollaron tres DTOs específicos para gestionar de forma segura los datos de la entidad `Categoria` según el tipo de operación:
- **`CategoriaCreate`**: Se encarga de la creación. Incluye únicamente `nombre` y `descripcion`. En su método `toEntity()`, utiliza el patrón Builder provisto por Lombok (`Categoria.builder()`) para instanciar la entidad de forma segura sin requerir constructores complejos.
- **`CategoriaDto`**: Se encarga de la lectura (lo que se expone al cliente). Contiene el `id`, el `nombre` y la `descripcion`. Utiliza un método estático `toDto()` que interactúa con los getters de la entidad generados por Lombok.
- **`CategoriaEdit`**: Se encarga de la edición. Su método `applyTo(Categoria)` verifica que los datos ingresados no sean nulos antes de modificar la entidad original, evitando borrar información por accidente durante actualizaciones parciales.

### 3.2. DTOs de Producto
Se implementó la misma arquitectura de DTOs para la entidad `Producto`, abordando en este caso el manejo de tipos de datos y relaciones de forma segura:
- **Relaciones por ID**: Para vincular un producto a una categoría, los DTOs (`ProductoCreate` y `ProductoEdit`) solicitan únicamente el identificador (`Long idCategoria`) en lugar de recibir un objeto anidado. Posteriormente, la lógica de negocio (Servicio) es la encargada de buscar la `Categoria` real e inyectarla en la entidad, manteniendo el payload de la API limpio y eficiente.
- **Uso de Clases Envoltorias (Wrappers - Esto tuvo un poco más de investigación)**: En el `ProductoEdit`, se reemplazaron los tipos primitivos (`int stock`, `boolean disponible`) por sus respectivas clases envoltorias (`Integer`, `Boolean`). Esta práctica es indispensable para operaciones de actualización parcial (PATCH), ya que un tipo primitivo nunca puede ser `null`, lo que causaría la sobreescritura accidental de datos si el cliente no los envía. Al usar Wrappers, se pueden validar los nulos (`if (this.stock != null)`) e ignorar los atributos que no requieren cambios.

### 3.3. DTOs de Usuario
Se estructuraron los DTOs (`UsuarioCreate`, `UsuarioDto`, `UsuarioEdit`) como `record` para aprovechar su inmutabilidad. En el `UsuarioDto` se agregó explícitamente el campo `id` necesario para la identificación en el cliente, y se excluyeron datos sensibles como la contraseña y el rol por seguridad. También se centralizó la lógica de conversión en el método estático `toDto()` y se implementó el patrón Builder de Lombok en `toEntity()` para construir la entidad de forma segura.

### 3.4. DTOs de Pedido
Para la entidad `Pedido`, el `PedidoDto` incluye los datos principales y se encarga de transformar su lista interna de `DetallePedido` a una lista de `DetallePedidoDto` usando *Streams*. Por su parte, el `PedidoEdit` se diseñó restringido para permitir únicamente la modificación del `estado` y la `formaPago`, protegiendo así el resto de la información inmutable del pedido.

### 3.5. DTOs de DetallePedido
El `DetallePedidoDto` expone la `cantidad`, el nombre del producto asociado y el `subtotal` (que es un valor calculado). Al igual que en la arquitectura de productos, el `DetallePedidoCreate` únicamente solicita la cantidad y el identificador referencial (`idProducto`), delegando la búsqueda de la entidad Producto real a la futura capa de Servicios.


## 4. Creación de la capa de Repositorios
Para cumplir con las "Conclusiones Esperadas" respecto al uso de estereotipos y proveer la persistencia de datos mediante Spring Data JPA, se crearon las interfaces de Repositorios para cada una de las entidades:
- `CategoriaRepository`
- `ProductoRepository`
- `UsuarioRepository`
- `PedidoRepository`
- `DetallePedidoRepository`

Todas estas interfaces extienden de `JpaRepository<Entidad, Long>`, delegando a Spring la generación de los métodos CRUD y la ejecución de consultas a la base de datos H2. Adicionalmente, fueron decoradas explícitamente con la anotación `@Repository` para denotar su rol en la arquitectura y cumplir con las consignas del Trabajo Práctico.

## 5. Creación de la capa de Servicios
Continuando con la arquitectura en capas y para cumplir con la consigna de Inyección de Dependencias, se comenzó a implementar la lógica de negocio mediante clases anotadas con `@Service`.

### 5.1. Servicio de Categorías
Se definió la interfaz `CategoriaService` para establecer el contrato de operaciones interactuando puramente con DTOs (`CategoriaCreate`, `CategoriaDto`, `CategoriaEdit`), aislando así las Entidades del exterior.
En su implementación, `CategoriaServiceImpl`, se inyectó el `CategoriaRepository` mediante constructor y se desarrollaron las siguientes lógicas:
- **Guardado (`save`)**: Conversión del DTO de creación a Entidad usando su método `.toEntity()`, persistencia en base de datos y retorno de un `CategoriaDto`.
- **Búsquedas (`findById` y `findAll`)**: Uso de `orElseThrow` para manejar entidades no encontradas de forma elegante, y uso de *Streams* de Java para mapear listas enteras de entidades a sus respectivos DTOs.
- **Actualización (`update`)**: Se busca la entidad original, se le aplican los cambios parciales con el método `.applyTo()` del DTO de edición, y se vuelve a persistir.
- **Baja Lógica (`deleteById`)**: En lugar de hacer un borrado físico, se aprovechó la propiedad `eliminado` heredada de la clase `Base` (migrada del trabajo práctico de JPA) para realizar un borrado con baja lógica, cambiando el estado de eliminado a `true` y conservando el historial en la base de datos.
