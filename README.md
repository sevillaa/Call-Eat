# Call-Eat

[English](#english) · [Español](#español)

## English

Call-Eat is a team project for a restaurant management desktop application. It brings together workflows for orders, tables, dishes, staff and inventory through a Java Swing interface.

### Architecture and features

The [`Call_eat_02`](Call_eat_02) source tree separates responsibilities into three layers:

- [`Presentacion`](Call_eat_02/src/Presentacion): Swing screens and controller events, including waiter and manager views.
- [`Negocio`](Call_eat_02/src/Negocio): application services, transfer objects and business rules, such as validating table capacity.
- [`Integracion`](Call_eat_02/src/Integracion): data access and JSON persistence through Jackson.

The code includes order creation and modification, table management, dish selection, employee management and inventory screens. The [architecture notes](LEER.md) provide additional context.

### Exploring the project

The [Eclipse configuration](Call_eat_02/.classpath) targets **Java 17**, includes **JUnit 5**, and references Jackson and JCalendar libraries in `JARs`. Open `Call_eat_02` as an existing Eclipse Java project; [`Presentacion.Main`](Call_eat_02/src/Presentacion/Main.java) is the application entry point. Local JSON files and resources are located within that directory.

The [`tests`](Call_eat_02/src/tests) directory includes JUnit tests, with [order persistence tests](Call_eat_02/src/tests/PedidoTestsIntegracion.java) covering creation, lookup, modification and deletion. These tests delete and recreate `pedidos.json` in the working directory, so use a disposable copy. The repository uses Eclipse project configuration; no Maven or Gradle build is included.

## Español

Call-Eat es un proyecto en equipo para gestionar un restaurante mediante una aplicación de escritorio. Reúne flujos de pedidos, mesas, platos, empleados e inventario en una interfaz Java Swing.

### Arquitectura y funcionalidades

El código de [`Call_eat_02`](Call_eat_02) distribuye las responsabilidades en tres capas:

- [`Presentacion`](Call_eat_02/src/Presentacion): pantallas Swing y eventos del controlador, con vistas de camarero y gestor.
- [`Negocio`](Call_eat_02/src/Negocio): servicios, objetos de transferencia y reglas como la validación de capacidad de las mesas.
- [`Integracion`](Call_eat_02/src/Integracion): acceso a datos y persistencia JSON con Jackson.

Incluye creación y modificación de pedidos, gestión de mesas, selección de platos y pantallas de empleados e inventario. [LEER.md](LEER.md) amplía la explicación de la arquitectura.

### Explorar el proyecto

La [configuración de Eclipse](Call_eat_02/.classpath) utiliza **Java 17**, **JUnit 5**, Jackson y JCalendar. Abre `Call_eat_02` como proyecto Java existente de Eclipse; el punto de entrada es [`Presentacion.Main`](Call_eat_02/src/Presentacion/Main.java). Los JSON y recursos locales están en ese directorio.

Hay [pruebas JUnit](Call_eat_02/src/tests), incluidas [pruebas de persistencia de pedidos](Call_eat_02/src/tests/PedidoTestsIntegracion.java). Estas eliminan y recrean `pedidos.json` en el directorio de trabajo: utiliza una copia desechable. El repositorio utiliza la configuración de proyecto de Eclipse; no se incluye una configuración Maven o Gradle.
