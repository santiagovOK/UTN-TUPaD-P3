# PROGRAMACIÓN III

**Trabajo Práctico - JPA**

## OBJETIVO GENERAL

Diseñar e implementar un modelo de dominio persistente utilizando JPA

## MARCO TEÓRICO

|     |     |
| --- | --- |
| **Concepto** | **Aplicación en el proyecto** |
| JPA | Permite mapear objetos a tablas de una base de datos relacional |
| @Id | Atributo que identifica de manera única a una entidad dentro del contexto de persistencia. |
| Entity | clase Java que representa una tabla en una base de datos |

## Caso Práctico

Dado el siguiente UML:

Continuando con las clases generadas en la Practica de “Lombok y DTO” deberá:

1.  Incorporar librería de hibernate.
2.  Generar archivo persistence.xml

```xml
&lt;?xml version="1.0" encoding="UTF-8"?&gt;

<persistence xmlns="https://jakarta.ee/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"

version="3.0">

&lt;persistence-unit name="miUnidad" transaction-type="RESOURCE_LOCAL"&gt;

&lt;provider&gt;org.hibernate.jpa.HibernatePersistenceProvider&lt;/provider&gt;

&lt;!-- TUS CLASES VAN ACA --&gt;

&lt;properties&gt;

&lt;!-- JDBC H2 en archivo --&gt;

&lt;property name="jakarta.persistence.jdbc.driver" value="org.h2.Driver"/&gt;

&lt;property name="jakarta.persistence.jdbc.url" value="jdbc:h2:file:./data/jpa_db"/&gt;

&lt;property name="jakarta.persistence.jdbc.user" value="sa"/&gt;

&lt;property name="jakarta.persistence.jdbc.password" value=""/&gt;

&lt;!-- Dialecto --&gt;

&lt;property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/&gt;

&lt;!-- Hibernate --&gt;

&lt;property name="hibernate.hbm2ddl.auto" value="update"/&gt;

&lt;property name="hibernate.show_sql" value="true"/&gt;

&lt;property name="hibernate.format_sql" value="true"/&gt;

&lt;/properties&gt;

&lt;/persistence-unit&gt;

&lt;/persistence&gt;

```

1.  Utilizar Anotaciones correspondientes de JPA para marcar relaciones, ids y entidades
2.  Deberá instanciar y Persistir:
    1.  2 Usuarios
    2.  3 Pedidos (al menos 2 detalles pedido por cada uno)
    3.  3 Categorías
    4.  10 productos
3.  Actualizar al menos 2 productos
4.  Buscar Usuario por id
5.  Buscar Usuario por mail
6.  Borrar 1 producto

## CONCLUSIONES ESPERADAS

- Persistir objetos en Base de datos
- Comprender los ciclos de vida de una entidad
- Comprender operaciones CRUD