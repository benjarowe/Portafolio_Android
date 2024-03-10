package com.example.pantbloq4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pantbloq4.databinding.ActivityBaseClimaBinding
import com.google.gson.Gson

private lateinit var binding: ActivityBaseClimaBinding

open class BaseClimaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseClimaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreCiudades = intent.getStringExtra("CIUDAD")

        if (Network.hayRed(this) && nombreCiudades != null) {
            // Ejecutar solicitud HTTP
            val url = construirURLClima(nombreCiudades)
            solicitudHTTPVolley(url)
        } else {
            // Mostrar error
            Toast.makeText(this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show()
        }
    }
    private fun solicitudHTTPVolley(url: String) {
        val queue = Volley.newRequestQueue(this)
        //val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
        val solicitud = StringRequest(Request.Method.GET, url, { response ->
            try {
                Log.d("solicitudHTTOVolley", response)

                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                actualizarVistas(ciudad)
            } catch (e: Exception) {
                // Manejar la excepción
            }
        },{ error ->
            // Manejar errores de la solicitud HTTP
        })
        queue.add(solicitud)
    }
    // Método que debe ser implementado por las clases hijas para actualizar las vistas específicas
    protected open fun actualizarVistas(ciudad: Ciudad) {
        // Actualizar las vistas comunes en la lógica base
        binding.ciudad?.text = ciudad.name
        binding.grados?.text = ciudad.main?.temp.toString() + "°"
        binding.nublado?.text = ciudad.weather?.get(0)?.description
    }
    private fun construirURLClima(nombreCiudades: String): String{
        return when(nombreCiudades){
            "Oviedo"-> "https://api.openweathermap.org/data/2.5/weather?id=3114711&appid=82364de59048b2b6a543169400514faf&units=metric&lang=es"
            "Barcelona" -> "https://api.openweathermap.org/data/2.5/weather?id=3128760&appid=82364de59048b2b6a543169400514faf&units=metric&lang=es"
            else -> "URL_ERROR"
        }
        //Barcelona= id: 3128760
        //Oviedo = id: 3114711
    }
}