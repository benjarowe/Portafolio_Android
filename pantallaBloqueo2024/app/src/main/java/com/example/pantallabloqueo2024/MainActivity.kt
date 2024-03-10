package com.example.pantallabloqueo2024

import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pantallabloqueo2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var tiempoRestante = 0L
    private var temporizadorView: View? = null
    private var vistaAgregadaAlWindowManager = false

    private lateinit var horasPicker: NumberPicker
    private lateinit var minutosPicker: NumberPicker
    private lateinit var segundosPicker: NumberPicker

    private val handler = Handler()
    private lateinit var runnable: Runnable

    companion object {
        private const val REQUEST_CODE_DRAW_OVERLAY_PERMISSION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar componentes de la interfaz de usuario
        initializeUIComponents()

        // Configurar evento de clic para el botón de inicio
        binding.btnAdd.setOnClickListener {
            val horas = horasPicker.value
            val minutos = minutosPicker.value
            val segundos = segundosPicker.value
            iniciarTemporizador(horas, minutos, segundos)
        }
    }

    // Utiliza la variable de instancia horasPicker, minutosPicker, segundosPicker
    private fun initializeUIComponents() {
        horasPicker = binding.numPickerHs
        horasPicker.minValue = 0
        horasPicker.maxValue = 24

        minutosPicker = binding.numPickerMints
        minutosPicker.minValue = 0
        minutosPicker.maxValue = 59

        segundosPicker = binding.numPickerSeg
        segundosPicker.minValue = 0
        segundosPicker.maxValue = 59
    }

    private fun iniciarTemporizador(horas: Int, minutos: Int, segundos: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            solicitarPermisoSuperposicion()
        } else {
            if (!vistaAgregadaAlWindowManager) {
                agregarVistaTemporizador(horas, minutos, segundos)
            }
        }
    }

    private fun solicitarPermisoSuperposicion() {
        startActivityForResult(
            Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            ),
            REQUEST_CODE_DRAW_OVERLAY_PERMISSION
        )
    }

    private fun agregarVistaTemporizador(horas: Int, minutos: Int, segundos: Int) {
        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            this.type = type
            flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                    or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            format = PixelFormat.TRANSLUCENT
        }

        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        temporizadorView = layoutInflater.inflate(R.layout.fondo_dialog, null)
        val textViewTiempoRestante =
            temporizadorView?.findViewById<TextView>(R.id.tvTiempoRestante)

        val tiempoTotal = ((horas * 3600) + (minutos * 60) + segundos) * 1000L
        tiempoRestante = tiempoTotal

        windowManager.addView(temporizadorView, params)
        vistaAgregadaAlWindowManager = true

        runnable = object : Runnable {
            override fun run() {
                if (tiempoRestante > 0) {
                    tiempoRestante -= 1000L // Restar 1 segundo
                    textViewTiempoRestante?.text = formatTiempoRestante(tiempoRestante)
                    handler.postDelayed(this, 1000)
                } else {
                    terminarTemporizador()
                }
            }
        }
        handler.post(runnable)

        window.decorView.apply {
            systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
        supportActionBar?.hide()
    }

    private fun terminarTemporizador() {
        temporizadorView?.let {
            val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            windowManager.removeView(it)
        }
        vistaAgregadaAlWindowManager = false
        supportActionBar?.show()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        Toast.makeText(this, "Tiempo finalizado", Toast.LENGTH_SHORT).show()
    }

    private fun formatTiempoRestante(tiempoRestante: Long): String {
        val horas = tiempoRestante / 3600000
        val minutos = (tiempoRestante % 3600000) / 60000
        val segundos = (tiempoRestante % 60000) / 1000

        return String.format("%02d:%02d:%02d", horas, minutos, segundos)
    }
}
