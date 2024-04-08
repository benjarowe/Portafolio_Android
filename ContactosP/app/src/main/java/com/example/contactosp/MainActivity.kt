package com.example.contactosp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.contactosp.databinding.ActivityMainBinding
import com.example.contactosp.general.AdaptadorCustom
import com.example.contactosp.general.Agregar
import com.example.contactosp.general.Contacto
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA_2
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA_3
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA_4
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA_5
import com.example.contactosp.general.Contacto.Companion.FOTO_URI_PREDETERMINADA_6
import com.example.contactosp.general.ContactoManager
import com.example.contactosp.general.Detalles

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adaptador: AdaptadorCustom? = null // Adaptador de la ListView
    private val REQUEST_CODE_AGREGAR = 1001
    private var lista: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbarMenu
        setSupportActionBar(toolbar)

        // Contactos predeterminados
        ContactoManager.contactos.apply {
            if (isNullOrEmpty()) {
                add(Contacto("roman", "Rivas", 15, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA ))
                add(Contacto("flaco", "Rivas", 25, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA_2 ))
                add(Contacto("malala", "Rivas", 35, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA_3 ))
                add(Contacto("venecia", "Rivas", 55, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA_4 ))
                add(Contacto("olivia", "Rivas", 75, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA_5 ))
                add(Contacto("moshka", "Rivas", 85, 25.2f, "Richieri 2933", "55 64621681", "benjarowe90@gmail.com", FOTO_URI_PREDETERMINADA_6 ))
            }
        }

        lista = binding.lista
        adaptador = AdaptadorCustom(this, ContactoManager.contactos)
        lista?.adapter = adaptador // Establecer el adaptador en la ListView

        // elemento de la ListView para abrir la actividad de detalles
        lista?.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, Detalles::class.java)
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
        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK) {
            val imagenUri = data?.getStringExtra("imagen_uri")
            val idContacto = data?.getIntExtra("ID", -1)
            idContacto?.let { id ->
                val contacto = ContactoManager.obtenerContactoPorID(id)
                contacto?.fotoUri = imagenUri ?: Contacto.FOTO_URI_PREDETERMINADA
                adaptador?.notifyDataSetChanged()
            }
        }
    }


    // Infla el menú definido en "menu_main.xml" en la barra de acciones.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Maneja la selección de elementos del menú de opciones en la barra de acciones.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.iNuevo -> {
                startActivity(Intent(this, Agregar::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
