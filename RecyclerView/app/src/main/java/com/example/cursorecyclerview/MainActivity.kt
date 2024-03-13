package com.example.cursorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cursorecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lista: RecyclerView
    private lateinit var adaptador: AdaptadorCustom
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lista = binding.lista
        adaptador = AdaptadorCustom(getPlatillos(), object : ClickListener {
            override fun onClick(vista: View, index: Int) {
                Toast.makeText(applicationContext, adaptador.platillos[index].nombre, Toast.LENGTH_SHORT).show()
            }
        })
        lista.apply {
            setHasFixedSize(true)
            adapter = adaptador
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }
    }

    private fun refreshData() {
        val nuevaLista = getPlatillos()
        nuevaLista.add(Platillo("Nuevo Platillo", 200.0, 4.0F, R.drawable.platillo10))
        adaptador.updateData(nuevaLista)
        swipeRefreshLayout.isRefreshing = false
    }

    private fun getPlatillos(): ArrayList<Platillo> {
        val platillos = ArrayList<Platillo>()
        platillos.add(Platillo("Platillo 1", 250.0, 3.5F, R.drawable.platillo01))
        platillos.add(Platillo("Platillo 2", 250.0, 3.5F, R.drawable.platillo02))
        platillos.add(Platillo("Platillo 3", 250.0, 3.5F, R.drawable.platillo03))
        platillos.add(Platillo("Platillo 4", 250.0, 3.5F, R.drawable.platillo04))
        platillos.add(Platillo("Platillo 5", 250.0, 3.5F, R.drawable.platillo05))
        platillos.add(Platillo("Platillo 6", 250.0, 3.5F, R.drawable.platillo06))
        platillos.add(Platillo("Platillo 7", 250.0, 3.5F, R.drawable.platillo07))
        platillos.add(Platillo("Platillo 8", 250.0, 3.5F, R.drawable.platillo08))
        platillos.add(Platillo("Platillo 9", 250.0, 3.5F, R.drawable.platillo09))
        platillos.add(Platillo("Platillo 10", 250.0, 3.5F, R.drawable.platillo10))
        return platillos
    }
}