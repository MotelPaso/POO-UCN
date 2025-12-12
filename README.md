# Taller 04 - Programación Orientada a Objetos

## Descripción del Proyecto
La Universidad Católica del Mish ha lanzado un ambicioso programa de Certificaciones Profesionales
en Tecnología, diseñado para complementar la formación de grado con competencias
especializadas demandadas por la industria tecnológica.

Actualmente, más de 400 estudiantes de las carreras de Ingeniería en TI e Ingeniería Civil en
Computación participan en tres líneas de certificación:
- Desarrollo de Software (DevOps, Arquitectura, Full-Stack)
- Sistemas Inteligentes (IA, Machine Learning, Big Data)
- Ciberseguridad (Ethical Hacking, Criptografía, Análisis Forense)

## Integrantes
| Nombre Completo | RUT | 
| :--- | :--- | 
| Paulo Araya Rojo | 21.918.080-2 | 
| Diego Malebran | 21.661.740-1 | 

## 🏗️ Estructura del Proyecto
El proyecto sigue una arquitectura de tres capas (Dominio, Lógica, Presentación):

* **`src/dominio`**: Contiene la clase principal `App.java`, la interfaz `Sistema.java` y su implementación `SistemaImpl.java` (Singleton).
* **`src/logica`**: Contiene las clases del modelo de negocio (ej. `Usuario`, `Estudiante`, `Curso`, `Certificacion`) y el patrón Factory (`FactoryUsuarios.java`).
* **`src/presentacion`**: Contiene la interfaz gráfica de usuario (`GUI.java`) implementada con Java Swing.

## 🔨 Patrones de Diseño Implementados
Se implementaron los siguientes patrones:\
Singleton,\
Factory,\
Strategy,\
Visitor

## 🚀 Instrucciones de Ejecución
1.  Importe el proyecto como un proyecto Java a su IDE favorita.
2.  Verifique que los archivos `.txt` (usuarios.txt, estudiantes.txt, notas.txt, etc.) se encuentren en la raíz del proyecto para que puedan ser leídos por la aplicacion.
3.  Ejecute la clase `App.java`.
4.  El sistema de inicio de sesión aparecerá. Use las credenciales de los archivos usuarios.txt y estudiantes.txt.
