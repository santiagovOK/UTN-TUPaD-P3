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

*(A partir de aquí, se continuará con la creación de los DTOs para el resto de las entidades).*
