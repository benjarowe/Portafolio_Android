package com.example.pantbloq4

import android.os.AsyncTask
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection
//Mapeo activity
class DescargaURL(var completadoListener: CompletadoListener?): AsyncTask<String, Void, String>() {
    //Se propesa la descarga de los datos
    override fun doInBackground(vararg params: String): String? {
        try {
            return descargaDatos(params[0])
        }catch (e:IOException){
            return null
        }
    }
    //Como se envia
    override fun onPostExecute(result: String){
        try {
            completadoListener?.descargaCompleta(result)
        }catch (e:Exception){

        }
    }
    @Throws(IOException::class)
    private fun descargaDatos(url:String):String{
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
        }finally {
            if (inputStream != null){
                inputStream.close()
            }
        }
    }

}