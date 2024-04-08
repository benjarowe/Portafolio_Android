package com.estudiandokotlin.fichero.Categories

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.estudiandokotlin.R
import com.estudiandokotlin.fichero.TaskCategory

class CategoriesViewHolder (view:View): RecyclerView.ViewHolder(view) {

    private val CategorieName:TextView = view.findViewById(R.id.CategorieName)
    private val divider:View =view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)


    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit){

        val color = if (taskCategory.isSelected){
            R.color.colorDeCarta
        }else{
            R.color.colorDetext
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener{onItemSelected(layoutPosition)}

        when(taskCategory){
            TaskCategory.Business ->{
                CategorieName.text = " Negocio"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.bussiness_category)
                )
            }
            TaskCategory.Other ->{
                CategorieName.text = " Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
            TaskCategory.Personal ->{
                CategorieName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
        }
    }
}