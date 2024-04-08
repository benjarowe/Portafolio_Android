package com.example.contactosp.general

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.contactosp.R
import com.example.contactosp.databinding.ActivityDetallesBinding

class Detalles : AppCompatActivity(), ContactoManager.ContactoListener {
    private lateinit var binding: ActivityDetallesBinding
    private var index: Int = -1 // Indice del contacto seleccionado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?. setDisplayHomeAsUpEnabled(true)

        // Obtiene el índice del contacto seleccionado de los extras de la intención
        index = intent.getIntExtra("ID", -1)

        // Obtiene el contacto por su ID y actualiza los detalles del contacto si se encuentra
        val contacto = ContactoManager.obtenerContactoPorID(index)
        if (contacto == null) {
            Log.e("Detalle", "No se encontró ningún contacto con el ID: $index")
            finish()
            return
        }
        actualizarDetallesContacto(contacto)
    }

    // Infla el menú de opciones en la barra de herramientas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalles, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.eliminar ->{
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
                // Edita el contacto seleccionado
                val intent = Intent(this, Agregar::class.java)
                intent.putExtra("modoEditar", true)
                intent.putExtra("ID", index)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        // Registra el listener para recibir actualizaciones de la lista de contactos
        ContactoManager.registerListener(this)
        // Actualiza los detalles del contacto cuando la actividad se reanuda
        val contacto = ContactoManager.obtenerContactoPorID(index)
        contacto?.let {
            actualizarDetallesContacto(contacto)
        }
    }

    override fun onPause() {
        super.onPause()
        ContactoManager.unregisterListener(this)
    }

    // Se ejecuta cuando la lista de contactos ha cambiado
    override fun onContactListChanged() {
        val contacto = ContactoManager.obtenerContactoPorID(index)
        contacto?. let {
            // Actualiza los detalles del contacto actual si aún existe en la lista
            actualizarDetallesContacto(contacto)
        }
    }

    private fun actualizarDetallesContacto(contacto: Contacto) {
        Glide.with(this)
            .load(Uri.parse(contacto.fotoUri))
            .placeholder(R.drawable.foto_06) // Imagen de relleno mientras carga la imagen
            .error(R.drawable.foto_05) // Imagen de error si no se puede cargar la imagen
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
