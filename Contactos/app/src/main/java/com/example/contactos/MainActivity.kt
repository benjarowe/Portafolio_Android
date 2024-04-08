package com.example.contactos

import android.Manifest
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA_2
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA_3
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA_4
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA_5
import com.example.contactos.Contacto.Companion.FOTO_URI_PREDETERMINADA_6


import com.example.contactos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lista: ListView? = null // Variable para la ListView
    private var adaptador: AdaptadorCustom? = null // Variable para el adaptador de la ListView
    private val REQUEST_CODE_AGREGAR = 1001 // Puedes usar cualquier valor entero que desees

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbarMenu
        setSupportActionBar(toolbar)

        // Contactos predeterminados
        if (ContactoManager.contactos.isNullOrEmpty()) {
            ContactoManager.agregarContacto(Contacto("roman", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA ))
            ContactoManager.agregarContacto(Contacto("flaco", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA_2 ))
            ContactoManager.agregarContacto(Contacto("malala", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA_3 ))
            ContactoManager.agregarContacto(Contacto("venecia", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA_4 ))
            ContactoManager.agregarContacto(Contacto("olivia", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA_5 ))
            ContactoManager.agregarContacto(Contacto("moshka", "Rivas", 5, 25.2f, "70.0F", "Richieri 2933", "55 64621681", FOTO_URI_PREDETERMINADA_6 ))
        }

        lista = binding.lista
        adaptador = AdaptadorCustom(this, ContactoManager.contactos ?: ArrayList())
        lista?.adapter = adaptador // Establecer el adaptador en la ListView

        // elemento de la ListView para abrir la actividad de detalles
        lista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("ID", position)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        adaptador?.notifyDataSetChanged() // Notifica al adaptador cuando se reanuda la actividad
    }

    // Maneja el resultado de agregar un contacto.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == Activity.RESULT_OK) {
            val imagenUri = data?.getStringExtra("imagen_uri")
            val idContacto = data?.getIntExtra("ID", -1)
            if (idContacto != null && idContacto != -1) {
                val contacto = ContactoManager.obtenerContactoPorID(idContacto) // Actualiza la foto del contacto con la URI recibida, o usa una predeterminada
                contacto?.fotoUri = imagenUri ?: FOTO_URI_PREDETERMINADA // Si la URI es nula, se utiliza la URI predeterminada
                adaptador?.notifyDataSetChanged()
            }
        }
    }
    // Infla el menú definido en "menu_main.xml" en la barra de acciones.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Maneja la selección de elementos del menú de opciones en la barra de acciones.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iNuevo -> {
                val intent = Intent(this, Agregar::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

