package com.example.contactosp.general

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
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
import com.example.contactosp.R
import com.example.contactosp.databinding.ActivityAgregarBinding
import java.lang.ref.WeakReference

class Agregar : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarBinding
    private lateinit var foto: ImageView
    private var selectedImageUri: Uri? = null
    private val CODIGO_PERMISO_LECTURA = 124
    private var modoEditar: Boolean = false
    private var dialogo: AlertDialog? = null
    private var activityRef: WeakReference<AppCompatActivity>? = null

    // Imágenes predeterminadas
    private val fotos = arrayOf(
        R.drawable.foto_01, R.drawable.foto_02, R.drawable.foto_03,
        R.drawable.foto_04, R.drawable.foto_05, R.drawable.foto_06
    )

    // Registro para lanzar la actividad de selección de imagen y obtener el resultado
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    selectedImageUri = uri
                    cargarImagenSeleccionada()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activityRef = WeakReference(this)

        foto = binding.imagenDetalle
        foto.setOnClickListener {
            seleccionarFoto()
        }

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Si estamos en modo de edición, obtiene el ID del contacto y muestra sus detalles
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

    // Limpiar diálogo en pausa
    override fun onPause() {
        super.onPause()
        dialogo?.dismiss()
    }

    // Limpiar referencia débil en destrucción de la actividad
    override fun onDestroy() {
        super.onDestroy()
        activityRef?.clear()
    }

    // Infla el menú de opciones "aceptar" en la barra de acciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.aceptar, menu)
        return true
    }

    // Maneja la selección de elementos del menú de opciones en la barra de acciones
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

    // Guardar contacto y manejar la finalización de la actividad
    private fun guardarContacto() {
        val campos = obtenerCamposContacto()

        if (campos.all { it.isNotEmpty() }) {
            val nuevoContacto = crearContacto(campos)
            if (nuevoContacto != null) {
                actualizarOAgregarContacto(nuevoContacto)
                // Pasa la URI de la imagen a la actividad de lista y finaliza la actividad actual
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

    // Obtener campos de contacto desde vistas
    private fun obtenerCamposContacto(): List<String> {
        return with(binding) {
            listOf(
                nombreDetalle.text.toString(),
                empresDetalle.text.toString(),
                edadNumDetalle.text.toString(),
                pesoNumDetalle.text.toString(),
                telefDt.text.toString(),
                emailDetall.text.toString(),
                direcDetall.text.toString()
            )
        }
    }

    // Crear objeto de contacto con los campos proporcionados
    private fun crearContacto(campos: List<String>): Contacto? {
        return if (selectedImageUri != null) {
            // Si se seleccionó una imagen, crea el contacto con la URI de la imagen seleccionada.
            Contacto(
                campos[0], campos[1], campos[2].toInt(), campos[3].toFloat(),
                campos[4], campos[5], campos[6], fotoUri = selectedImageUri.toString()
            )
        } else {
            // Si no se seleccionó una imagen, verifica si hay un contacto existente.
            val id = intent.getIntExtra("ID", -1)
            val contactoExistente = ContactoManager.obtenerContactoPorID(id)
            if (contactoExistente != null) {
                // Si existe un contacto, selecciona una foto y crea el contacto con la URI de la foto existente.
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


    // Actualiza o agrega contacto según el modo de edición
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

    // Manejar selección de foto
    private fun seleccionarFoto() {
        solicitarPermisoGaleria()
    }

    // Solicita permiso para acceder a la galería de fotos
    private fun solicitarPermisoGaleria() {
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
                // Permiso ya concedido, mostrar opciones de selección de foto
                mostrarOpcionesSeleccionFoto()
            }
        }
    }

    // Mostrar diálogo para elegir entre fotos predeterminadas y galería
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

    // Lista de imágenes predeterminadas para elegir
    private fun mostrarListaImagenesPredeterminadas() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar Imagen Predeterminada")

        // Imágenes predeterminadas
        val nombresImagenes =
            arrayOf("Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4", "Imagen 5", "Imagen 6")

        builder.setItems(nombresImagenes) { dialog, which ->
            if (which >= 0 && which < fotos.size) {
                // Actualiza URI de imagen seleccionada y cargar en la vista
                selectedImageUri = Uri.parse("android.resource://${packageName}/${fotos[which]}")
                cargarImagenSeleccionada()
            } else {
                Toast.makeText(
                    this,
                    "Índice de imagen predeterminada fuera de rango",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.show()
    }

    // Galería para seleccionar imagen
    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val chooserIntent = Intent.createChooser(intent, "Seleccione una imagen")
        pickImageLauncher.launch(chooserIntent)
    }

    // Maneja la respuesta de la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CODIGO_PERMISO_LECTURA -> {
                // Verifica si se concedió el permiso de lectura
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permiso concedido, mostrar opciones de selección de foto
                    mostrarOpcionesSeleccionFoto()
                } else {
                    mostrarMensajeHabilitarPermiso()
                }
                return
            }
        }
    }

    // Método para mostrar un mensaje y guiar al usuario para habilitar el permiso desde la configuración de la aplicación
    private fun mostrarMensajeHabilitarPermiso() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permiso necesario")
        builder.setMessage("Para acceder a la galería de fotos, necesitas conceder el permiso de lectura de almacenamiento externo. Puedes habilitar el permiso desde la configuración de la aplicación.")
        builder.setPositiveButton("Abrir configuración") { dialog, which ->
            abrirConfiguracionAplicacion()
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
        }
        builder.show()
    }

    // Abre la configuración de la aplicación
    private fun abrirConfiguracionAplicacion() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    // Carga la imagen seleccionada en la vista
    private fun cargarImagenSeleccionada() {
        selectedImageUri?.let { uri ->
            Glide.with(this).load(uri).into(binding.imagenDetalle)
        }
    }

    // Método para mostrar detalles de un contacto existente en modo de edición
    private fun mostrarDetallesContacto(contacto: Contacto) {
        with(binding) {
            // Establece los detalles del contacto en las vistas correspondientes
            nombreDetalle.setText(contacto.nombre)
            empresDetalle.setText(contacto.empresa)
            edadNumDetalle.setText(contacto.edad.toString())
            pesoNumDetalle.setText(contacto.peso.toString())
            telefDt.setText(contacto.telefono)
            emailDetall.setText(contacto.email)
            direcDetall.setText(contacto.direccion)
            // Carga la imagen del contacto existente en la vista
            if (!contacto.fotoUri.isNullOrEmpty()) {
                Glide.with(this@Agregar).load(Uri.parse(contacto.fotoUri)).into(imagenDetalle)
            }
        }
    }
}
