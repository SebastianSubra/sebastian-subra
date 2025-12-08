# Proyecto Java Maven - [Nombre del Proyecto]

Este proyecto es una plantilla base para comenzar a trabajar con aplicaciones Java utilizando Maven. Asegúrate de seguir las instrucciones para configurar tu entorno correctamente y entender cómo contribuir al proyecto.

## Tabla de Contenidos

- [Introducción](#introducción)
- [Requisitos](#requisitos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Compilación y Ejecución](#compilación-y-ejecución)
- [Contribución](#contribución)
- [Licencia](#licencia)

---

## Introducción

Este es un proyecto base para aprender sobre el uso de **Java** y **Maven**. El objetivo familiarizarse con las buenas prácticas de desarrollo y gestionar dependencias utilizando Maven.

## Requisitos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

- **Java 8+** (JDK) - Puedes descargarlo desde [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) o [OpenJDK](https://openjdk.java.net/)
- **Maven** - Puedes instalar Maven siguiendo las instrucciones en su sitio oficial: [https://maven.apache.org/install.html](https://maven.apache.org/install.html)
- **IDE recomendada:** Visual Studio Code o cualquier editor de tu preferencia con soporte para Java.

## Configuración del Entorno

### 1. Instalar Java
Asegúrate de que tienes **Java 8** o una versión superior instalada. Puedes verificarlo con el siguiente comando:

```bash
java -version
```

### 2. Instalar Maven
Una vez que tengas Java instalado, puedes proceder con la instalación de Maven. Para verificar que Maven esté instalado correctamente, ejecuta el siguiente comando:

mvn -version

### 3. Configurar el IDE
Para trabajar con este proyecto, puedes usar cualquier IDE que soporte Java, como Visual Studio Code, IntelliJ IDEA, Eclipse, etc. Si estás usando Visual Studio Code, asegúrate de instalar las siguientes extensiones:

Java Extension Pack (de Microsoft)
Maven for Java

## Estructura del proyecto

```plaintext
[NombreDelProyecto]/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ejemplo/
│                   └── App.java
├── pom.xml
├── target/
└── README.md
```

## Compilación y ejecución

Para compilar y ejecutar el proyecto, sigue estos pasos:

Abre una terminal en la raíz del proyecto.

Ejecuta el siguiente comando para compilar el proyecto:
```bash
mvn clean install
```
Para ejecutar la aplicación, usa el siguiente comando:
```bash
mvn exec:java
```

## Contribución

Si deseas contribuir a este proyecto, por favor sigue estos pasos:
- Haz un fork del repositorio.
- Crea una nueva rama (git checkout -b feature-nueva-funcionalidad).
- Realiza tus cambios y haz commit (git commit -am 'Agregué nueva funcionalidad').
- Empuja los cambios a tu fork (git push origin feature-nueva-funcionalidad).
- Crea un pull request.

## Funcionalidades Implementadas Según el Enunciado de la Actividad

Como parte de la práctica solicitada, se han añadido al proyecto las siguientes funcionalidades:

### 1. Icono personalizado de la aplicación (app.png)

Se agregó un icono propio a la interfaz gráfica.  
El archivo se encuentra en:

src/main/resources/app.png

Y se carga en la ventana principal mediante:

setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/app.png")));

---

### 2. Obtención dinámica del tipo de cambio EUR/USD

Se implementó la clase:

com.example.model.CurrencyService

La aplicación obtiene el tipo de cambio en tiempo real llamando al servicio gratuito:

https://api.frankfurter.app/latest?from=EUR&to=USD

Este servicio devuelve un JSON con el valor actualizado del dólar respecto al euro, el cual se utiliza en el controlador para actualizar la vista mediante:

view.displayOrder(order, rate);

---

### 3. Visualización de totales en EUR y USD

En el método displayOrder() de OrderView se muestran los importes en ambas monedas.

La conversión se realiza mediante:

grossUSD = grossEUR * rate  
discountedUSD = discountedEUR * rate

La interfaz gráfica muestra:

- Gross total (EUR)  
- Discounted total (EUR)  
- Gross total (USD)  
- Discounted total (USD)

permitiendo visualizar claramente ambos valores.

---

### 4. Generación de la primera release en GitHub

Para completar la actividad se generó una release en GitHub siguiendo estos pasos:

1. Subir todos los cambios al repositorio.
2. Ir a la sección “Releases”.
3. Seleccionar “Draft a new release”.
4. Crear la versión v1.0 con el título:  
   **First release – Icon and EUR/USD conversion**
5. (Opcional) Adjuntar el archivo JAR generado con Maven mediante:

mvn clean package

---

### Resultado final

La aplicación cumple todos los requisitos del enunciado:

- Integra un icono personalizado en la ventana principal.
- Consulta un servicio web externo para obtener el tipo de cambio EUR→USD.
- Muestra correctamente los totales en ambas monedas dentro de la interfaz gráfica.
- Se publicó una release oficial en GitHub con la funcionalidad implementada.

## Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.