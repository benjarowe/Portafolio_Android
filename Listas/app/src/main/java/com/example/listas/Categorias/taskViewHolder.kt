package com.example.listas.Categories

import android.view.View
import com.example.listas.R
import android.graphics.Paint
import android.widget.CheckBox
import android.widget.TextView
import android.content.res.ColorStateList
import com.example.listas.Categorias.task
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Categorias.TaskCategory

//creado para categoria 2 / se copia de categoriesviewHolder
class taskViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: task){

        if (task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        val color = when(task.category){
            TaskCategory.Business -> R.color.bussiness_category
            TaskCategory.Personal -> R.color.personal_category
            TaskCategory.Other -> R.color.other_category
            else -> {}
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color as Int)
        )

    }
}