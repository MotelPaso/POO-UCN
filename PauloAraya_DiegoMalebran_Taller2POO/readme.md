# Taller 2 POO: Sistema de Seguridad de Red (SecureNet LTDA)

Este taller se centra en el diseño de un sistema para simular la gestión de seguridad de una red informática. El objetivo fue aplicar los pilares fundamentales de la Programación Orientada a Objetos (POO) a través de la creación de múltiples clases interconectadas.

## Funcionalidad Principal

El programa simula un sistema de escaneo y gestión de equipos en una red, con acceso diferenciado por roles (ADMIN y USER).

* **Gestión de Usuarios:** Implementa una clase `User` con validación de credenciales (usando SHA-256 y Base64) y roles.
* **Modelado de Red:**
    * **Clase `Puerto`:** Modela un puerto de red y sus vulnerabilidades.
    * **Clase `PC`:** Modela un computador, con su IP, Sistema Operativo y una lista de puertos asociados (abiertos y cerrados).
* **Análisis de Riesgo:** La clase `PC` es capaz de calcular automáticamente su **clase de red** (A, B, C) basada en su IP y su **nivel de riesgo** (Bajo, Medio, Alto) basado en la cantidad de puertos abiertos.
* **Escaneo y Reporte:** El menú de usuario permite "escanear" un PC, calculando su riesgo y generando un reporte detallado que se guarda en el archivo `reportes.txt`.
* **Administración:** El menú de administrador permite listar, añadir, eliminar y clasificar PCs por nivel de riesgo.

## Conceptos de POO

* **Clases y Objetos:** Creación de las clases `User`, `PC` y `Puerto` para modelar las entidades del sistema.
* **Encapsulamiento:** El uso de atributos `private` y métodos *getters* y *setters* para controlar el acceso y la modificación de los datos internos.
* **Relaciones entre Objetos:** La clase `PC` contiene una lista (`ArrayList`) de objetos `Puerto`, demostrando la relación de composición.
* **Listas Dinámicas:** Uso de `ArrayList` para gestionar objetos (`listaUsuarios`, `listaPC`, `listaPuertos`).
