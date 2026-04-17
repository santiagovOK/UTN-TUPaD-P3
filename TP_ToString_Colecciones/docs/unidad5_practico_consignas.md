# PROGRAMACIÓN III

**Trabajo Práctico - ToString-Colecciones**

## OBJETIVO GENERAL

Desarrollar y procesar colecciones de objetos en Java, implementando toString()

para una representación clara de los datos.

## MARCO TEÓRICO

|     |     |
| --- | --- |
| **Concepto** | **Aplicación en el proyecto** |
| ToString | Método que convierte Objetos a una cadena de texto |
| Set | Tipo de Colección en Java. |
| Equals | Método comparador de objetos |
| HashCode | Método que genera un Hash en base al contenido del objeto |

## Caso Práctico

Dado el siguiente UML:

1.  Desarrolle las clases con sus Relaciones correspondientes (utilice Colecciones de tipo Set).
2.  En cada clase sobreescriba ToString, Equals y HashCode.
3.  Deberá instanciar:
    1.  2 Usuarios
    2.  3 Pedidos (al menos 2 detalles pedido por cada uno)
    3.  3 Categorías
    4.  10 productos

1.  En clase Main deberá de tener las instancias solicitadas en punto anterior y deberá utilizar toString para mostrar por consola un producto, el listado de productos cargados y los pedidos del usuario que más pedidos posea.
2.  Instancie un producto nuevo donde el/los campos comparados en equals sean iguales, compare esa instancia con toda la colección. Mostrar resultados por pantalla.

## CONCLUSIONES ESPERADAS

- Desarrollar código a partir del diagrama UML respetando tipos de datos y multiplicidad de las relaciones.
- Sobreescribir correctamente los métodos toString(), equals() y hashCode(), manteniendo coherencia entre identidad del objeto y funcionamiento de las colecciones.
- Utilizar colecciones de tipo Set comprendiendo cómo el contrato del objeto impacta en la unicidad de los elementos.
- Aplicar criterios básicos de diseño orientado a objetos (identidad, encapsulamiento y consistencia del modelo).