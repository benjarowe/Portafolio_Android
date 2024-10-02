Bloqueo de Pantalla :

Descripción general: La aplicación es un temporizador personalizado que permite al usuario seleccionar un intervalo de tiempo (horas, minutos, y segundos) y bloquear la pantalla del dispositivo durante ese tiempo. Al finalizar el temporizador, la pantalla se desbloquea, y el usuario recibe una notificación.

Propósito: Facilitar un control temporal sobre el uso del dispositivo, ideal para aquellos que desean concentrarse en una tarea sin distracciones.

2. Características Principales
Selector de tiempo: El usuario puede configurar horas, minutos y segundos para el temporizador utilizando NumberPickers.
Superposición de pantalla: Durante el tiempo establecido, la aplicación muestra una pantalla de bloqueo que se superpone a todas las demás aplicaciones mediante el permiso SYSTEM_ALERT_WINDOW.

Interfaz amigable: La aplicación presenta una interfaz simple con diseño de ConstraintLayout y animaciones como el uso de una ProgressBar.

Control del temporizador: Se utiliza un Handler para manejar el tiempo restante en intervalos de 1 segundo.

Compatibilidad con versiones modernas de Android: Gestión de permisos de superposición en tiempo de ejecución para dispositivos con Android 6.0 (Marshmallow) y versiones superiores.

3. Tecnologías Usadas
Lenguaje de programación: Kotlin.
Framework: Android SDK.
Librerías y APIs utilizadas:
WindowManager: Para gestionar la superposición de la pantalla de bloqueo.
Handler: Para controlar la ejecución del temporizador.
NumberPicker: Para que el usuario seleccione el tiempo.
Toast: Notificaciones visuales al usuario.
Componentes de Android:
Activity: La pantalla principal está controlada por MainActivity.
Service: Un servicio en segundo plano (TemporizadorService) para manejar el temporizador incluso cuando la app no está activa.
Interacción con el sistema operativo: Gestión de permisos como la superposición de pantalla (SYSTEM_ALERT_WINDOW).

5. Estructura del Proyecto
Arquitectura: El proyecto sigue un enfoque modular básico con separación de responsabilidades entre:
MainActivity: La interfaz y la lógica para iniciar el temporizador.
TemporizadorService: Gestiona la ejecución del temporizador en segundo plano.
TemporizadorView: Personalización de la vista que muestra el tiempo restante.
Patrones de diseño:
Runnable: El temporizador utiliza un Runnable ejecutado en un Handler para disminuir el tiempo restante cada segundo.
Service: El uso de un servicio para realizar tareas en segundo plano asegura que el temporizador funcione incluso si la aplicación se minimiza.

7. Detalles Técnicos Implementados
Interfaz de usuario (UI):
Diseño usando ConstraintLayout para posicionar de forma flexible los elementos, como los NumberPickers y el botón de inicio.
Vista personalizada: La superposición de pantalla utiliza un RelativeLayout con un ImageView para el fondo y un TextView para mostrar el tiempo restante.
Gestión de datos:
Permisos: Implementación de permisos en tiempo de ejecución para mostrar la superposición de pantalla.
Temporizador: El temporizador funciona con un Handler y un Runnable que actualiza el tiempo restante cada segundo.
Navegación:
No hay navegación entre pantallas, pero la app gestiona la superposición sobre la pantalla principal del dispositivo.
Pruebas y depuración:
Debugging: Se ha utilizado el sistema de logs y la depuración de Android Studio para solucionar problemas relacionados con permisos y comportamiento en segundo plano.

9. Pruebas y Depuración
Pruebas funcionales: Se ha probado la correcta ejecución del temporizador, la superposición de pantalla y la gestión de permisos en diferentes versiones de Android.
Pruebas de usabilidad: La interfaz ha sido testeada para asegurar que el usuario pueda fácilmente configurar y entender el funcionamiento del temporizador.
Depuración: Se ha realizado depuración activa para corregir errores relacionados con la superposición en diferentes dispositivos y versiones de Android.
10. Conclusión
La aplicación Pantalla Bloqueo Temporizador demuestra el uso de múltiples componentes y tecnologías de Android como el manejo de WindowManager, servicios en segundo plano, y la implementación de temporizadores eficientes con Handler y Runnable.
Este proyecto puede evolucionar implementando funcionalidades adicionales, como alertas sonoras al finalizar el temporizador, o estadísticas del uso del temporizador por parte del usuario.


![iconobloq2](https://github.com/user-attachments/assets/8d162abe-7a86-4760-9e4f-9dc607f74960)

![iniciando](https://github.com/user-attachments/assets/49c40790-cec6-4b01-9f5a-7a1254a71905)

![volteoMovil](https://github.com/user-attachments/assets/ab8c8538-72a0-43d7-876c-6b5716323654)



https://github.com/user-attachments/assets/f83cfa09-7d0e-4d46-bea3-f4a55d8caafb


-------------------------------

Calculadora de IMC:  

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


[Video: Funcionamiento APP](https://drive.google.com/file/d/1LpbOab268vkvvmm2nYC8ICSXn5Q84n7m/view?usp=drive_link)

[Video: Funcionamiento App 2](https://drive.google.com/file/d/1LxtAPu5W6nUowX3252nqEwheOFAClUnz/view?usp=drive_link)

--------------------------------------

La aplicacion Agenda de Contactos:  

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

<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/f0b2a289-e5f9-4bfb-9349-e5ecc85b69a0" alt="ContactosP" width="20%">
<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/5c41fa47-c3e8-45dc-8460-1189b9e8748a" alt="ContactosP" width="20%">
<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/f08869a0-2507-4547-bbf3-d04622741954" alt="ContactosP" width="20%">


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

