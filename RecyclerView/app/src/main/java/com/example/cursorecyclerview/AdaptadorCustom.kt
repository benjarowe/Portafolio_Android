package com.example.cursorecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(val platillos: ArrayList<Platillo>, private val listener: ClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_platillo, parent, false)
        return ViewHolder(vista, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = platillos[position]
        holder.foto?.setImageResource(item.foto)
        holder.nombre?.text = item.nombre
        holder.precio?.text = "€" +  item.precio.toString()
        holder.rating?.rating = item.rating
    }

    override fun getItemCount(): Int {
        return platillos.size
    }

    fun updateData(nuevaLista: ArrayList<Platillo>) {
        platillos.clear()
        platillos.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    class ViewHolder(vista: View, listener: ClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener {
        var foto: ImageView? = vista.findViewById(R.id.ivFotos)
        var nombre: TextView? = vista.findViewById(R.id.tvNombre)
        var precio: TextView? = vista.findViewById(R.id.tvPrecio)
        var rating: RatingBar? = vista.findViewById(R.id.tvRatingBar)
        var listener: ClickListener? = listener

        init {
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onClick(v!!, adapterPosition)
        }
    }
}