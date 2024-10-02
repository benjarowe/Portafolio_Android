### Bloqueo de Pantalla (preparando para subir a play store)

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
<br>
<video width="20" height="20" controls>
  <source src="https://github.com/user-attachments/assets/bf406d81-6bbb-4027-ac20-b8eed5401de6" type="video/mp4">
</video>


https://github.com/user-attachments/assets/99e7379f-67ab-4c02-9404-bc18de02d2fd


--------------------------------------

# Aplicación "Contactos"

## Descripción

La aplicación "Contactos" permite gestionar y organizar contactos en dispositivos Android. Su objetivo es facilitar la adición, edición, eliminación y visualización de detalles de contactos de manera intuitiva y eficiente.

## Funcionalidades

- **Agregar Contactos**: Añadir nuevos contactos con información relevante (nombre, número de teléfono, correo electrónico, dirección).
- **Editar Contactos**: Modificar información de contactos existentes.
- **Eliminar Contactos**: Eliminar contactos de forma segura.
- **Visualización de Detalles**: Ver todos los detalles de un contacto específico.

## Tecnologías Utilizadas

- **Kotlin**
- **Android Studio**
- **Room** (gestión local de datos)
- **Glide** (carga de imágenes)
- **MVVM** (Modelo-Vista-VistaModelo)

## Beneficios

- **Interfaz Intuitiva**: Navegación fácil y amigable.
- **Rendimiento Optimizado**: Acceso rápido a la base de datos con Room.
- **Escalabilidad**: Estructura MVVM permite agregar nuevas funcionalidades sin afectar la estabilidad.
- **Portabilidad**: Funciona en una amplia gama de dispositivos Android.

## Conclusión

La aplicación "Contactos" refleja mis habilidades en desarrollo de aplicaciones móviles y mi compromiso con la creación de software de calidad. Este proyecto es una muestra de mi capacidad para resolver problemas cotidianos y mi deseo de crecer profesionalmente en el ámbito de la programación móvil.

<h2>Pantalla principal</h2>
<br>
<img src="https://github.com/benjarowe/Portfolio_Android/assets/160912053/f0b2a289-e5f9-4bfb-9349-e5ecc85b69a0" alt="ContactosP" width="20%">

--------------------------------------

### Aplicación RecyclerView

**Descripción:**  
Aplicación de ejemplo que muestra cómo crear una lista de platillos utilizando RecyclerView y SwipeRefreshLayout en Android. Presenta una lista de platillos con su nombre, precio y rating, y permite actualizar la lista con un gesto de deslizamiento hacia abajo.

**Funcionalidades Destacadas:**
- **Lista de Platillos:**  
  Muestra una lista de platillos con nombre, precio y rating usando RecyclerView.
  
- **SwipeRefreshLayout:**  
  Permite al usuario actualizar la lista de platillos mediante un gesto de deslizamiento hacia abajo para refrescar la pantalla.

**Tecnologías Utilizadas:**
- **Android Studio:**  
  Entorno de desarrollo integrado (IDE) oficial para el desarrollo de aplicaciones Android.
  
- **RecyclerView:**  
  Implementado para mostrar la lista de platillos de manera eficiente y escalable.
  
- **SwipeRefreshLayout:**  
  Utilizado para permitir la actualización de la lista mediante un gesto de deslizamiento hacia abajo.

**Conclusión:**  
La aplicación RecyclerView es una muestra práctica de cómo utilizar componentes de Android para crear listas dinámicas y mejoradas con interacción del usuario.


