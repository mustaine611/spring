# Seguridad + i18n + API REST + WebFlux (Spring Boot)

> Proyecto académico que integra **Spring Security**, **internacionalización (i18n)**, 
> **API REST** con persistencia y una **evolución opcional a programación reactiva (WebFlux)**. 
> Incluye lineamientos para un **frontend en React** y pruebas con Postman.

---

## 🧭 Tabla de contenido
- [Descripción](#-descripción)
- [Características](#-características)
- [Arquitectura](#-arquitectura)
- [Requisitos](#-requisitos)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Configuración](#-configuración)
  - [Spring Initializr](#spring-initializr)
  - [Seguridad (Spring Security)](#seguridad-spring-security)
  - [Internacionalización (i18n)](#internacionalización-i18n)
  - [Persistencia y MySQL](#persistencia-y-mysql)
- [Ejecución](#-ejecución)
- [Endpoints REST (ejemplos)](#-endpoints-rest-ejemplos)
- [Frontend (React)](#-frontend-react)
- [Opción Reactiva (WebFlux)](#-opción-reactiva-webflux)
- [Pruebas (Postman)](#-pruebas-postman)
- [Buenas prácticas y notas](#-buenas-prácticas-y-notas)
- [Licencia](#-licencia)

---

## 📌 Descripción
Este repositorio demuestra, paso a paso, cómo construir una aplicación Spring Boot que:
1. Protege rutas con **roles** (ADMIN, USER) y **form login**.
2. Incluye **páginas Thymeleaf** para `login`, `index (dashboard)` y `registro` (opcional).
3. Implementa **i18n** mediante archivos `messages*.properties` y un **LocaleChangeInterceptor** (`?lang=es|en|pt`).
4. Expone **APIs REST** para catálogo de productos y autenticación.
5. Proporciona guía para un **frontend en React**.
6. Ofrece una variante **reactiva con WebFlux** para pedidos/seguimiento.

> El contenido se basa en el documento de la entrega académica adjunta al repo.  

## ✨ Características
- **Seguridad moderna (Spring Security 6)** con `SecurityFilterChain`, usuarios en memoria o BD, y rutas públicas/privadas.
- **Internacionalización**: ES/EN/PT conmutables desde UI.
- **Catálogo REST**: listar por categoría, buscar y ver detalle de productos.
- **Registro/Inicio de sesión** con validación (Bean Validation) y encriptación.
- **React Frontend**: ejemplo de cliente con `axios`, `react-router-dom` y `bootstrap`.
- **WebFlux opcional**: controladores reactivos para pedidos y seguimiento del estado.

## 🧱 Arquitectura
- **Backend (Spring Boot)**: MVC (Thymeleaf) + REST; alternativa **Reactive Stack** (WebFlux + R2DBC).
- **Frontend (React)**: SPA que consume el backend vía HTTP.
- **DB**: MySQL (JPA/Hibernate) o R2DBC (modo reactivo).

## ✅ Requisitos
- Java 17+
- Maven 3.9+
- Node.js 18+ (si usarás el frontend React)
- MySQL 8+ (para persistencia clásica)

## 🗂️ Estructura del proyecto
```
src/
 └─ main/
    ├─ java/.../config/
    │   ├─ SecurityConfig.java
    │   └─ I18nConfig.java
    ├─ java/.../controller/
    │   ├─ TestController.java
    │   ├─ LoginController.java
    │   └─ HomeController.java
    ├─ java/.../rest/
    │   └─ ProductoRestController.java
    ├─ resources/
    │   ├─ messages.properties
    │   ├─ messages_es.properties
    │   ├─ messages_pt.properties
    │   └─ templates/
    │       ├─ login.html
    │       ├─ index.html
    │       └─ registro.html (opcional)
    └─ pom.xml
```

## ⚙️ Configuración

### Spring Initializr
- **Project**: Maven
- **Language**: Java
- **Spring Boot**: 3.x
- **Dependencias**: `Spring Web`, `Spring Security`, `Thymeleaf`, `Validation`, (opcional `WebFlux`, `Data JPA`, `MySQL Driver`, `R2DBC`).

### Seguridad (Spring Security)
- Definir un **`SecurityFilterChain`** con reglas:
  - `"/public/**"` público
  - `"/user/**"` → ROLE_USER o ROLE_ADMIN
  - `"/admin/**"` → ROLE_ADMIN
- **Form Login**:
  - Por defecto o **página personalizada** en `/login`
  - `defaultSuccessUrl` al dashboard (`/` o `/public`)
- **Logout**:
  - `POST /logout`, invalidación de sesión y cookies, redirección a `/login?exit`

### Internacionalización (i18n)
- Archivos `messages*.properties` para ES/EN/PT.
- `LocaleChangeInterceptor` con `paramName = "lang"`.
- `SessionLocaleResolver` con idioma por defecto `es`.
- En vistas Thymeleaf, usar llaves `#{...}` para textos; agregar botones `?lang=es|en|pt`.

### Persistencia y MySQL
En `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_db
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```
Modelo de ejemplo `Producto` y repositorio con métodos `findByCategoria`, `findByNombreContainingIgnoreCase`.

## ▶️ Ejecución
1. **Backend**
   ```bash
   mvn spring-boot:run
   ```
   - Abrir `http://localhost:8080/login`.
   - Credenciales en memoria de ejemplo (`admin/admin123`, `user/user123`) o desde BD.

2. **Frontend (opcional)**
   ```bash
   npx create-react-app restaurante-frontend
   cd restaurante-frontend
   npm install axios react-router-dom bootstrap
   npm start
   ```
   Configura CORS en el backend para `http://localhost:3000`.

## 🔌 Endpoints REST (ejemplos)
**Productos** (`/api/productos`):
- `GET /categoria/{cat}` → Lista por categoría
- `GET /{id}` → Detalle por ID
- `GET /buscar?q=texto` → Búsqueda por nombre (case-insensitive)

**Auth (ejemplo)** (`/api/auth`):
- `POST /registrar` → Registro (valida duplicados, encripta password)
- `POST /login` → Inicio de sesión (flujo a elección: sesión/JWT)
- `POST /recuperar` → Flujo de recuperación por token + correo (requiere Mail)

## 🖥️ Frontend (React)
Cliente ejemplo con `axios` y componentes como **Catálogo** (RF4/RF5/RF6). 
Asegura **CORS** (`@CrossOrigin` o configuración global) y variables de entorno para URLs.

## ⚡ Opción Reactiva (WebFlux)
- Sustituir `spring-boot-starter-web` por `spring-boot-starter-webflux`.
- Persistencia reactiva con `spring-boot-starter-data-r2dbc` + `r2dbc-mysql`.
- Controladores con `Mono`/`Flux` para **Pedidos** (`/api/pedidos`):
  - `POST /` → registrar pedido (estado inicial `EN_PREPARACION`)
  - `GET /{id}` → consultar pedido
  - `GET /{id}/estado` → ver estado de pedido

## 🧪 Pruebas (Postman)
Colección recomendada:

| Método | Endpoint                                   | Acción             | RF |
|-------:|--------------------------------------------|--------------------|----|
| POST   | `/api/auth/registrar`                      | Crear usuario      | 1  |
| GET    | `/api/productos/buscar?q=hamburguesa`      | Buscar productos   | 6  |
| GET    | `/api/productos/categoria/bebidas`         | Filtrar catálogo   | 4  |
| GET    | `/api/productos/{id}`                      | Ver detalle        | 5  |
| POST   | `/api/pedidos` (WebFlux)                   | Registrar pedido   | 7  |
| GET    | `/api/pedidos/{id}/estado` (WebFlux)       | Ver estado pedido  | 10 |

## 🧰 Buenas prácticas y notas
- Encriptar contraseñas con `PasswordEncoder` (BCrypt).
- Validar DTOs con **Bean Validation** (`@NotBlank`, `@Size`, etc.).
- Manejar errores y redirecciones en login/logout con mensajes amigables.
- En i18n, centralizar textos en `messages*.properties` para evitar literals en vistas.
- Documentar CORS y variables de entorno usadas por el frontend.

## 📄 Licencia
Este proyecto se distribuye con fines educativos. Ajusta la licencia según tus necesidades (MIT, Apache-2.0, etc.).


