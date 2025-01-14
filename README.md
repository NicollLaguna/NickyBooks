# NickyBooks - Gestión de Libros

**NickyBooks** es una aplicación de escritorio Java que permite buscar libros en la plataforma *Gutendex*, obtener estadísticas de descarga de los libros y visualizar un Top 10 de los libros más descargados. Utiliza la API pública de *Gutendex* para obtener datos de libros disponibles en dominio público.

## Características

- **Búsqueda de libros**: Permite ingresar el nombre de un libro para obtener información detallada, como el autor y el título.
- **Top 10 de libros más descargados**: Muestra un listado con los 10 libros más descargados de *Gutendex*.
- **Estadísticas de descargas**: Presenta estadísticas como la media, máxima, mínima y cantidad de registros evaluados de las descargas de los libros.

## Requisitos

Para ejecutar este proyecto, necesitas tener instalado:

- [Java 11 o superior](https://adoptopenjdk.net/) (Se recomienda usar OpenJDK)
- [Maven](https://maven.apache.org/) (para la gestión de dependencias)

## Instalación

1. **Clonar el repositorio**:
 
   git clone https://github.com/NicollLaguna/NickyBooks.git

2. **Compilar el proyecto**:

   Navega al directorio del proyecto y ejecuta el siguiente comando:

   mvn clean install

3. **Ejecutar la aplicación**:

   Luego de compilar, puedes ejecutar la aplicación desde la terminal:

   mvn exec:java

   También puedes ejecutar la aplicación desde un IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [Eclipse](https://www.eclipse.org/).

## Interfaz de Usuario
La interfaz de usuario de NickyBooks ha sido diseñada con el propósito de ser simple, intuitiva y fácil de usar. La aplicación permite al usuario realizar búsquedas de libros, ver las estadísticas y acceder al Top 10 de los libros más descargados de manera clara y accesible.
![image](https://github.com/user-attachments/assets/9217b21f-2f7b-4fb6-ba22-45323ac90bf6)

## Características de la Interfaz:
Campo de búsqueda: En la parte superior, el usuario puede ingresar el nombre de un libro en el campo de texto.
Botón de búsqueda: Al hacer clic en el botón de búsqueda, la aplicación realizará la consulta de información en la API de Gutendex y mostrará los resultados en la ventana.
Top 10: Un botón que al hacer clic muestra una lista de los 10 libros más descargados.
Estadísticas: Otro botón permite al usuario ver las estadísticas relacionadas con las descargas de los libros.
Diseño Visual
La interfaz ha sido diseñada con un estilo sencillo y directo, utilizando componentes gráficos de Java Swing para permitir una experiencia fluida.

## Uso

1. **Búsqueda de libro**: Ingresa el nombre del libro en el campo de texto y haz clic en el botón *Buscar*. Se mostrará información sobre el libro si se encuentra en la base de datos.

2. **Top 10 de libros**: Haz clic en el botón *Top 10* para ver los 10 libros más descargados en *Gutendex*.

3. **Estadísticas**: Haz clic en el botón *Estadísticas* para ver estadísticas de las descargas de los libros.

## Estructura del Proyecto

El proyecto tiene la siguiente estructura de carpetas:

![image](https://github.com/user-attachments/assets/56dbb4f2-bc66-41e4-95ec-afbcca3d1472)


## Dependencias

Este proyecto utiliza las siguientes dependencias de Maven:

- **Jackson**: Para la conversión de los datos JSON obtenidos de la API a objetos Java. Se usa la librería `jackson-databind`.
- **JDK**: Para ejecutar el proyecto con la funcionalidad de GUI.

### Dependencias en el archivo `pom.xml`

<dependencies>
	<dependency>
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-databind</artifactId>
		<version>2.16.0</version>
	</dependency>
</dependencies>


## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, puedes:

1. Hacer un fork del repositorio.
2. Crear una rama con tus cambios.
3. Enviar un *pull request* explicando las mejoras que has realizado.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

### Contacto

Si tienes preguntas o necesitas ayuda con el proyecto, no dudes en contactarme.

- **GitHub**: [NicollLaguna](https://github.com/NicollLaguna)
- **Correo electrónico**: [lagunagonzaleznicoll@gmail.com](mailto:lagunagonzaleznicoll@gmail.com)
