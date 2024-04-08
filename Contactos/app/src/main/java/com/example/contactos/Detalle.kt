package com.example.gridviewlistview.AppsCompleto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.gridviewlistview.R
import com.example.gridviewlistview.databinding.ActivityDetalleBinding

private lateinit var binding: ActivityDetalleBinding

// Variable global para almacenar el índice del contacto seleccionado
var index = 0

class Detalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtiene el índice del contacto seleccionado de los extras de la intención
        index = intent.getIntExtra("ID", -1)

        // Obtiene el contacto correspondiente al índice
        val contacto = ContactoManager.contactos?.getOrNull(index)
        contacto?.let {
            // Si el contacto no es nulo, asigna sus datos a las vistas usando ViewBinding
            binding.imagenDetalle.setImageResource(contacto.foto)
            binding.nombreDetalle.text = contacto.nombre
            binding.empresDetalle.text = contacto.empresa
            binding.edadNumDetalle.text = contacto.edad.toString()
            binding.pesoNumDetalle.text = contacto.peso.toString()
            binding.telefDt.text = contacto.telefono
            binding.emailDetall.text = contacto.email
            binding.direcDetall.text = contacto.direccion
        } ?: run {
            // Si el contacto es nulo, muestra un mensaje de error en el registro
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
                    ContactoManager.eliminarContacto(indexInt.toString())
                } else {
                    Log.e("Detalle", "No se puede eliminar el contacto: ID no válido")
                }
                finish()
                return true
            }
            R.id.editar ->{
                // Maneja la acción de editar el contacto seleccionado
                val intent = Intent(this, NuevoAgregar::class.java)
                intent.putExtra("ID", index.toString())
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
