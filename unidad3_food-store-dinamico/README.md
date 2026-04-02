# Trabajo Práctico - Unidad 3 (JavaScript) - Volver dinámico al Food Store

✨ Estudiante

- Nombre: Varela, Santiago Octavio
- Comisión: M25 C3-13
- Email institucional: santiago.varela@tupad.utn.edu.ar

Repositorio donde podrán encontrar mis trabajos de Programación III: https://github.com/santiagovOK/UTN-TUPaD-P3

---

## Objetivo

En esta unidad se transformó la maqueta estática del Food Store en una interfaz dinámica usando JavaScript, renderizando categorías y productos a partir de datos en memoria.

## Funcionalidades implementadas

- Renderizado dinámico de categorías en el listado lateral.
- Renderizado dinámico de productos destacados en la sección principal.
- Generación de estructura semántica por producto con article, h3, p y button.
- Inserción de contenido dinámico con template strings: imagen, nombre, descripción y precio.
- Carga de datos desde arrays definidos en JavaScript.
- Interacción básica en botones de productos mediante eventos.

## Criterios aplicados (según las consignas)

- Uso de const para declarar arrays y funciones.
- Iteración de listas con métodos de array (forEach).
- Creación de nodos del DOM con createElement y appendChild.
- Uso de innerHTML para maquetar el contenido interno de cada tarjeta.
- Renderizado respetando etiquetas semánticas solicitadas por el TP.
- Rutas relativas de imágenes funcionando según la estructura del proyecto.
- Archivos principales sin errores de sintaxis.
- Validación: no hay errores en consola al cargar el sitio.

## Otras decisiones en base a las consignas

- Se implementaron únicamente los selectores solicitados por la actividad (IDs).
- No se crearon clases adicionales para estructura o utilidades, para mantener el alcance exacto del TP.
- La prioridad de esta unidad fue resolver la lógica de renderizado dinámico con JavaScript, manteniendo una base de estilos simple y funcional.

## Estructura básica del módulo

- data.js: fuente de datos (categorías y productos).
- main.js: lógica de renderizado e interacción.
- index.html: estructura base y contenedores para inyección dinámica.
- styles.css: estilos generales y layout responsivo con Flexbox. (creado en esta instancia, ya que en la unidad 1 no lo habíamos creado.)

## Nota de diseño

- Display y centrado elegido: **Flexbox**, con algunas particularidades para aplicar un diseño responsivo sin usar media queries para este caso sencillo, más centrado en JavaScript.