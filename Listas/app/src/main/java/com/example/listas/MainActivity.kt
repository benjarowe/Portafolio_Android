package com.example.listas.Categorias

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudiandokotlin.fichero.Categories.cat2adapter
import com.example.listas.Categories.Adapter
import com.example.listas.R
import com.example.listas.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

/*updateTasks() :
*  fun initListeners() // Este método asigna una acción al botón flotante para mostrar un diálogo
*  fun showDialog()    // Este método crea y muestra un diálogo para añadir una nueva tarea
*  fun initComponent() // Este método inicializa los componentes de la interfaz gráfica
*  fun initUI() :  // Este método inicializa la interfaz gráfica con los datos de las categorías y las tareas
*  fun onItemSelected(position:Int){   // Actualiza el estado de selección de la tarea en la posición dada y refresca la interfaz.
*  fun updateCategories(position:Int) :  // Actualiza el estado de selección de la categoría en la posición dada y refresca la interfaz.
*  fun updateTasks() :    // Este método actualiza la interfaz gráfica con la nueva tarea
* */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Estas son las categorías posibles para las tareas
    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    // Esta es la lista de tareas que se mostrarán en el menú
    private val task = mutableListOf(
        task("PruebaBusiness", TaskCategory.Business),
        task("PruebaPersonal", TaskCategory.Personal),
        task("PruebaOther", TaskCategory.Other)
    )

    // Estos son los componentes de la interfaz gráfica que se usarán para mostrar
    // las categorías y las tareas
    private lateinit var cardCategiria: RecyclerView
    private lateinit var adapter: Adapter

    private lateinit var cardCategoria2: RecyclerView
    private lateinit var cat2adapter: cat2adapter

    // Este es el botón flotante que se usará para añadir una nueva tarea
    private lateinit var fabaddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponent() // Este método inicializa los componentes de la interfaz gráfica
        initUI()   // Este método inicializa la interfaz gráfica con los datos de las categorías y las tareas
        initListeners()  // Este método asigna las acciones a los componentes de la interfaz gráfica
    }

    // Este método asigna una acción al botón flotante para mostrar un diálogo
    private fun initListeners() {
        fabaddTask.setOnClickListener { showDialog() } // Al hacer clic en el botón flotante, se llama al método showDialog
    }

    // Este método crea y muestra un diálogo para añadir una nueva tarea
    private fun showDialog() {
        val dialog =
            Dialog(this) // Se crea un objeto de tipo Dialog con el contexto de la actividad
        dialog.setContentView(R.layout.dialogo_task) // Se asigna el contenido del diálogo con el layout dialogo_task

        val btnAddTask: Button =
            dialog.findViewById(R.id.btnAddTask) // Se obtiene el botón para añadir la tarea del diálogo
        val etTask: EditText =
            dialog.findViewById(R.id.etTask)  // Se obtiene el campo de texto para escribir el nombre de la tarea del diálogo
        val rGrupCategories: RadioGroup =
            dialog.findViewById(R.id.rGrupCategories) // Se obtiene el grupo de botones radiales para elegir la categoría de la tarea del diálogo

        btnAddTask.setOnClickListener {// Se asigna una acción al botón para añadir la tarea
            val currenTask =
                etTask.text.toString() // Se obtiene el texto del campo de texto como una cadena
            if (currenTask.isNotEmpty()) { // Se comprueba que la cadena no esté vacía

                val selectedId =
                    rGrupCategories.checkedRadioButtonId // Se obtiene el id del botón radial seleccionado
                val selectedRadioButton: RadioButton =
                    rGrupCategories.findViewById(selectedId) // Se obtiene el botón radial seleccionado
                val currentCategory: TaskCategory =
                    // Se obtiene la categoría correspondiente al texto del botón radial seleccionado usando una expresión when
                    when (selectedRadioButton.text) {
                        "Negocios" -> TaskCategory.Business
                        "Personal" -> TaskCategory.Personal
                        else -> TaskCategory.Other
                    }
                // Se añade una nueva tarea a la lista de tareas con el nombre y la categoría obtenidos
                task.add(task(etTask.text.toString(), currentCategory))
                dialog.dismiss() // Se oculta el diálogo
                updateTasks() // Se llama al método updateTasks para actualizar la interfaz gráfica con la nueva tarea
            }
        }
        dialog.show() // Se muestra el diálogo
    }

    // Este método inicializa los componentes de la interfaz gráfica
    private fun initComponent() {
        cardCategiria = binding.cardCategiria // Se obtiene el RecyclerView para mostrar las categorías
        cardCategoria2 = binding.cardCategiria2 // Se obtiene el RecyclerView para mostrar las tareas
        fabaddTask = binding.agrTask // Se obtiene el botón flotante para añadir una nueva tarea
    }

    // Este método inicializa la interfaz gráfica con los datos de las categorías y las tareas
    private fun initUI() {
        // Se crea un adaptador para las categorías con la lista de categorías
        adapter = Adapter(categories) { position -> updateCategories(position) }

        // Se asigna un gestor de diseño lineal horizontal al RecyclerView de las categorías
        cardCategiria.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Se asigna el adaptador al RecyclerView de las categorías
        cardCategiria.adapter = adapter

        cat2adapter = cat2adapter(task) { position -> onItemSelected(position) }

        // Se asigna un gestor de diseño lineal vertical al RecyclerView de las tareas
        cardCategoria2.layoutManager = LinearLayoutManager(this)

        // Se asigna el adaptador al RecyclerView de las tareas
        cardCategoria2.adapter = cat2adapter
    }

    // Actualiza el estado de selección de la tarea en la posición dada y refresca la interfaz.
    private fun onItemSelected(position: Int) {
        task[position].isSelected = !task[position].isSelected

        updateTasks()
    }

    // Actualiza el estado de selección de la categoría en la posición dada y refresca la interfaz.
    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        adapter.notifyItemChanged(position)
        updateTasks()
    }

    // Este método actualiza la interfaz gráfica con la nueva tarea
    private fun updateTasks() {
        //cat2adapter.notifyDataSetChanged() // Se notifica al adaptador de las tareas que los datos han cambiado
        val selectedCategory: List<TaskCategory> = categories.filter { it.isSelected }
        val newTask = task.filter { selectedCategory.contains(it.category) }
        cat2adapter.task = newTask
        cat2adapter.notifyDataSetChanged()
    }
}
