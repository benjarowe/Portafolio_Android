La aplicación CalculadoradeIMC: es una calculadora de índice de masa corporal (IMC) para dispositivos Android. 
El IMC es una medida que evalúa el peso corporal en relación con la altura y se utiliza comúnmente para determinar si una persona tiene un peso saludable.

La aplicación permite al usuario ingresar su género, altura y peso, y luego calcular su IMC. 
También muestra el rango de IMC recomendado para diferentes grupos de edad y género.

Los componentes y formatos utilizados en la aplicación:

Lenguaje de programación: La aplicación está escrita en Kotlin, que es un lenguaje de programación oficialmente compatible con el desarrollo de aplicaciones Android.
Diseño de la interfaz de usuario (UI): La interfaz de usuario se define utilizando archivos XML en el directorio res/layout. 
Se utilizan ConstraintLayout y CardView de AndroidX para diseñar y organizar los elementos de la interfaz de usuario.
Recursos gráficos: Los recursos gráficos, como imágenes de género y otros iconos, se almacenan en el directorio res/drawable.

Estilos y temas: Se utilizan estilos y temas personalizados para definir la apariencia de los elementos de la interfaz de usuario,
como los CardView y los FloatingActionButton.

Binding de vistas: Se utiliza View Binding para acceder fácilmente a las vistas en el código Kotlin. 
Esto se logra mediante el uso de la clase ActivityMainBinding generada automáticamente.

Diálogo emergente personalizado: Para mostrar el resultado del IMC, se utiliza un AlertDialog personalizado 
que infla un diseño XML (popup_layout.xml) que contiene TextViews para mostrar el resultado y el rango de IMC según la edad.

Manejo de eventos: Se utilizan listeners para manejar eventos como clics en botones (setOnClickListener) y
cambios en el rango de altura (addOnChangeListener).
Dependencias: Se utilizan varias dependencias de AndroidX y Material Design para implementar funcionalidades como RangeSlider y FloatingActionButton.


[Mostrando sin ingresar genero](https://drive.google.com/file/d/1LpbOab268vkvvmm2nYC8ICSXn5Q84n7m/view?usp=drive_link)

[Mostrando sin ingresar genero](https://drive.google.com/file/d/1LxtAPu5W6nUowX3252nqEwheOFAClUnz/view?usp=drive_link)
