package com.example.pantbloq4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pantbloq4.databinding.ActivityAppsProbandoHttpBinding
import com.example.pantbloq4.databinding.ActivityMainBinding
import com.google.gson.Gson

private lateinit var binding: ActivityAppsProbandoHttpBinding


class AppsProbandoHTTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppsProbandoHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBarcelona.setOnClickListener{ envio("Barcelona") }
        binding.btnOviedo.setOnClickListener { envio("Oviedo") }

    }
    private fun envio(ciudad: String){
        val intent = Intent(this, BaseClimaActivity::class.java)
        intent.putExtra("CIUDAD", ciudad)
        startActivity(intent)
    }

}