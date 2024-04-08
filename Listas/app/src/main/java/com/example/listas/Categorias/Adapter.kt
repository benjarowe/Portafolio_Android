package com.estudiandokotlin.fichero.Categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estudiandokotlin.R
import com.estudiandokotlin.fichero.TaskCategory
//La primera línea define una clase llamada Adapter que recibe un parámetro de tipo
// List<TaskCategory>. Este parámetro representa una lista de categorías de tareas que el
// adaptador usará para mostrar los datos en un RecyclerView
//class Adapter (private val categories:List<TaskCategory>) :
class Adapter (private val categories:List<TaskCategory>, private val onItemSelected:(Int) -> Unit) :

    //La segunda línea indica que la clase Adapter hereda de la clase
    // RecyclerView.Adapter<CategoriesViewHolder>. Esto significa que la clase Adapter
    // debe implementar los métodos necesarios para crear y vincular las vistas que se
    // mostrarán en el RecyclerView.
    RecyclerView.Adapter<CategoriesViewHolder> () {

    //La cuarta línea sobrescribe el método onCreateViewHolder, que se encarga de crear un
    // objeto de tipo CategoriesViewHolder a partir de una vista inflada del layout que se
    // usará para cada elemento del RecyclerView. El método recibe dos parámetros: parent,
    // que es el ViewGroup al que pertenece el RecyclerView, y viewType, que es un entero
    // que indica el tipo de vista que se debe crear. El método debe devolver un objeto de
    // tipo CategoriesViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        //La primera línea crea una variable llamada view que almacena el resultado de
        // inflar una vista a partir del layout item_task_category.xml. Para ello, usa
        // el método LayoutInflater.from, que recibe el contexto de la vista padre
        // (parent.context), y el método inflate, que recibe el id del layout
        // (R.layout.item_task_category), el ViewGroup al que pertenece la vista (parent)
        // y un booleano que indica si se debe adjuntar la vista al ViewGroup (false).
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category,parent,false)
        return CategoriesViewHolder(view)

    }

    //La séptima línea sobrescribe el método onBindViewHolder, que se encarga de asignar los
    // datos de la lista de categorías al CategoriesViewHolder correspondiente. El método
    // recibe dos parámetros: holder, que es el CategoriesViewHolder que se creó en el
    // método anterior, y position, que es un entero que indica la posición del elemento en
    // la lista. El método no devuelve nada.
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected)
    }

    //La décima línea sobrescribe el método getItemCount, que se encarga de devolver el
    // número de elementos que tiene la lista de categorías. El método no recibe ningún
    // parámetro y devuelve un entero igual al tamaño de la lista.
    override fun getItemCount() = categories.size

}