package com.estudiandokotlin.fichero.Categories

import com.example.listas.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.listas.Categorias.task
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Categories.taskViewHolder

//creado para categoria 2 / se copia de adapter

// Esta es la clase que define el adaptador para las tareas
//class cat2adapter (private val task:List<task>) : RecyclerView.Adapter<taskViewHolder>(){ // La clase recibe una lista de tareas como parámetro y hereda de RecyclerView.Adapter con el tipo de vista taskViewHolder
class cat2adapter (var task:List<task>, private val onTaskSelected:(Int) -> Unit): RecyclerView.Adapter<taskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder { // Este método se encarga de crear las vistas para cada elemento de la lista de tareas
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fichero_task,parent,false) // Se infla el layout item_fichero_task con el contexto del padre y se obtiene una vista
        return taskViewHolder(view) // Se devuelve un objeto de tipo taskViewHolder con la vista
    }


    override fun getItemCount()= task.size // Este método devuelve el número de elementos de la lista de tareas


    override fun onBindViewHolder(holder: taskViewHolder, position: Int) { // Este método se encarga de asignar los datos de cada elemento de la lista de tareas a la vista correspondiente
        holder.render(task[position]) // Se llama al método render del objeto holder con el elemento de la lista de tareas en la posición indicada
        holder.itemView.setOnClickListener{onTaskSelected(position)}
    }
}
