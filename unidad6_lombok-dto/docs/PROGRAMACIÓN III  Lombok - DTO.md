# PROGRAMACIÓN III

**Trabajo Práctico - Lombok-DTO**

## OBJETIVO GENERAL

Hacer uso de librería lombok para simplificar código repetitivo, crear DTOs para ocultar información sensible.

## MARCO TEÓRICO

|     |     |
| --- | --- |
| **Concepto** | **Aplicación en el proyecto** |
| DTO | Data Transfer Object |
| Lombok | Librería que simplifica código repetitivo |
| Record | Clase Inmutable |

## Caso Práctico

Dado el siguiente UML:

1.  Para el desarrollo de este trabajo práctico se deberá tomar como base el modelo de clases desarrollado en la Unidad 5. A diferencia del trabajo anterior, en esta instancia el proyecto deberá configurarse y desarrollarse utilizando Gradle como herramienta de gestión de dependencias y construcción. Asimismo, se deberá incorporar la librería Lombok, la cual será utilizada para reemplazar código repetitivo mediante anotaciones.
2.  Insertar anotaciones Lombok, se deberán utilizar al menos las siguientes anotaciones:
    1.  Getter/Setter
    2.  ToString
    3.  EqualsAndHashCode
    4.  Builder / SuperBuilder
    5.  AllArgsConstructor
    6.  NoArgsConstructor

1.  En el método main se deberán instanciar utilizando patron builder
    1.  2 Usuarios
    2.  3 Pedidos (al menos 2 detalles pedido por cada uno)
    3.  3 Categorías
    4.  10 productos
2.  En la clase Main se deberán tener las instancias solicitadas en el punto anterior y se deberá utilizar el método toString para mostrar por consola un producto, el listado de productos cargados y los pedidos del usuario que posea la mayor cantidad de pedidos.
3.  Instanciar un nuevo producto donde el/los campos utilizados en el método equals sean iguales a los de otro producto existente. Luego, comparar dicha instancia con todos los elementos de la colección de productos y mostrar los resultados por pantalla.
4.  Crear un nuevo paquete llamado DTOs y, dentro de él, una clase record llamada UsuarioDTO, que contendrá la misma información que la clase Usuario, evitando mostrar información sensible. Se deberán ocultar los siguientes atributos:
    1.  Rol
    2.  Contraseña

## CONCLUSIONES ESPERADAS

- Desarrollar código a partir del diagrama UML
- Utilizar Librería Lombok
- Crear DTOs utilizando Record