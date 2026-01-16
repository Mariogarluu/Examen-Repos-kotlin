# Turing Library - Aplicación de Búsqueda de Libros 📚

Una aplicación Android moderna desarrollada en Kotlin que permite buscar y explorar libros utilizando la API de Open Library. Construida con las últimas tecnologías de Android y siguiendo las mejores prácticas de arquitectura limpia.

## 🌟 Características Principales

- **Búsqueda de Libros**: Busca libros por título, autor o palabras clave
- **Resultados Detallados**: Visualiza una lista completa de libros encontrados
- **Detalles del Libro**: Consulta información detallada de cada libro
- **Almacenamiento Local**: Cacheo de datos con Room Database para acceso offline
- **UI Moderna**: Interfaz construida con Jetpack Compose y Material Design 3
- **Navegación Fluida**: Sistema de navegación tipo-seguro con Navigation Compose

## 🏗️ Arquitectura

El proyecto sigue los principios de **Clean Architecture** y está organizado en capas claramente definidas:

```
app/
├── data/                      # Capa de Datos
│   ├── local/                 # Fuente de datos local (Room)
│   │   ├── BookDatabase.kt
│   │   ├── BookEntity.kt
│   │   └── BookDao.kt
│   ├── remote/                # Fuente de datos remota (Retrofit)
│   │   ├── BookApi.kt
│   │   └── BookRemoteDataSource.kt
│   ├── model/                 # Modelos de datos
│   │   ├── Book.kt
│   │   └── BookDto.kt
│   └── repository/            # Repositorios
│       ├── BookRepository.kt
│       └── BookRepositoryImpl.kt
├── di/                        # Inyección de Dependencias (Hilt)
│   ├── AppModule.kt
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   └── BookDataSourceModule.kt
├── ui/                        # Capa de Presentación
│   ├── search/                # Pantalla de búsqueda
│   ├── results/               # Pantalla de resultados
│   ├── detail/                # Pantalla de detalles
│   ├── navigation/            # Configuración de navegación
│   └── theme/                 # Temas y estilos
└── MainActivity.kt            # Actividad principal
```

### Patrón MVVM
- **Model**: Entidades de datos y lógica de negocio
- **View**: Componentes de Jetpack Compose
- **ViewModel**: Gestión de estado y lógica de presentación

## 🛠️ Tecnologías Utilizadas

### Core
- **Kotlin 2.2.21** - Lenguaje de programación
- **Android SDK 34-36** - Plataforma Android
- **Jetpack Compose** - UI declarativa moderna

### Arquitectura y Patrones
- **Hilt 2.57.2** - Inyección de dependencias
- **Room 2.8.4** - Base de datos local
- **Retrofit 3.0.0** - Cliente HTTP para APIs REST
- **Navigation Compose 2.9.6** - Navegación tipo-seguro

### Adicionales
- **Kotlin Serialization** - Serialización de datos
- **Material Design 3** - Componentes de diseño
- **Coroutines & Flow** - Programación asíncrona

## 📋 Requisitos Previos

- **Android Studio** Hedgehog (2023.1.1) o superior
- **JDK 11** o superior
- **Android SDK** con nivel mínimo de API 34 (Android 14)
  - *Nota: El proyecto utiliza API 34 debido a las características modernas de Jetpack Compose y Material Design 3. Para mayor compatibilidad, se puede reducir el minSdk en `app/build.gradle.kts` a API 24 o superior.*
- **Gradle 8.1+**
- Conexión a Internet (para búsqueda de libros)

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Mariogarluu/Examen-Repos-kotlin.git
cd Examen-Repos-kotlin
```

### 2. Abrir en Android Studio
1. Abre Android Studio
2. Selecciona `File > Open`
3. Navega a la carpeta del proyecto clonado
4. Espera a que Gradle sincronice las dependencias

### 3. Compilar el Proyecto
```bash
./gradlew build
```

### 4. Ejecutar la Aplicación
- Conecta un dispositivo Android (API 34+) o inicia un emulador
- Haz clic en el botón `Run` (▶️) en Android Studio
- O ejecuta desde la terminal:
```bash
./gradlew installDebug
```

## 📱 Uso de la Aplicación

1. **Pantalla de Búsqueda**: Ingresa el término de búsqueda (título, autor, etc.)
2. **Pantalla de Resultados**: Explora la lista de libros encontrados
3. **Pantalla de Detalle**: Toca un libro para ver información completa

## 🔌 API Externa

La aplicación utiliza la **Open Library API** para obtener información de libros:

- **Base URL**: `https://openlibrary.org`
- **Endpoint**: `/search.json?q={query}`
- **Documentación**: [Open Library API Docs](https://openlibrary.org/developers/api)

### Ejemplo de Consulta
```
GET https://openlibrary.org/search.json?q=kotlin
```

## 📦 Dependencias Principales

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.9.6")

// Hilt (Dependency Injection)
implementation("com.google.dagger:hilt-android:2.57.2")
kapt("com.google.dagger:hilt-compiler:2.57.2")

// Room (Database)
implementation("androidx.room:room-runtime:2.8.4")
implementation("androidx.room:room-ktx:2.8.4")
ksp("androidx.room:room-compiler:2.8.4")

// Retrofit (Network)
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-gson:3.0.0")

// Kotlin Serialization
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
```

## 🎨 Características de UI

- **Material Design 3**: Diseño moderno y adaptable
- **Modo Oscuro**: Soporte automático de tema claro/oscuro
- **Edge-to-Edge**: Aprovecha toda la pantalla del dispositivo
- **Animaciones Fluidas**: Transiciones suaves entre pantallas
- **Componentes Reutilizables**: Arquitectura de UI escalable

## 🧪 Testing

El proyecto incluye configuración para:
- **Unit Tests**: JUnit 4
- **Instrumentation Tests**: AndroidX Test + Espresso
- **UI Tests**: Compose Testing

```bash
# Ejecutar tests unitarios
./gradlew test

# Ejecutar tests instrumentados
./gradlew connectedAndroidTest
```

## 📂 Estructura de Navegación

```kotlin
NavGraph
├── Search (Pantalla inicial)
├── Results (Lista de resultados)
└── Detail (Detalles del libro)
```

La navegación utiliza **Type-Safe Navigation** con argumentos serializables.

## 🔐 Permisos

La aplicación requiere el siguiente permiso en el `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

## 🤝 Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Convenciones de Código

- Seguir las [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Usar nombres descriptivos en inglés para clases y métodos
- Documentar funciones públicas con KDoc
- Mantener funciones pequeñas y enfocadas en una sola responsabilidad

## 🐛 Problemas Conocidos

- La aplicación requiere API 34+ (Android 14+) por defecto
  - *Solución: Para mayor compatibilidad, reducir `minSdk` en `app/build.gradle.kts` a API 24 o superior*
- Se necesita conexión a Internet para búsquedas en tiempo real
- El cacheo offline está implementado pero requiere una búsqueda inicial con conexión

## 📄 Licencia

Este proyecto es un ejercicio académico desarrollado con fines educativos.

## 👨‍💻 Autor

**Mario García**
- GitHub: [@Mariogarluu](https://github.com/Mariogarluu)

## 🙏 Agradecimientos

- [Open Library](https://openlibrary.org/) por proporcionar la API gratuita
- [Android Developers](https://developer.android.com/) por la documentación
- Comunidad de Kotlin y Jetpack Compose

---

⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub!
