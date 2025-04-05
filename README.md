# retotecnico-cobol

## Introducción:

El procesamiento de archivos en plataformas windows y linux puede ser un desafio, inclusive el paradigma de programación y la cantidad de datos a procesar.
En esta oportunidad procesamos archivos csv con ayuda de un complemento univocity y el uso de streams. Aunque sin un enfoque reactivo de momento.

## Instrucciones de Ejecución:
- **Dependencias**: Agregar com.univocity:univocity-parsers:2.9.1 en el archivo build.gradle
- **Ejeción**:
Genera un jar en la carpeta build
```bash
gradle shadowJar
java -jar .\build\libs\retotecnico-cobol-1.0-SNAPSHOT-all.jar .\src\NotFound
java -jar .\build\libs\retotecnico-cobol-1.0-SNAPSHOT-all.jar
java -jar .\build\libs\retotecnico-cobol-1.0-SNAPSHOT-all.jar .\external.csv
```

## Enfoque y Solución:

- **Lógica**: Leer el archivo y procesarlo por medio del uso de streams.
- **Diseño**: Maximo de filas en el archivo 2^31, por el uso de List. Programación bloqueante, en caso decidir reactiva
precisaria que todo el ecosistema sea reactivo. Sin emabrgo en JAVA 24, se pueden usar los virtual threats.
En caso no se le proporcione una ruta o el destino no existe, usara un documento que se encuentre en los recursos del jar.

## Estructura del Proyecto:

- **Arquitectura**: Hexagonal, reference Get Your Hands Dirty on Clean Architecture
- **Package**: Adapters, Ports and Domain
