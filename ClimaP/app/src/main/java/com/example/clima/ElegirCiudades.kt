package com.example.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clima.databinding.ActivityElegirCiudadesBinding

class ElegirCiudades : AppCompatActivity() {

    private lateinit var binding: ActivityElegirCiudadesBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityElegirCiudadesBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBarcelona.setOnClickListener{ envio("Barcelona") }
        binding.btnOviedo.setOnClickListener { envio("Oviedo") }

    }
    private fun envio(ciudad: String){
        val intent = Intent(this, Ciudades::class.java)
        intent.putExtra("CIUDAD", ciudad)
        startActivity(intent)
    }

}
