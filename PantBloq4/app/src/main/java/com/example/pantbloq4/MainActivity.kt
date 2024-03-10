package com.example.pantbloq4

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pantbloq4.databinding.ActivityMainBinding
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //HTTP
        val boton = binding.startButton1
        val boton2 = binding.startButton2HTTP
        val boton3 = binding.startButton3
        val boton4 = binding.startButton4
        val boton5 = binding.startButton5

        //Solicitud HTTP
        boton.setOnClickListener { verificarConexionYRealizarAccion("https://www.youtube.com/") }
        boton2.setOnClickListener { verificarConexionYRealizarAccion("https://www.google.es/") }
        boton3.setOnClickListener { verificarConexionYRealizarAccion("https://www.google.es/") }
        boton4.setOnClickListener { verificarConexionYRealizarAccion("https://www.google.es/") }

        boton5.setOnClickListener {
            val intent = Intent(this, AppsProbandoHTTP::class.java)
            startActivity(intent)
        }
    }

    //HTTP
    private fun verificarConexionYRealizarAccion(url: String) {
        if (Network.hayRed(this)) {
            when (url) {
                "https://www.youtube.com/" -> Toast.makeText(
                    this,
                    "Realizar acción para YouTube",
                    Toast.LENGTH_LONG
                ).show()

                "https://www.google.es/" -> DescargaURL(this).execute(url)
            }
        } else {
            Toast.makeText(this, "No hay conexión a Internet", Toast.LENGTH_LONG).show()
        }
    }

    //Metodo Volley Libreria(verificaa solicitudes 1 por uno a la vez) queue
//Me da un resultado en forma de string. Solicitud
    private fun solicitudHTTPVolley(url: String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Method.GET, url, { response ->
            try {
                Log.d("solicitudHTTOVolley", response)
            } catch (e: Exception) {

            }
        }, { })
        queue.add(solicitud)
    }

    //Metodo para OKHTTP --- envio de solicitud de dato mas sencillo
    private fun solicitudHTTPOKHTTP(url: String) {
        val cliente = OkHttpClient()//OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()

        cliente.newCall(solicitud).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                //implementar error
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val resultado = response.body?.string()
                this@MainActivity.runOnUiThread {
                    try {
                        resultado?.let { Log.d("solicutudHTTPOkHTTP", it) }
                    } catch (e: Exception) {

                    }
                }
            }
        })
    }

    //el resultado se devuelve en forma de texto
    override fun descargaCompleta(resultado: String) {
        Log.d("descargaCompleta", resultado)
    }
}

//es una funcion llamado descargar datos, que tiene como parametro un url y devuelve un string
//se declara una variable llamada inputStream inicializado con un null, toma flujo de dato de la conexion
//se utliza por si llega a haber problema
@Throws(IOException::class)
private fun descargaDatos(url: String): String {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    var inputStream: InputStream? = null

    try {
        val url = URL(url)
        val conn = url.openConnection() as HttpsURLConnection
        conn.requestMethod = "GET"
        conn.connect()

        inputStream = conn.inputStream
        return inputStream.bufferedReader().use {
            it.readText()
        }
    } finally {
        if (inputStream != null) {
            inputStream.close()
        }
    }
}

/* private lateinit var timerTextView: TextView
 private lateinit var startButton: Button
 private lateinit var countDownTimer: CountDownTimer

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     timerTextView = findViewById(R.id.timerTextView)
     startButton = findViewById(R.id.startButton)

     startButton.setOnClickListener {
         startTimer()
     }
 }

 private fun startTimer() {
     countDownTimer = object : CountDownTimer(30000, 1000) { // 30 segundos en este ejemplo
         override fun onTick(millisUntilFinished: Long) {
             val seconds = millisUntilFinished / 1000
             val minutes = seconds / 60
             val hours = minutes / 60

             val formattedTime = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
             timerTextView.text = formattedTime
         }

         override fun onFinish() {
             timerTextView.text = "00:00:00"
         }
     }.start()
}
}*/
//Boton internet
/*
   boton.setOnClickListener{
       if (Network.hayRed(this)){
           Toast.makeText(this, "Si hay red", Toast.LENGTH_LONG).show()
       }else{
           Toast.makeText(this,"No hay red", Toast.LENGTH_LONG).show()
       }
   }
   boton2.setOnClickListener{
       if (Network.hayRed(this)){
           //Log.d("boton2", descargaDatos("https://www.youtube.com/"))
           DescargaURL(this).execute("https://www.google.es/")
       }else{
           Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_LONG).show()
       }
       boton3.setOnClickListener {
           if (Network.hayRed(this)){
               solicitudHTTPVolley("https://www.google.es/")
           }else{
               Toast.makeText(this,"No hay conexion", Toast.LENGTH_LONG).show()
           }
       }
       boton4.setOnClickListener {
           if (Network.hayRed(this)){
               solicitudHTTPOKHTTP("https://www.google.es/")
           }else{
               Toast.makeText(this,"No hay conexion", Toast.LENGTH_LONG).show()
           }
       }
   }*/
