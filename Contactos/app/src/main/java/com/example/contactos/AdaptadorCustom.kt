package com.example.gridviewlistview.AppsCompleto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gridviewlistview.R
import com.example.gridviewlistview.databinding.TemplateContactoBinding

// Definición de la clase AdaptadorCustom que extiende de BaseAdapter y recibe un contexto y una lista de contactos
class AdaptadorCustom(private val contexto: Context, private var items: ArrayList<Contacto>) : BaseAdapter() {
    // Propiedad que almacena una copia de la lista original de contactos
    private var copiaItems: ArrayList<Contacto> = ArrayList(items)

    // Método que devuelve el número de elementos en la lista de contactos
    override fun getCount(): Int {
        return items.size
    }

    // Método que devuelve el objeto en la posición especificada en la lista de contactos
    override fun getItem(position: Int): Any {
        return items[position]
    }

    // Método que devuelve el ID de un elemento en la posición especificada
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Método que infla y devuelve la vista de cada elemento en la lista de contactos
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var vista: View? = convertView

        // Si la vista es nula, se infla utilizando el LayoutInflater
        if (vista == null) {
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_contacto, parent, false)
            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder
        } else {
            viewHolder = vista.tag as ViewHolder
        }

        // Se obtiene el objeto Contacto correspondiente a la posición actual
        val item = getItem(position) as Contacto

        // Se asignan los valores del Contacto a las vistas correspondientes en el ViewHolder
        viewHolder?.nombre?.text = item.nombre
        viewHolder?.empresa?.text = item.empresa
        viewHolder?.foto?.setImageResource(item.foto)

        return vista!!
    }

    // Método utilizado para filtrar la lista de contactos
    fun filtrar(str: String) {
        items.clear() // Limpiar la lista actual de contactos
        if (str.isEmpty()) {
            items.addAll(copiaItems) // Restaurar la lista original si la cadena de búsqueda está vacía
        } else {
            val busqueda = str.toLowerCase()
            for (item in copiaItems) {
                val nombre = item.nombre.toLowerCase()
                if (nombre.contains(busqueda)) {
                    items.add(item) // Agregar elementos que coincidan con la búsqueda a la lista filtrada
                }
            }
        }
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }

    // Clase interna ViewHolder para contener las vistas de cada elemento en la lista
    private class ViewHolder(vista: View) {
        var nombre: TextView = vista.findViewById(R.id.tvNombre)
        var foto: ImageView = vista.findViewById(R.id.ivFoto)
        var empresa: TextView = vista.findViewById(R.id.tvEmpresa)
    }
}

// Ajusta la escala de la imagen
        //binding.ivFoto.scaleType = ImageView.ScaleType.CENTER_INSIDE

        // Imprime el ID de la imagen en la consola
        //Log.d("Recursos", "ID de la imagen: ${item.foto}")
