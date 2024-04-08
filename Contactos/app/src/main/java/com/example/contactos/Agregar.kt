package com.example.contactos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.contactos.databinding.ActivityAgregarBinding
import java.lang.ref.WeakReference

class Agregar : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarBinding
    private lateinit var foto: ImageView
    private var selectedImageUri: Uri? = null
    private var modoEditar: Boolean = false
    private val CODIGO_PERMISO = 123
    private val CODIGO_PERMISO_LECTURA = 124
    private val REQUEST_EXTERNAL_STORAGE = 1
    private var dialogo: AlertDialog? = null
    private var activityRef: WeakReference<AppCompatActivity>? = null

    // Arreglo de recursos de imágenes predeterminadas
    private val fotos = arrayOf(
        R.drawable.foto_01, R.drawable.foto_02, R.drawable.foto_03,
        R.drawable.foto_04, R.drawable.foto_05, R.drawable.foto_06
    )

    // Permiso para seleccionar una imagen de la galería
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    selectedImageUri = uri
                    cargarImagenSeleccionada()
                }
            }
        }
    // Método para verificar y solicitar permisos
    private fun verifyStoragePermissions(activity: Activity) {
        val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activityRef = WeakReference(this)

        // Llamar a este método desde tu actividad, por ejemplo, desde onCreate()
        verifyStoragePermissions(this)

        foto = binding.imagenDetalle
        foto.setOnClickListener {
            seleccionarFoto()
        }

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Verifica si estamos en modo de edición
        modoEditar = intent.getBooleanExtra("modoEditar", false)
        if (modoEditar) {
            val id = intent.getIntExtra("ID", -1)
            if (id != -1) {
                val contactoExistente = ContactoManager.obtenerContactoPorID(id)
                if (contactoExistente != null) {
                    mostrarDetallesContacto(contactoExistente)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dialogo?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityRef?.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.aceptar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.iAceptarEditar -> {
                guardarContacto()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun guardarContacto() {
        val campos = listOf(
            binding.nombreDetalle.text.toString(),
            binding.empresDetalle.text.toString(),
            binding.edadNumDetalle.text.toString(),
            binding.pesoNumDetalle.text.toString(),
            binding.telefDt.text.toString(),
            binding.emailDetall.text.toString(),
            binding.direcDetall.text.toString()
        )
        if (campos.all { it.isNotEmpty() }) {
            val nuevoContacto = crearContacto(campos)
            if (nuevoContacto != null) {
                actualizarOAgregarContacto(nuevoContacto)
                // Aquí se pasa la URI de la imagen a la actividad de la lista
                val intent = Intent()
                intent.putExtra("imagen_uri", nuevoContacto.fotoUri)
                setResult(Activity.RESULT_OK, intent)
                finish()
                return
            }
        } else {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun crearContacto(campos: List<String>): Contacto? {
        return if (selectedImageUri != null) {
            Contacto(
                campos[0], campos[1], campos[2].toInt(), campos[3].toFloat(),
                campos[4], campos[5], campos[6], fotoUri = selectedImageUri.toString()
            )
        } else {
            val id = intent.getIntExtra("ID", -1)
            val contactoExistente = ContactoManager.obtenerContactoPorID(id)
            if (contactoExistente != null) {
                seleccionarFoto()
                Contacto(
                    campos[0], campos[1], campos[2].toInt(), campos[3].toFloat(),
                    campos[4], campos[5], campos[6], contactoExistente.fotoUri
                )
            } else {
                null
            }
        }
    }

    private fun actualizarOAgregarContacto(nuevoContacto: Contacto) {
        if (modoEditar) {
            val id = intent.getIntExtra("ID", -1)
            if (id != -1) {
                val contactoExistente = ContactoManager.obtenerContactoPorID(id)
                if (contactoExistente != null) {
                    contactoExistente.apply {
                        nombre = nuevoContacto.nombre
                        empresa = nuevoContacto.empresa
                        edad = nuevoContacto.edad
                        peso = nuevoContacto.peso
                        telefono = nuevoContacto.telefono
                        email = nuevoContacto.email
                        direccion = nuevoContacto.direccion
                        fotoUri = nuevoContacto.fotoUri
                    }
                    ContactoManager.actualizarContacto(id, contactoExistente)
                }
            }
        } else {
            ContactoManager.agregarContacto(nuevoContacto)
        }
    }

    private fun seleccionarFoto() {
        activityRef?.get()?.let { activity ->
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    CODIGO_PERMISO_LECTURA
                )
            } else {
                solicitarPermisos()
                // Llamar a la función para abrir la galería utilizando ACTION_OPEN_DOCUMENT
                abrirGaleria()
            }
        }
    }


    private fun mostrarOpcionesSeleccionFoto() {
        val opciones = arrayOf("Fotos Predeterminadas", "Galería de Fotos")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Elegir Foto")
        builder.setItems(opciones) { dialog, which ->
            when (which) {
                0 -> mostrarListaImagenesPredeterminadas()
                1 -> abrirGaleria()
            }
        }
        builder.show()
    }

    // Método para mostrar la lista de imágenes predeterminadas
    private fun mostrarListaImagenesPredeterminadas() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Imagen Predeterminada")

        // Lista de nombres de imágenes predeterminadas
        val nombresImagenes =
            arrayOf("Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4", "Imagen 5", "Imagen 6")

        builder.setItems(nombresImagenes) { dialog, which ->
            if (which >= 0 && which < fotos.size) {
                // Actualiza la URI de la imagen seleccionada
                selectedImageUri = Uri.parse("android.resource://${packageName}/${fotos[which]}")
                // Carga la imagen seleccionada en la vista
                cargarImagenSeleccionada()
            } else {
                // Muestra un mensaje de error si el índice está fuera de rango
                Toast.makeText(this, "Índice de imagen predeterminada fuera de rango", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    // Método para abrir la galería de fotos y seleccionar una imagen
    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val chooserIntent = Intent.createChooser(intent, "Seleccione una imagen")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentGallery))
        pickImageLauncher.launch(chooserIntent)
    }

    // Carga la imagen seleccionada en la vista
    private fun cargarImagenSeleccionada() {
        selectedImageUri?.let { uri ->
            Glide.with(this).load(uri).into(binding.imagenDetalle)
        }
    }

    private fun solicitarPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), CODIGO_PERMISO_LECTURA)
        }
        // Puedes solicitar más permisos según sea necesario
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CODIGO_PERMISO_LECTURA -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permiso concedido, puedes acceder a la galería
                    abrirGaleria()
                } else {
                    // Permiso denegado, muestra un mensaje al usuario
                    Toast.makeText(this, "Para acceder a la galería, necesitas conceder el permiso de lectura de almacenamiento externo. ¿Deseas hacerlo ahora?", Toast.LENGTH_LONG).show()
                }
                return
            }
            // Puedes manejar más casos de solicitud de permisos aquí si es necesario
        }
    }

    // Muestra los detalles de un contacto existente en modo de edición
    private fun mostrarDetallesContacto(contacto: Contacto) {
        with(binding) {
            nombreDetalle.setText(contacto.nombre)
            empresDetalle.setText(contacto.empresa)
            edadNumDetalle.setText(contacto.edad.toString())
            pesoNumDetalle.setText(contacto.peso.toString())
            telefDt.setText(contacto.telefono)
            emailDetall.setText(contacto.email)
            direcDetall.setText(contacto.direccion)
            // Cargar la imagen del contacto existente en la vista
            if (!contacto.fotoUri.isNullOrEmpty()) {
                Glide.with(this@Agregar).load(Uri.parse(contacto.fotoUri)).into(imagenDetalle)
            }
        }
    }
}

