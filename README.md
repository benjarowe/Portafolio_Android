### Bloqueo de Pantalla

**Descripción:**  
Aplicación que permite al usuario establecer un temporizador personalizado para bloquear la pantalla del dispositivo, ayudando a concentrarse sin distracciones. Al finalizar el tiempo, la pantalla se desbloquea y se envía una notificación.

**Características Principales:**
- **Selector de tiempo:** Configuración de horas, minutos y segundos usando NumberPickers.
- **Superposición de pantalla:** Muestra una pantalla de bloqueo mediante el permiso SYSTEM_ALERT_WINDOW.
- **Interfaz amigable:** Diseño simple con animaciones y ProgressBar.
- **Compatibilidad:** Maneja permisos de superposición en dispositivos Android 6.0 y superiores.

**Tecnologías Usadas:**
- **Lenguaje:** Kotlin
- **Marco:** SDK de Android
- **Componentes:** 
  - **MainActivity:** Controla la interfaz principal.
  - **TemporizadorService:** Gestiona el temporizador en segundo plano.
  - **Permisos:** Manejo de permisos de superposición.

**Estructura del Proyecto:**
- **Arquitectura modular:** Separación de responsabilidades entre las actividades y servicios.
- **Patrones de diseño:** Uso de Runnable y Handler para el temporizador.

**Pruebas y Depuración:**
- Pruebas de funcionalidad y usabilidad realizadas para asegurar un buen desempeño y experiencia de usuario.

**Conclusión:**  
La aplicación demuestra el uso eficiente de componentes y tecnologías de Android, con potencial para añadir nuevas funcionalidades como alertas sonoras o estadísticas de uso.

<h2>icono de la apps</h2>
<br>
<img src="https://github.com/user-attachments/assets/8d162abe-7a86-4760-9e4f-9dc607f74960" alt="iconobloq2" width="20%">
<br>
<h2>Pantalla principal</h2>
<br>
<img src="https://github.com/user-attachments/assets/49c40790-cec6-4b01-9f5a-7a1254a71905" alt="iniciando" width="20%">
<br>
<h2>Modo horizontal</h2>
<br>
<img src="https://github.com/user-attachments/assets/ab8c8538-72a0-43d7-876c-6b5716323654" alt="volteoMovil" width="50%">
<br>
<h2>Video completo (cuando comienza el conteo bloquea el movil)</h2>
<br>
<video width="20" height="20" controls>
  <source src="https://github.com/user-attachments/assets/f83cfa09-7d0e-4d46-bea3-f4a55d8caafb.mp4" type="video/mp4">
</video>

https://github.com/user-attachments/assets/9c06108f-1ffc-421e-8756-cfda7e706cd1


-------------------------------

### Calculadora de IMC

**Descripción:**  
Aplicación para calcular el Índice de Masa Corporal (IMC) en dispositivos Android, permitiendo evaluar el peso corporal en relación con la altura y determinar si el peso es saludable.

**Características Principales:**
- **Ingreso de Datos:** Permite al usuario ingresar género, altura y peso.
- **Cálculo del IMC:** Calcula el IMC y muestra el resultado.
- **Rangos de IMC:** Muestra rangos recomendados de IMC según grupo de edad y género.

**Tecnologías Usadas:**
- **Lenguaje:** Kotlin
- **Diseño UI:**
  - **XML:** Diseño de interfaz usando archivos XML en `res/layout`.
  - **Componentes:** Utiliza ConstraintLayout y CardView de AndroidX para organizar elementos.
- **Recursos Gráficos:** Imágenes y iconos almacenados en `res/drawable`.
- **Estilos y Temas:** Estilos personalizados para CardView y FloatingActionButton.
- **Binding de Vistas:** Uso de View Binding para acceso fácil a vistas mediante `ActivityMainBinding`.
- **Diálogo Emergente:** AlertDialog personalizado para mostrar el resultado del IMC.
- **Manejo de Eventos:** Listeners para manejar clics en botones y cambios en el rango de altura.
- **Dependencias:** Uso de dependencias de AndroidX y Material Design para funcionalidades como RangeSlider y FloatingActionButton.

**Conclusión:**  
La aplicación permite calcular y evaluar el IMC de manera fácil y amigable, utilizando las tecnologías y herramientas más recientes de Android.

<h2>Calculadora IMC</h2>
<br>
<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/d2af69b9-4b41-4c11-bcb9-c504a22e764e" alt="calculadoradeimc" width="20%">
<br>
<h2>Video funcion</h2>
<video width="20" height="20" controls>
https://github.com/user-attachments/assets/57d79323-80fe-4d96-ad73-4e7ea6540c28
</video>
<br>
<h2>Video funcion</h2>
<br>
[Video: Funcionamiento App 2](https://drive.google.com/file/d/1LxtAPu5W6nUowX3252nqEwheOFAClUnz/view?usp=drive_link)

--------------------------------------

### Agenda de Contactos

**Descripción:**  
Aplicación móvil para gestionar y organizar contactos en dispositivos Android, permitiendo agregar, editar y eliminar contactos, y ver detalles específicos de cada uno.

**Funcionalidades Destacadas:**
- **Agregar y Editar Contactos:**  
  Permite agregar nuevos contactos con información básica (nombre, empresa, edad, peso, dirección, teléfono y correo) y editar detalles de contactos existentes.
  
- **Galería de Fotos Integrada:**  
  Personaliza contactos añadiendo fotos de perfil desde la galería o seleccionando imágenes predeterminadas.
  
- **Detalles Completos de Contactos:**  
  Muestra detalles de cada contacto, incluyendo foto de perfil, información personal y datos de contacto.
  
- **Búsqueda Rápida:**  
  Función de búsqueda para encontrar contactos filtrando por nombre.

**Tecnologías Utilizadas:**
- **Android Studio:**  
  Entorno de desarrollo integrado (IDE) oficial para el desarrollo de aplicaciones Android.
  
- **Android SDK:**  
  Kit de Desarrollo de Software oficial utilizado para crear la aplicación.
  
- **Glide:**  
  Biblioteca para carga de imágenes, optimizando el rendimiento y la gestión de memoria.
  
- **Room:**  
  Biblioteca de persistencia de datos SQLite para la gestión de datos en la aplicación.
  
- **Arquitectura MVVM:**  
  Estructura modular y escalable utilizando el patrón Modelo-Vista-VistaModelo (MVVM) para organizar el código.

**Conclusión:**  
La aplicación Agenda de Contactos ofrece una solución completa para gestionar contactos de forma eficiente y amigable, incorporando tecnologías y patrones modernos de desarrollo.


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

