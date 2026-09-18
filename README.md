# Sistema RFID - Modulo de categorias de activos

Proyecto desarrollado para la evidencia **GA7-220501096-AA2-EV01: Codificacion de modulos del software** del programa Tecnologo en Analisis y Desarrollo de Software - ADSO.

## Aprendiz

- **Nombre:** Cristian Camilo Betancur Alzate
- **Ficha:** 3336117
- **Programa:** Tecnologo en Analisis y Desarrollo de Software - ADSO
- **Institucion:** Servicio Nacional de Aprendizaje - SENA
- **Proyecto formativo:** Sistema RFID de Gestion de Activos

## Descripcion

Este proyecto implementa el modulo de gestion de categorias de activos del Sistema RFID de Gestion de Activos.

La aplicacion fue desarrollada en Java y utiliza JDBC para conectarse con una base de datos MySQL. Mediante un menu de consola, permite registrar, consultar, actualizar y eliminar categorias de activos.

El alcance corresponde a un modulo funcional del proyecto y conserva una estructura organizada por responsabilidades para facilitar su mantenimiento y crecimiento posterior.

## Funcionalidades

- Registrar una categoria de activo.
- Consultar las categorias registradas.
- Actualizar una categoria mediante su identificador.
- Eliminar una categoria mediante su identificador.
- Validar campos de texto obligatorios.
- Validar opciones numericas y estados.
- Solicitar confirmacion antes de eliminar un registro.
- Informar cuando una categoria no existe.

## Tecnologias utilizadas

- Java 21.
- Apache NetBeans.
- Apache Maven.
- JDBC.
- MySQL.
- MySQL Workbench.
- MySQL Connector/J 9.4.0.
- Git.
- GitHub.

## Estructura del proyecto

```text
RFIDCategoriaActivo/
|-- pom.xml
|-- README.md
|-- .gitignore
|-- database/
|   `-- configuracion_rfid.sql
`-- src/
    `-- main/
        `-- java/
            `-- co/
                `-- edu/
                    `-- sena/
                        `-- rfid/
                            |-- conexion/
                            |   `-- ConexionBD.java
                            |-- dao/
                            |   `-- CategoriaActivoDAO.java
                            |-- modelo/
                            |   `-- CategoriaActivo.java
                            `-- vista/
                                `-- Main.java
```

## Organizacion del codigo

### ConexionBD

La clase `ConexionBD` centraliza la conexion JDBC con MySQL. Las credenciales no se almacenan en el codigo fuente, sino que se obtienen mediante las variables de entorno `RFID_DB_USER` y `RFID_DB_PASSWORD`.

### CategoriaActivo

La clase `CategoriaActivo` representa una categoria de activos mediante los siguientes atributos:

- `idCategoria`
- `nombre`
- `descripcion`
- `activo`

La clase incluye constructores, metodos de acceso y modificacion, y una representacion textual para apoyar las consultas en consola.

### CategoriaActivoDAO

La clase `CategoriaActivoDAO` implementa el patron DAO para centralizar las operaciones de acceso a la tabla `categoriaactivo`.

Las operaciones disponibles son:

- `insertarCategoria()`: registra una categoria.
- `listarCategorias()`: consulta las categorias existentes.
- `actualizarCategoria()`: modifica una categoria mediante su ID.
- `eliminarCategoria()`: elimina una categoria mediante su ID.

Las operaciones utilizan `PreparedStatement` y administran los recursos JDBC mediante `try-with-resources`.

### Main

La clase `Main` es el punto de entrada de la aplicacion. Contiene el menu de consola, recibe los datos del usuario, aplica validaciones basicas y llama los metodos del DAO.

## Base de datos

- **Motor:** MySQL
- **Base:** `rfid_gestion_activos`
- **Tabla:** `categoriaactivo`

La tabla utilizada contiene las siguientes columnas:

| Columna | Tipo esperado | Descripcion |
|---|---|---|
| `id_categoria` | `INT AUTO_INCREMENT` | Identificador principal |
| `nombre` | `VARCHAR(100)` | Nombre de la categoria |
| `descripcion` | `VARCHAR(255)` | Descripcion de la categoria |
| `activo` | `TINYINT(1)` | Estado activo o inactivo |

El script de preparacion se encuentra en:

```text
database/configuracion_rfid.sql
```

## Requisitos previos

Antes de ejecutar el proyecto se requiere:

1. Java 21 o una version compatible con la configuracion de Maven.
2. Apache Maven.
3. MySQL en ejecucion.
4. La base `rfid_gestion_activos` y la tabla `categoriaactivo`.
5. Un usuario MySQL con permisos `SELECT`, `INSERT`, `UPDATE` y `DELETE` sobre la base.
6. Las variables de entorno requeridas por la aplicacion.

## Configuracion de MySQL

El proyecto recomienda utilizar un usuario exclusivo para la aplicacion, en lugar de conectarse como `root`.

Ejemplo de configuracion:

```sql
CREATE USER IF NOT EXISTS 'rfid_app'@'localhost'
IDENTIFIED BY 'CONTRASENA_LOCAL';

GRANT SELECT, INSERT, UPDATE, DELETE
ON rfid_gestion_activos.*
TO 'rfid_app'@'localhost';

FLUSH PRIVILEGES;
```

Reemplace `CONTRASENA_LOCAL` por una contraseña definida en la instalacion local. No incluya contraseñas reales en el repositorio.

## Variables de entorno en Windows

La aplicacion requiere estas variables:

```text
RFID_DB_USER
RFID_DB_PASSWORD
```

Ejemplo desde CMD:

```bat
setx RFID_DB_USER "rfid_app"
setx RFID_DB_PASSWORD "CONTRASENA_LOCAL"
```

Despues de definir o modificar las variables, cierre y abra nuevamente NetBeans o la terminal para que el nuevo proceso pueda leerlas.

Para comprobar el usuario sin revelar la contraseña:

```bat
echo %RFID_DB_USER%
if defined RFID_DB_PASSWORD (echo RFID_DB_PASSWORD configurada) else (echo RFID_DB_PASSWORD no configurada)
```

## Compilacion y ejecucion en NetBeans

1. Abra Apache NetBeans.
2. Seleccione **File > Open Project**.
3. Abra la carpeta `RFIDCategoriaActivo`.
4. Espere a que Maven descargue las dependencias.
5. Haga clic derecho sobre el proyecto.
6. Seleccione **Clean and Build**.
7. Compruebe que la salida muestre `BUILD SUCCESS`.
8. Ejecute el proyecto con **Run Project**.

La clase principal configurada es:

```text
co.edu.sena.rfid.vista.Main
```

## Compilacion y ejecucion desde terminal

Desde la carpeta que contiene `pom.xml`, ejecute:

```bat
mvn clean package
mvn exec:java
```

La terminal utilizada debe tener acceso a las variables `RFID_DB_USER` y `RFID_DB_PASSWORD`.

## Menu de la aplicacion

```text
==============================================
 SISTEMA RFID - GESTION DE CATEGORIAS
==============================================
1. Registrar categoria
2. Consultar categorias
3. Actualizar categoria
4. Eliminar categoria
5. Salir
==============================================
```

## Flujo recomendado de prueba

1. Seleccione `1` y registre una categoria.
2. Seleccione `2` y verifique el registro creado.
3. Seleccione `3`, indique el ID y modifique sus datos.
4. Seleccione `2` y confirme la actualizacion.
5. Seleccione `4`, indique el ID y confirme con `S`.
6. Seleccione `2` y confirme la eliminacion.
7. Seleccione `5` para cerrar el programa.

## Pruebas realizadas

Durante el desarrollo se comprobo mediante ejecucion:

- Compilacion correcta del proyecto Maven.
- Conexion JDBC con MySQL.
- Insercion de una categoria.
- Consulta de categorias.
- Actualizacion del nombre, descripcion y estado.
- Eliminacion de una categoria.
- Persistencia de los cambios en MySQL Workbench.
- Validacion de campos obligatorios.
- Validacion de opciones numericas.
- Confirmacion previa a la eliminacion.
- Ejecucion del menu con el usuario limitado `rfid_app`.

## Seguridad

- Las credenciales no se almacenan en el codigo fuente.
- La aplicacion obtiene las credenciales mediante variables de entorno.
- Se recomienda utilizar un usuario MySQL limitado al CRUD de `rfid_gestion_activos`.
- No se deben subir contraseñas, archivos de configuracion privada ni datos sensibles al repositorio.

## Versionamiento

El proyecto utiliza Git para el control de cambios y GitHub como repositorio remoto.

El enlace del repositorio se incluye adicionalmente en el archivo requerido para la entrega:

```text
ENLACE_REPOSITORIO.txt
```

## Alcance y evolucion

Esta evidencia implementa un unico modulo funcional, `CategoriaActivo`, de acuerdo con el alcance solicitado. La estructura puede reutilizarse posteriormente para incorporar otros modulos del Sistema RFID, como activos, ubicaciones, etiquetas RFID e inventarios.

En una evolucion posterior se pueden agregar:

- Busqueda de categorias por ID.
- Desactivacion logica para conservar trazabilidad.
- Capa de servicios para reglas de negocio.
- Interfaz web.
- Pruebas automatizadas con una base exclusiva de pruebas.
- Registro estructurado de eventos y errores.

## Autor

**Cristian Camilo Betancur Alzate**  
Tecnologo en Analisis y Desarrollo de Software - ADSO  
Ficha 3336117  
Servicio Nacional de Aprendizaje - SENA
