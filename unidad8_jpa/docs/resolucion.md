**Estas son las modificaciones realizadas en el proyecto respecto a cómo había quedado en la entrega anterior (unidad7_programacion-funcional)**

# Consigna 1 Utilizar Anotaciones correspondientes de JPA para marcar relaciones, ids y entidades

**Creación del archivo persistence**
Se creó la carpeta `META-INF` dentro de `src/main/resources/` y el archivo `persistence.xml` (plantilla proveniente de las consignas del TP Nº8) correspondiente para que JPA funcione correctamente.

**Agregar dependencias en build.gradle.kts**
Se incorporaron y actualizaron las dependencias de **Hibernate Core** (proveedor oficial de JPA, actualizado a `6.4.10.Final` y el conector para **H2 Database** `2.4.240` en el archivo `build.gradle.kts`.

**a) Mapear la Clase Base**
Se agregó la anotación `@MappedSuperclass` a la clase `Base` para que sus propiedades (`id`, `eliminado`, `createdAt`) se hereden en las tablas de las entidades hijas. Se importó `jakarta.persistence.*` y se configuró el atributo `id` como clave primaria autoincremental utilizando `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
