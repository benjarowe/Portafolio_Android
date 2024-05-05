La aplicación CalculadoradeIMC:  

Este proyecto está desarrollado utilizando Kotlin.

Es una calculadora de índice de masa corporal (IMC) para dispositivos Android. 
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

<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/d2af69b9-4b41-4c11-bcb9-c504a22e764e" alt="calculadoradeimc" width="20%">


[Video: Mostrando completo](https://drive.google.com/file/d/1LpbOab268vkvvmm2nYC8ICSXn5Q84n7m/view?usp=drive_link)

[Video: Sin asignar ingresar genero](https://drive.google.com/file/d/1LxtAPu5W6nUowX3252nqEwheOFAClUnz/view?usp=drive_link)

--------------------------------------

La aplicacion ContactosP:  

Este proyecto está desarrollado utilizando Kotlin.

Esta es una aplicación móvil diseñada para facilitar la gestión y organización de contactos en dispositivos Android. Con esta aplicación, puedes agregar, editar y eliminar contactos, así como ver detalles específicos de cada uno de ellos, todo desde la comodidad de tu teléfono inteligente.

Funcionalidades Destacadas:

Agregar y Editar Contactos: Agrega fácilmente nuevos contactos proporcionando su información básica, como nombre, empresa, edad, peso, dirección, número de teléfono y correo electrónico. Además, puedes editar los detalles de los contactos existentes en cualquier momento.
Galería de Fotos Integrada: Personaliza tus contactos añadiendo una foto de perfil desde tu galería de fotos o elige entre una selección de imágenes predeterminadas.
Detalles Completos de Contactos: Explora los detalles completos de cada contacto, incluida su foto de perfil, información personal y datos de contacto.
Búsqueda Rápida: Encuentra rápidamente el contacto que necesitas utilizando la función de búsqueda integrada, que te permite filtrar la lista de contactos por nombre.
Tecnologías Utilizadas:

Android Studio: La aplicación se desarrolló en Android Studio, el entorno de desarrollo integrado (IDE) oficial para el desarrollo de aplicaciones Android.
Android SDK: Utilizamos el Kit de Desarrollo de Software (SDK) oficial de Android para crear esta aplicación.
Glide: Para la carga de imágenes en la aplicación, se utilizó Glide, una biblioteca de carga de imágenes para Android que ofrece rendimiento optimizado y gestión inteligente de memoria.
Room: Para la persistencia de datos en la aplicación, se implementó Room, una biblioteca de persistencia de datos SQLite para Android.
Arquitectura MVVM: La arquitectura Modelo-Vista-VistaModelo (MVVM) se utilizó para organizar y estructurar el código de la aplicación de manera modular y escalable.


--------------------------------------

La aplicacion RecyclerView:  

Este proyecto está desarrollado utilizando Kotlin.

Esta es una aplicación de ejemplo que demuestra cómo crear una lista de platillos utilizando RecyclerView y SwipeRefreshLayout en Android. La aplicación muestra una lista de platillos con su nombre, precio y rating. Además, permite actualizar la lista mediante un gesto de deslizamiento hacia abajo para refrescar.

Funcionalidades Destacadas:

Lista de Platillos: Muestra una lista de platillos con su nombre, precio y rating utilizando RecyclerView.
SwipeRefreshLayout: Permite al usuario actualizar la lista de platillos mediante un gesto de deslizamiento hacia abajo para refrescar la pantalla.
Tecnologías Utilizadas:

Android Studio: La aplicación se desarrolló en Android Studio, el entorno de desarrollo integrado (IDE) oficial para Android.
RecyclerView: Se implementó RecyclerView para mostrar la lista de platillos de manera eficiente y escalable.
SwipeRefreshLayout: Se utilizó SwipeRefreshLayout para permitir al usuario actualizar la lista de platillos mediante un gesto de deslizamiento hacia abajo.



--------------------------------------

