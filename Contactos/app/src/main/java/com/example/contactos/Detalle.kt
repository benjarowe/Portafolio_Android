package com.example.contactos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.contactos.databinding.ActivityDetalleBinding
import com.example.contactos.Agregar

//import com.example.gridviewlistview.AppsCompleto.ContactoManager


class Detalle : AppCompatActivity(), ContactoManager.ContactoListener {
    private lateinit var binding: ActivityDetalleBinding
    // Variable global para almacenar el índice del contacto seleccionado
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?. setDisplayHomeAsUpEnabled(true)

        // Obtiene el índice del contacto seleccionado de los extras de la intención
        index = intent.getIntExtra("ID", -1)

        val contacto = ContactoManager.obtenerContactoPorID(index)
        contacto?. let {
            actualizarDetallesContacto(contacto)
        } ?: run {
            Log.e("Detalle", "No se encontró ningún contacto con el ID: $index")
        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Infla el menú de opciones en la barra de herramientas
        menuInflater.inflate(R.menu.menu_detalles, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                // Maneja la acción de retroceder en la barra de herramientas
                finish()
                return true
            }
            R.id.eliminar ->{
                // Maneja la acción de eliminar el contacto seleccionado
                val indexInt = index
                if (indexInt != -1) {
                    ContactoManager.eliminarContacto(index)
                } else {
                    Log.e("Detalle", "No se puede eliminar el contacto: ID no válido")
                }
                finish()
                return true
            }
            R.id.editar ->{
                // Maneja la acción de editar el contacto seleccionado
                val intent = Intent(this, Agregar::class.java)
                intent.putExtra("modoEditar", true)
                intent.putExtra("ID", index)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Se ejecuta cuando la actividad se reanuda
    override fun onResume() {
        super.onResume()
        // Registrar el listener para recibir actualizaciones de la lista de contactos
        ContactoManager.registerListener(this)
        // Actualizar los detalles del contacto cuando la actividad se reanuda
        val contacto = ContactoManager.obtenerContactoPorID(index)
        contacto?.let {
            actualizarDetallesContacto(contacto)
        }
    }

    // Se ejecuta cuando la actividad está a punto de pausarse
    override fun onPause() {
        super.onPause()
        // Cancela el registro del listener para dejar de recibir actualizaciones de la lista de contactos
        ContactoManager.unregisterListener(this)
    }

    override fun onContactListChanged() {
        val contacto = ContactoManager.obtenerContactoPorID(index)
        contacto?. let {
            actualizarDetallesContacto(contacto)
        }
    }

    private fun actualizarDetallesContacto(contacto: Contacto) {
        Glide.with(this)
            .load(Uri.parse(contacto.fotoUri))
            .into(binding.imagenDetalle)

        binding.nombreDetalle.text = contacto.nombre
        binding.empresDetalle.text = contacto.empresa
        binding.edadNumDetalle.text = contacto.edad.toString()
        binding.pesoNumDetalle.text = contacto.peso.toString()
        binding.telefDt.text = contacto.telefono
        binding.emailDetall.text = contacto.email
        binding.direcDetall.text = contacto.direccion
    }
}
