**README - Proyecto Java con Arquitectura MVC**

# Introducción
Este proyecto sigue la arquitectura **Modelo-Vista-Controlador (MVC)**, lo que permite una mejor organización del código y facilita la escalabilidad. A continuación, se explica la estructura del proyecto, cómo se añaden nuevos casos de uso y qué hace cada archivo principal.

---
# **Estructura del Proyecto**
El código está dividido en los siguientes paquetes:

## 📂 **Integración **
Este paquete maneja la comunicación con la capa de persistencia (base de datos en JSON en este caso).
- DAOCliente.java → Se encarga de leer y escribir clientes en el archivo JSON.
- FactoriaDAO.java → Fábrica para obtener instancias de los DAO según se necesite.
## 📂 **Negocio **
Contiene la lógica de negocio, es decir, las clases encargadas de manejar los datos y reglas de la aplicación.
- **SAClienteImp.java** → Implementa la lógica para gestionar clientes (registro, acceso, etc.).
- **TransferCliente.java** → Clase que representa un cliente con sus atributos.

## 📂 **Presentacion **
Contiene las interfaces gráficas del usuario (GUIs) construidas con **Swing**.
- **GUICliente.java** → Ventana principal de gestión de clientes.
- **LoginPanel.java** → Panel para iniciar sesión.
- **RegistroPanel.java** → Panel de registro de nuevos clientes.
- **InicioPanel.java** → Pantalla inicial con opciones de acceso y registro.
- **Controlador ** (Patrón Singleton)
  Administra la comunicación entre la vista y el modelo.
  - **ControladorImp.java** → Recibe eventos de la vista y llama a la capa de negocio para procesarlos.
  - **Eventos.java** → Define los distintos eventos posibles dentro de la aplicación.
