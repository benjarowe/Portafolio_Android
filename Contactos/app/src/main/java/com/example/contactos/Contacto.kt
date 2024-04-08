package com.example.contactos

data class Contacto(
    var nombre: String,
    var empresa: String,
    var edad: Int,
    var peso: Float,
    var direccion: String,
    var telefono: String,
    var email: String,
    var fotoUri: String
) {
    companion object {

        const val FOTO_URI_PREDETERMINADA = "android.resource://com.example.contactosp/drawable/foto_01"
        const val FOTO_URI_PREDETERMINADA_2 = "android.resource://com.example.contactosp/drawable/foto_02"
        const val FOTO_URI_PREDETERMINADA_3 = "android.resource://com.example.contactosp/drawable/foto_03"
        const val FOTO_URI_PREDETERMINADA_4 = "android.resource://com.example.contactosp/drawable/foto_04"
        const val FOTO_URI_PREDETERMINADA_5 = "android.resource://com.example.contactosp/drawable/foto_05"
        const val FOTO_URI_PREDETERMINADA_6 = "android.resource://com.example.contactosp/drawable/foto_06"
    }
}

// Gestiona la lista de contactos
object ContactoManager {
    // Lista mutable de contactos
    var contactos: ArrayList<Contacto> = ArrayList()
    // Lista de listeners interesados en cambios en la lista de contactos
    private val listeners: MutableList<ContactoListener> = mutableListOf()

    // Agrega un nuevo contacto a la lista
    fun agregarContacto(contacto: Contacto) {
        contactos.add(contacto)
    }

    // // Elimina un contacto de la lista por su índice si el índice está dentro del rango válido
    fun eliminarContacto(index: Int) {
        if (index in 0 until contactos.size) {
            contactos.removeAt(index)
        }
    }

    // Obtiene un contacto de la lista por su índice, devolviendo null si el índice está fuera de rango
    fun obtenerContactoPorID(index: Int): Contacto? {
        return if (index in 0 until contactos.size) {
            contactos[index]
        } else {
            null
        }
    }

    // Actualiza un contacto en la lista por su índice y notifica a los listeners
    fun actualizarContacto(index: Int, nuevoContacto: Contacto) {
        if (index in 0 until contactos.size) {
            contactos[index] = nuevoContacto
            notifyListeners()
        }
    }

    // Registra un listener interesado en cambios en la lista de contactos
    fun registerListener(listener: ContactoManager.ContactoListener) {
        listeners.add(listener)
    }

    // Cancela el registro de un listener de cambios en la lista de contactos
    fun unregisterListener(listener: ContactoListener) {
        listeners.remove(listener)
    }

    // // Notifica a todos los listeners registrados que la lista de contactos ha cambiado
    fun notifyListeners() {
        listeners.forEach { it.onContactListChanged() }
    }

    // Interfaz para los listeners interesados en cambios en la lista de contactos
    interface ContactoListener {
        fun onContactListChanged()
    }
}
