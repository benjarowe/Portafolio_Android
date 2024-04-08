package com.example.listas.Categories

import com.example.listas.R
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Categorias.TaskCategory

// Adapter para mostrar una lista de categorías de tareas en un RecyclerView.
class Adapter(
    private val categories: List<TaskCategory>, // Lista de categorías de tareas
    private val onItemSelected: (Int) -> Unit // Acción a realizar cuando se selecciona un elemento
) : RecyclerView.Adapter<CategoriesViewHolder>() {

    // Crea un nuevo CategoriesViewHolder inflando la vista de un elemento de categoría.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    // Vincula los datos de la lista de categorías al CategoriesViewHolder correspondiente.
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected)
    }

    // Devuelve el número de elementos en la lista de categorías.
    override fun getItemCount() = categories.size
}
