package com.example.listas.Categories

import android.view.View
import com.example.listas.R
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Categorias.TaskCategory

// ViewHolder para elementos de categorías en el RecyclerView.
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val CategorieName: TextView = view.findViewById(R.id.CategorieName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    // Método para renderizar una categoría y configurar su apariencia.
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // Determina el color de fondo basado en si la categoría está seleccionada o no.
        val color = if (taskCategory.isSelected) {
            R.color.colorDeCarta
        } else {
            R.color.colorDetext
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        // Escucha los clics en la vista y notifica la posición seleccionada.
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        // Configura el nombre de la categoría y el color del separador basado en el tipo de categoría.
        when (taskCategory) {
            TaskCategory.Business -> {
                CategorieName.text = " Negocio"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.bussiness_category)
                )
            }
            TaskCategory.Other -> {
                CategorieName.text = " Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
            TaskCategory.Personal -> {
                CategorieName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
            else -> { } // No hace nada para otros casos
        }
    }
}
