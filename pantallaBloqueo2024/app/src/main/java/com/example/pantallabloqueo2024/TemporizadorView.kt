package com.example.pantbloqueofebrero

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View

class TemporizadorView(context: Context) : View(context) {

    private var tiempoRestante: Long = 0L

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
*/
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun setTiempoRestante(tiempoRestante: Long) {
        this.tiempoRestante = tiempoRestante
        invalidate()
    }
}
