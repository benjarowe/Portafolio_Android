package com.example.gridviewlistview.AppsCompleto

class Contacto(nombre:String, empresa:String, edad:Int, peso:Float, direccion:String, telefono:String, email:String, foto: Int) {
    var nombre: String = nombre
    var empresa: String = empresa
    var edad: Int = edad
    var peso: Float = peso
    var direccion: String = direccion
    var telefono: String = telefono
    var email: String = email
    var foto: Int = foto
}
// Es una clase de utilidad que gestiona la lista de contactos. Tiene una lista mutable de
// contactos y métodos para agregar y eliminar contactos de la lista.

class ContactoManager {
    companion object {
        var contactos: ArrayList<Contacto> = ArrayList()
        //var contactos: ArrayList<Contacto>? = null
        var adaptador: AdaptadorCustom? = null

        // Método para agregar un nuevo contacto a la lista
        fun agregarContacto(contacto: Contacto) {
            contactos?.add(contacto)
        }

        // Método para eliminar un contacto de la lista por su nombre
        fun eliminarContacto(nombre: String) {
            contactos?.removeAt(index)
        }

        fun obtenerContactoPorID(index: Int): Contacto? {
            return contactos?.getOrNull(index)
        }

        fun actualizarContacto(index: Int, nuevoContacto: Contacto) {
            contactos?.set(index, nuevoContacto)

        }
    }
}
