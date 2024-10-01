package com.example.pantallabloqueo2024

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pantallabloqueo2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var horasPicker: NumberPicker
    private lateinit var minutosPicker: NumberPicker
    private lateinit var segundosPicker: NumberPicker
    private var tiempoRestante = 0L
    private var temporizadorView: View? = null
    private var vistaAgregadaAlWindowManager = false
    private var temporizadorActivo = false
    private lateinit var params: WindowManager.LayoutParams

    private lateinit var countDownTimer: CountDownTimer

    companion object {
        private const val REQUEST_CODE_DRAW_OVERLAY_PERMISSION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUIComponents()
        checkAndRequestPermissions()

        binding.btnAdd.setOnClickListener {
            val horas = horasPicker.value
            val minutos = minutosPicker.value
            val segundos = segundosPicker.value
            permisoInicioTemporizador(horas, minutos, segundos)
        }
    }

    // Inicializa los componentes de la interfaz de usuario (UI) relacionados con el temporizador
    private fun initializeUIComponents() {
        horasPicker = binding.numPickerHs.apply {
            minValue = 0
            maxValue = 24
        }
        minutosPicker = binding.numPickerMints.apply {
            minValue = 0
            maxValue = 59
        }
        segundosPicker = binding.numPickerSeg.apply {
            minValue = 0
            maxValue = 59
        }
    }

    // Verifica si se tiene el permiso de superposición y, si es así, iniciar el temporizador
    private fun permisoInicioTemporizador(horas: Int, minutos: Int, segundos: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            solicitarPermisoSuperposicion()
        } else {
            if (!vistaAgregadaAlWindowManager) {
                agregarVistaTemporizador(horas, minutos, segundos)
            }
        }
    }

    // Solicita el permiso de superposición al usuario
    private fun solicitarPermisoSuperposicion() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        startActivityForResult(intent, REQUEST_CODE_DRAW_OVERLAY_PERMISSION)
    }

    // Agrega la vista del temporizador a la ventana (pantalla superpuesta)
    private fun agregarVistaTemporizador(horas: Int, minutos: Int, segundos: Int) {

        // Crea los parámetros de diseño (layout params) necesarios para la vista superpuesta
        params = crearLayoutParams()

        temporizadorView = layoutInflater.inflate(R.layout.fondo_dialog, null)
        val textViewTiempoRestante = temporizadorView?.findViewById<TextView>(R.id.tvTiempoRestante)

        tiempoRestante = calcularTiempoRestante(horas, minutos, segundos)
        textViewTiempoRestante?.text = formatTiempoRestante(tiempoRestante)

        // Agregar la vista del temporizador a la ventana utilizando el WindowManager
        (getSystemService(WINDOW_SERVICE) as WindowManager).addView(temporizadorView, params)
        vistaAgregadaAlWindowManager = true
        temporizadorActivo = true

        iniciarContador(textViewTiempoRestante)
    }

    // Crea los parámetros de diseño (LayoutParams) para la vista superpuesta del temporizador
    private fun crearLayoutParams(): WindowManager.LayoutParams {
        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        )
    }

    // Calcula el tiempo restante en milisegundos
    private fun calcularTiempoRestante(horas: Int, minutos: Int, segundos: Int): Long {
        return ((horas * 3600) + (minutos * 60) + segundos) * 1000L
    }

    private fun iniciarContador(textView: TextView?) {
        startKioskMode()

        countDownTimer = object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tiempoRestante = millisUntilFinished
                textView?.text = formatTiempoRestante(tiempoRestante)
            }

            override fun onFinish() {
                runOnUiThread {
                    stopLockTask()
                    terminarTemporizador()
                }
            }
        }.start()
    }

    // Activa el modo Kiosk (modo de bloqueo de tareas)
    private fun startKioskMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            if (activityManager.lockTaskModeState == ActivityManager.LOCK_TASK_MODE_NONE) {
                startLockTask()
            }
        }
    }

    // Se encarga de finalizar el temporizador y restaurar la interfaz de usuario normal.
    private fun terminarTemporizador() {
        temporizadorView?.let {
            (getSystemService(WINDOW_SERVICE) as WindowManager).removeView(it)
        }
        vistaAgregadaAlWindowManager = false
        temporizadorActivo = false
        supportActionBar?.show()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        Toast.makeText(this, "Tiempo finalizado", Toast.LENGTH_SHORT).show()
    }

    // Verifica si se tienen los permisos necesarios para superponer vistas
    private fun checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            solicitarPermisoSuperposicion()
        }
    }

    // Formatea el tiempo restante en formato de horas, minutos y segundos
    private fun formatTiempoRestante(tiempoRestante: Long): String {
        val horas = tiempoRestante / 3600000
        val minutos = (tiempoRestante % 3600000) / 60000
        val segundos = (tiempoRestante % 60000) / 1000
        return String.format("%02d:%02d:%02d", horas, minutos, segundos)
    }

    // Maneja el resultado de la solicitud de permisos
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DRAW_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Sobrescribe el método onConfigurationChanged() para manejar los cambios de orientación del dispositivo
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (temporizadorActivo) {
            // Obtiene la referencia de la imagen vertical y horizontal dentro de la vista superpuesta
            val imageViewVertical = temporizadorView?.findViewById<ImageView>(R.id.imageViewVertical)
            val imageViewHorizontal = temporizadorView?.findViewById<ImageView>(R.id.imageViewHorizontal)

            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageViewVertical?.visibility = View.GONE
                imageViewHorizontal?.visibility = View.VISIBLE
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageViewVertical?.visibility = View.VISIBLE
                imageViewHorizontal?.visibility = View.GONE
            }

            // Actualiza el diseño de la vista en el WindowManager para reflejar los cambios en la orientación
            (getSystemService(WINDOW_SERVICE) as WindowManager).updateViewLayout(temporizadorView, params)
        }
    }
}
