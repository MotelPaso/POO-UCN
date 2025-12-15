# Taller 3 POO: Sistema de Gestión de Proyectos y Tareas

Este taller representa una evolución en el nivel de complejidad y diseño, enfocándose en la implementación de **Patrones de Diseño de Software** para modelar un sistema de gestión de proyectos.

## Funcionalidad Principal

El sistema gestiona **Proyectos** y **Tareas**. Las tareas pueden ser de diferentes tipos, como `Bug`, `Feature` o `Documentacion`. Los usuarios se dividen en `Administrador` y `Colaborador`.

## Conceptos de POO y Patrones de Diseño

* **Herencia y Polimorfismo:** Las clases de tareas (`Bug`, `Feature`, `Documentacion`) heredan de una clase base `Tarea`, y las clases de usuario (`Administrador`, `Colaborador`) heredan de `Usuario`.
* **Patrón Factory (Fábrica):** Se usa para crear objetos de la familia `Usuario` (`FactoryUsuarios.java`) y `Tarea` (`FactoryTareas.java`) sin necesidad de especificar el tipo exacto en el código cliente.
* **Patrón Strategy (Estrategia):** Permite cambiar el algoritmo de priorización de tareas en tiempo de ejecución, ordenando las listas utilizando **BucketSort**. Se define una interfaz `PrioridadStrategy` con implementaciones concretas como:
    * `PrioridadComplejidad.java`
    * `PrioridadFecha.java`
    * `PrioridadImpacto.java`
* **Patrón Visitor (Visitante):** Se utiliza para realizar operaciones sobre la jerarquía de objetos `Tarea` (Bug, Feature, Documentacion) sin modificar sus clases.
