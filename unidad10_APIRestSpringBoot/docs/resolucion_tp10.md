# Resolución TP10: API Rest Spring Boot

En este documento se detallará el proceso paso a paso de la resolución del Trabajo Práctico de la Unidad 10.

## 1. Migración del Proyecto Anterior (Unidad 9 - Fundamentos Spring Boot)

Dado que la estructura y la base de código que provenían del Trabajo Práctico de la Unidad 9 eran directamente útiles y compatibles para realizar el Trabajo Práctico de la Unidad 10, se tomó la decisión de copiar el proyecto tal cual estaba. 

Posteriormente, se llevó a cabo una refactorización integral del proyecto en la cual:
- Se renombraron los directorios fuente para reflejar la nueva unidad (`src/main/java/com/tpUnidad10/unidad10_APIRestSpringBoot`).
- Se actualizaron en bloque todos los nombres de los paquetes (`package`) y las sentencias de importación (`import`) a lo largo de todo el código.
- Se reconfiguraron los archivos de compilación (`build.gradle`, `settings.gradle`) y las configuraciones de entorno del IDE para que no queden vestigios del proyecto anterior.

## 2. Configuración de Swagger (OpenAPI)
Para poder documentar de forma interactiva nuestra API, se agregó al archivo `build.gradle` la dependencia correspondiente a Springdoc OpenAPI:
`implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'`

## 3. Ajuste de Repositorios y Servicios (Búsqueda por Mail)
Para cumplir con las consignas, específicamente la búsqueda de un usuario por su correo electrónico, se extendió la capa de persistencia y lógica de negocio heredada del TP9:
- **`UsuarioRepository`**: Se agregó la firma `Optional<Usuario> findByMail(String mail);` delegando a Spring Data JPA la creación automática de la consulta a la base de datos.
- **`UsuarioService`**: Se definió el nuevo contrato `UsuarioDto findByMail(String mail);`.
- **`UsuarioServiceImp`**: Se implementó el método utilizando `.orElseThrow()` para arrojar una excepción (posteriormente manejada) en caso de que no exista el usuario, y transformando la entidad encontrada a un `UsuarioDto` antes de ser devuelta.
