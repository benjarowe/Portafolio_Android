package com.example.clima

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
//Se creo esta clase para que no este constantemente agregando la validacion en cada boton
//declaro la funcion de forma estatica (companion object)
//getSystemService es un metodo (forma parte de una actividad se lo aclara como parametro
//donde va el contexto Context.CONNECTIVITY_SERVICE
//se crea dentro de companion object un parametro que se llama concectivityManager que usa como
// metodo que se llama
// getSystemService()
class Network {
    companion object{
        fun hayRed(activity:AppCompatActivity):Boolean{
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            //return si hay informacion de red (True) si no hay informacion de red null && y aprte se comprueba la coneccion
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
