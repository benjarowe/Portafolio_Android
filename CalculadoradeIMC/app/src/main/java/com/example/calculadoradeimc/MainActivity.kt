package com.example.calculadoradeimc

import android.app.AlertDialog
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoradeimc.databinding.ActivityMainBinding

/*Para hombres:
18-24	IMC = 18.5 - 24.9
25-34	IMC = 19.0 - 25.0
35-44	IMC = 20.0 - 25.5
45-54	IMC = 20.5 - 26.0
55-64	IMC = 21.0 - 26.5
65-74	IMC = 21.5 - 27.0
75-84	IMC = 22.0 - 27.5
85-94	IMC = 22.5 - 28.0
95-104	IMC = 23.0 - 28.5
105-114	IMC = 23.5 - 29.0
115-120	IMC = 24.0 - 29.5

Para mujeres:
18-24	IMC = 18.5 - 24.9
25-34	IMC = 18.5 - 25.0
35-44	IMC = 19.0 - 25.5
45-54	IMC = 19.5 - 26.0
55-64	IMC = 20.0 - 26.5
65-74	IMC = 20.5 - 27.0
75-84	IMC = 21.0 - 27.5
85-94	IMC = 21.5 - 28.0
95-104	IMC = 22.0 - 28.5
105-114	IMC = 22.5 - 29.0
115-120	IMC = 23.0 - 29.5
*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectorColor = Color.parseColor("#ACE2E1") // Color celeste claro
    private var rangAlturaInt = 120 // Altura inicial
    private var calPeso = 60 // Peso inicial
    private var calEdad = 40 // Edad inicial
    private var carHombre = true // Género inicial (hombre)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        // Configuración de los listeners de los botones de género
        binding.masculino.setOnClickListener {
            carHombre = true
            binding.masculino.setCardBackgroundColor(selectorColor) // Establece color de fondo
            binding.femenino.setCardBackgroundColor(Color.WHITE)
            // Cambia la imagen del masculino a blanco
            binding.masculino.findViewById<ImageView>(R.id.imgMasculino).setColorFilter(Color.WHITE)
            // Cambia la imagen del femenino a celeste claro
            binding.femenino.findViewById<ImageView>(R.id.imgfemenino).setColorFilter(selectorColor)
        }

        binding.femenino.setOnClickListener {
            carHombre = false
            binding.femenino.setCardBackgroundColor(selectorColor)
            binding.masculino.setCardBackgroundColor(Color.WHITE)
            // Cambia la imagen del femenino a blanco
            binding.femenino.findViewById<ImageView>(R.id.imgfemenino).setColorFilter(Color.WHITE)
            // Cambia la imagen del masculino a celeste_claro
            binding.masculino.findViewById<ImageView>(R.id.imgMasculino).setColorFilter(selectorColor)
        }

        // Listener para el cambio de valor en el rango de altura
        binding.rangAltura.addOnChangeListener { _, value, _ ->
            rangAlturaInt = value.toInt()
            binding.medidAlturNumer.text = "$rangAlturaInt cm" // Actualiza la vista con la altura seleccionada
        }

        // Listeners para los botones de sumar y restar peso
        binding.btnSumarPes.setOnClickListener {
            calPeso += 1
            updatePesoView()
        }

        binding.btnRestarPes.setOnClickListener {
            calPeso -= 1
            updatePesoView()
        }

        // Listeners para los botones de sumar y restar edad
        binding.btnSumarEdad.setOnClickListener {
            calEdad += 1
            updateEdadView()
        }

        binding.btnRestarEdad.setOnClickListener {
            calEdad -= 1
            updateEdadView()
        }

        // Listener para el botón de calcular el IMC
        binding.btnResultado.setOnClickListener {
            if (binding.masculino.cardBackgroundColor.defaultColor != selectorColor &&
                binding.femenino.cardBackgroundColor.defaultColor != selectorColor
            ) {
                Toast.makeText(this, "Debes ingresar datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imc = calcularIMC(calPeso.toDouble(), rangAlturaInt.toDouble())

            resultIMC(imc) // Calcula y muestra el resultado del IMC
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Realiza las actualizaciones necesarias en la interfaz
    }

    private fun updatePesoView() {
        binding.vistNumPeso.text = calPeso.toString() // Actualiza la vista con el peso actual
    }

    private fun updateEdadView() {
        binding.VistNumEdad.text = calEdad.toString() // Actualiza la vista con la edad actual
    }

    private fun calcularIMC(peso: Double, altura: Double): Double {
        val alturaMetros = altura / 100 // Convierte la altura a metros
        return peso / (alturaMetros * alturaMetros) // Calcula el IMC
    }

    private fun calculateAgeGroup(edad: Int, esHombre: Boolean): String {
        // Calcula el rango de IMC según la edad y género
        return if (esHombre) {
            when {
                edad in 18..24 -> "min: 18.5 - max: 24.9"
                edad in 25..34 -> "min: 19.0 - max: 25.0"
                edad in 35..44 -> "min: 20.0 - max: 25.5"
                edad in 45..54 -> "min: 20.5 - max: 26.0"
                edad in 55..64 -> "min: 21.0 - max: 26.5"
                edad in 65..74 -> "min: 21.5 - max: 27.0"
                edad in 75..84 -> "min: 22.0 - max: 27.5"
                edad in 85..94 -> "min: 22.5 - max: 28.0"
                edad in 95..104 -> "min: 23.0 - max: 28.5"
                edad in 105..114 -> "min: 23.5 - max: 29.0"
                edad in 115..120 -> "min: 24.0 - max: 29.5"
                else -> "o"
            }
        } else {
            when {
                edad in 18..24 -> "min: 18.5 - max: 24.9"
                edad in 25..34 -> "min: 18.5 - max: 25.0"
                edad in 35..44 -> "min: 19.0 - max: 25.5"
                edad in 45..54 -> "min: 19.5 - max: 26.0"
                edad in 55..64 -> "min: 20.0 - max: 26.5"
                edad in 65..74 -> "min: 20.5 - max: 27.0"
                edad in 75..84 -> "min: 21.0 - max: 27.5"
                edad in 85..94 -> "min: 21.5 - max: 28.0"
                edad in 95..104 -> "min: 22.0 - max: 28.5"
                edad in 105..114 -> "min: 22.5 - max: 29.0"
                edad in 115..120 -> "min: 23.0 - max: 29.5"
                else -> "o"
            }
        }
    }

    private fun resultIMC(result: Double) {
        // Mostrar el resultado del IMC en un diálogo de alerta
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.ventana_emergente, null)

        // Obtiene el rango de IMC según la edad
        val ageGroup = calculateAgeGroup(calEdad, carHombre)

        builder.setView(dialogView)
            .setTitle("Resultado")
            .setCancelable(true)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }

        val tvResult: TextView = dialogView.findViewById(R.id.tvResult)
        tvResult.text = "Tu IMC: ${String.format("%.2f", result)}"  // Muestra el IMC calculado

        val tvAgeGroup: TextView = dialogView.findViewById(R.id.tvAgeGroup)
        tvAgeGroup.text = "Rango de IMC según edad = $ageGroup" // Muestra el rango de IMC según edad

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
