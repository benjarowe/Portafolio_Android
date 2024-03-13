package com.example.cursorecyclerview

//Mapeo. Constructor primario
class Platillo (nombre:String, precio:Double, rating: Float, foto:Int) {
    //declaro propiedades
    var nombre = ""
    var precio = 0.0
    var rating = 0.0F
    var foto = 0
    //bloque instancia
    init {
        this.nombre = nombre
        this.precio = precio
        this.rating = rating
        this.foto = foto
    }

}
