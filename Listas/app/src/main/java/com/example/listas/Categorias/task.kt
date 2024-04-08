package com.example.listas.Categorias


// Modelo de datos para una tarea en la lista de categorías.
data class task(
    val name: String, // Nombre de la tarea
    val category: TaskCategory, // Categoría de la tarea
    var isSelected: Boolean = false // Indica si la tarea está seleccionada (por defecto, falso)
)


/*
//creado para categoria 2 lista (taskCategorie)
// Esta es la clase que define el modelo de datos para las tareas
data class task (val name: String, val category: TaskCategory, var isSelected:Boolean= false){
// La clase es de tipo data, lo que significa que se enfoca en almacenar datos y se generan automáticamente los métodos toString,
// equals, hashCode y copy. La clase tiene tres atributos: el nombre de la tarea, la categoría de la tarea y un booleano que
// indica si la tarea está seleccionada o no. El atributo isSelected tiene un valor por defecto de false.
}*/