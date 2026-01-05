# Order Management System (Java + Maven)

Aplicación de escritorio desarrollada en **Java** utilizando **Maven** y el patrón
**Modelo–Vista–Controlador (MVC)**. La aplicación permite gestionar pedidos mediante
una interfaz gráfica, incluyendo búsqueda, creación, edición y eliminación de pedidos,
así como persistencia de datos en formato JSON.

Este proyecto ha sido desarrollado como parte de la asignatura **Introducción a la Ingeniería**.

---

## Tabla de Contenidos

- Introducción
- Requisitos
- Configuración del Entorno
- Estructura del Proyecto
- Compilación y Ejecución
- Funcionalidades Implementadas
- Persistencia de Datos
- Diagramas UML
- Releases
- Licencia

---

## Introducción

El objetivo de este proyecto es aplicar los conceptos fundamentales de la ingeniería del
software mediante el desarrollo de una aplicación Java real, estructurada y mantenible.

Se han utilizado buenas prácticas de desarrollo como la separación de responsabilidades
mediante el patrón MVC, persistencia de datos y documentación mediante diagramas UML.

---

## Requisitos

Para ejecutar el proyecto es necesario tener instalado:

- Java 8 o superior
- Maven
- IDE recomendado: Visual Studio Code, IntelliJ IDEA o Eclipse

Puedes comprobar las versiones instaladas con:

java -version  
mvn -version

---

## Configuración del Entorno

Si se utiliza Visual Studio Code, se recomienda instalar las siguientes extensiones:

- Java Extension Pack
- Maven for Java

---

## Estructura del Proyecto

demo/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── view/
│       │       └── Main.java
│       └── resources/
│           ├── app.png
│           ├── logback.xml
│           ├── orders.json
│           └── plantuml/
│               ├── casos.puml
│               ├── clases.puml
│               └── secuencia.puml
├── orders.json
├── pom.xml
└── README.md

---

## Compilación y Ejecución

Desde la raíz del proyecto:

mvn clean install  
mvn exec:java

---

## Funcionalidades Implementadas

### 1. Icono personalizado de la aplicación

La aplicación incluye un icono propio para la ventana principal.

Archivo:
src/main/resources/app.png

---

### 2. Conversión dinámica EUR / USD

Se ha implementado un servicio de conversión de moneda que obtiene el tipo de
cambio EUR/USD en tiempo real mediante una API externa. Este valor se utiliza
para mostrar los importes tanto en euros como en dólares.

---

### 3. Visualización y búsqueda de pedidos

- Al iniciar la aplicación se muestra una lista con los IDs de todos los pedidos
- El usuario puede buscar un pedido introduciendo su ID
- Se muestra el detalle completo del pedido seleccionado
- Se calculan y muestran los totales en EUR y USD

---

### 4. Creación de pedidos

- El usuario puede crear nuevos pedidos desde la interfaz gráfica
- El ID del pedido debe ser único
- El pedido creado se añade automáticamente a la lista
- Los cambios se guardan de forma persistente

---

### 5. Edición de pedidos (opcional)

- El usuario puede editar un pedido existente
- Solo se permite modificar:
  - cantidad de los artículos
  - descuento aplicado a los artículos
- El resto de la información del pedido permanece inalterada
- Los cambios se guardan de forma persistente en el archivo JSON

---

### 6. Eliminación de pedidos

- El usuario puede eliminar pedidos seleccionándolos desde la lista
- Se solicita confirmación antes de eliminar
- La lista se actualiza automáticamente
- Los cambios se guardan de forma persistente

---

## Persistencia de Datos

La aplicación utiliza archivos JSON como sistema de persistencia.

Existen dos archivos JSON con funciones distintas:

- src/main/resources/orders.json  
  Contiene datos iniciales de ejemplo y se utiliza únicamente si no existe
  un archivo persistente.

- orders.json (raíz del proyecto)  
  Archivo persistente que se crea y actualiza automáticamente al crear,
  editar o borrar pedidos.

Los cambios realizados por el usuario se mantienen entre ejecuciones de la aplicación.

---

## Diagramas UML

Los diagramas UML del proyecto se encuentran en:

src/main/resources/plantuml/

Incluyen:

- Diagrama de casos de uso (casos.puml)
- Diagrama de clases (clases.puml)
- Diagrama de secuencia (secuencia.puml)

El diagrama de casos de uso ha sido actualizado para reflejar las nuevas
funcionalidades implementadas.

---

## Releases

### v1.0 – Initial version
- Estructura inicial del proyecto
- Configuración básica con Maven
- Arquitectura MVC
- Carga de pedidos desde archivo JSON

### v1.1 – Release automation setup
- Configuración del versionado automático en GitHub
- Generación automática del mensaje de release
- Sin cambios funcionales en la aplicación

### v1.2 – Icon + EUR/USD Conversion (Assignment Submission)
- Icono personalizado de la aplicación
- Conversión dinámica EUR → USD usando Frankfurter API
- Visualización de totales en EUR y USD en la interfaz gráfica
- README actualizado
- Limpieza del repositorio y corrección de estructura

### v2.0 – Order Management and Persistence
Incluye todas las funcionalidades desarrolladas en la última iteración:
- Listado de pedidos al iniciar la aplicación
- Búsqueda y visualización de pedidos
- Creación de pedidos
- Edición de pedidos (cantidad y descuento de artículos)
- Eliminación de pedidos
- Persistencia completa en archivo JSON
- Diagrama de casos de uso actualizado
- Documentación final actualizada

## Licencia

Este proyecto se distribuye bajo la Licencia MIT.