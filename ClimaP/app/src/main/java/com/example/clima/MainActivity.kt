package com.example.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clima.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        val btnClima = binding.btnClima



        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnClima.setOnClickListener {
            val intent = Intent(this, ElegirCiudades::class.java)
            startActivity(intent)
        }

    }
}
