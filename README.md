# Columbia Viajes

Trabajo Práctico N°1 — Programación 2 — Tecnicatura Superior en Informática Aplicada (UTN INSPT)

Sistema de gestión para una cadena de agencias de viajes: administra sucursales, hoteles, vuelos, turistas y las reservas que los vendedores cargan para ellos. Desarrollado en Java, con persistencia en archivos de texto plano (sin base de datos) y entrada/salida exclusivamente por consola.

## Integrantes

- Guido Alvarez ([@gvarone](https://github.com/gvarone))
- Ramiro
- Alexis
- Daniel Realini

## Enunciado

El sistema debe permitir:
- Gestionar sucursales (código, dirección, e-mail, teléfono).
- Gestionar hoteles contratados en exclusiva (código, nombre, dirección, ciudad, teléfono, plazas disponibles).
- Gestionar vuelos regulares en exclusiva (número, fecha y hora, origen, destino, plazas totales y de clase turista).
- Registrar turistas (código, nombre y apellidos, dirección, e-mail, teléfono).
- Asociar cada turista a la sucursal que lo contrató.
- Permitir que un turista reserve vuelo (clase turista o primera) y/o hotel (media pensión o pensión completa, con fecha de llegada y partida) de forma independiente entre sí.
- Manejar 4 tipos de usuario con distintos permisos: **Cliente** (solo consulta), **Vendedor** (administra reservas y clientes), **Administrador** (administra todo) y **Dueño** (consulta el ranking de vendedores según facturación).

Debe aplicar abstracción, encapsulamiento, herencia, polimorfismo y persistencia (sin BD), con arquitectura en capas (MVC + DAO + DTO) y consola como única interfaz.

## Arquitectura

El proyecto sigue un esquema en capas:

```
Views (consola)  →  Controllers  →  Services  →  DAOs  →  archivos .txt (data/)
                                        ↓
                                    Modelos
```

- **Modelos**: entidades de dominio (`Usuario` y sus subclases, `Turista`, `Sucursal`, `Hotel`, `Vuelo`, `Reserva`) y enums (`Rol`, `Clase`, `Hospedaje`).
- **DAOs**: acceso a persistencia. Una clase abstracta genérica `DAO<T>` centraliza lectura/escritura de archivos de texto (vía NIO, `Files`/`Paths`), y cada entidad tiene su DAO concreto con parsers (`mapeador`/`formateador`) propios.
- **Services**: lógica de negocio (validaciones, generación de códigos, orquestación entre DAOs).
- **Controllers**: orquestan el flujo de la aplicación (login, menús por rol, despacho de opciones a los Services).
- **Views**: lectura/escritura por consola, un menú por rol.

### Paquetes (convención propia del proyecto, no estándar de Java)

```
com.project.columbiaviajes
├── Modelos
├── Enums
├── DAOs
├── Services
├── Controllers
└── Views
```

> Nota: el proyecto usa paquetes en PascalCase (`Modelos`, `DAOs`, etc.) en lugar de la convención estándar de Java (minúsculas). Fue una decisión consciente del equipo para simplificar el desarrollo bajo plazo.

## Modelo de dominio

### Jerarquía de `Usuario` (clase abstracta)
- `Cliente` — usuario con solo permisos de consulta. Referencia a un `Turista` (`codTurista`).
- `Vendedor` — administra reservas y clientes. Trackea `totalFacturado` (se actualiza al concretar ventas).
- `Administrador` — gestiona sucursales, hoteles, vuelos, turistas y usuarios.
- `Duenio` — consulta listado de vendedores ordenado por facturación.

Todos comparten: `codigo`, `nombre`, `apellido`, `username`, `contrasenia`, `rol` (enum), y el método `validarContrasenia(String)`. Baja lógica vía atributo `eliminado`.

### Entidades de catálogo (no heredan de `Usuario`)
- `Turista`: código, nombre, apellido, dirección, e-mail, teléfono.
- `Sucursal`: código, nombre, dirección, e-mail, teléfono.
- `Hotel`: código, nombre, dirección, ciudad, teléfono, plazas totales (`final`) y disponibles.
- `Vuelo`: código, origen, destino, fecha y hora (`LocalDateTime`), plazas totales y turista (`final`), plazas turista disponibles.

### `Reserva` (entidad central)
Vincula turista, vendedor y sucursal (obligatorios) con vuelo y/o hotel (opcionales e independientes entre sí, representados con `Integer` en lugar de `int` para permitir `null`):

- `codigo`, `codTurista`, `codVendedor`, `codSucursal` — obligatorios.
- `codVuelo`, `clase` (enum `Clase`) — se cargan juntos vía `agregarVuelo(...)`.
- `codHotel`, `hospedaje` (enum `Hospedaje`), `checkIn`, `checkOut` (`LocalDateTime`) — se cargan juntos vía `agregarHotel(...)`.
- Regla de negocio: una reserva debe tener vuelo y/o hotel (no puede estar vacía).

### Enums
- `Rol`: DUENIO, ADMIN, VENDEDOR, CLIENTE.
- `Clase`: TURISTA, PRIMERA.
- `Hospedaje`: MEDIA_PENSION, PENSION_COMPLETA.

## Persistencia

- Serialización propia en texto plano (NIO: `Files.lines`, `Files.write`), **no** `ObjectOutputStream` (se descartó ese enfoque durante el desarrollo).
- Un archivo `.txt` por entidad dentro de `data/` (carpeta generada en tiempo de ejecución, excluida del repo vía `.gitignore`).
- Cada DAO concreto define un `Function<String, T>` (mapeador) y un `Function<T, String>` (formateador) para convertir entre línea de texto y objeto, separando campos con `;`.
- La clase abstracta `DAO<T>` centraliza:
  - `CARPETA_DATOS` y `FORMATO_FECHA` (constantes compartidas).
  - `leerTodos(mapeador)` / `guardarTodos(lista, formateador)` (sobrescribe el archivo completo en cada guardado — no usa `APPEND`).
  - `registrar(item, mapeador, formateador)` y `modificar(item, mapeador, formateador, obtenerCodigo)` genéricos.
- Baja lógica: las entidades no se eliminan físicamente, se marcan con un atributo `eliminado` que se persiste como cualquier otro campo.
- Códigos autoincrementales: cada DAO expone `obtenerUltimoCodigo()`, que recorre todos los registros existentes (incluidos los eliminados) y devuelve el máximo + 1.
- `Usuario` es polimórfico dentro de un único archivo/DAO: el campo `rol` permite reconstruir la subclase concreta (`Cliente`/`Vendedor`/`Administrador`/`Duenio`) al leer.

## Flujo de la aplicación

1. Al iniciar, si no hay usuarios registrados, se fuerza la creación de un primer Administrador.
2. Pantalla de login con reintentos limitados; se puede escribir `salir` como username para cerrar el programa.
3. Según el `Rol` del usuario autenticado, se instancia la vista de menú correspondiente y se despacha a un Controller específico por rol (`AdminControlador`, `VendedorControlador`, etc.).
4. Cada Controller expone un único método público `manejarMenu()`; el resto de sus métodos son privados.
5. Jerarquía de quién registra a quién:
   - El Administrador da de alta cualquier tipo de usuario (Cliente, Vendedor, Administrador, Dueño).
   - El Vendedor gestiona turistas y carga reservas (vuelo y/o hotel) a nombre de un turista.

## Convenciones del proyecto

- Nombres de variables/métodos en camelCase; clases en PascalCase (salvo los paquetes, ver nota arriba).
- Sin setters para identificadores (`codigo`) ni para campos derivados de `final`.
- Getters/setters siguen la convención JavaBeans (`getX`/`setX`, `isX` para booleanos).
- Un único `Scanner` por Vista de menú (instanciado en el constructor, no en cada lectura).
- Lectura de enteros por consola siempre vía `nextLine()` + `Integer.parseInt(...)` dentro de un `try/catch`, nunca `Scanner.nextInt()` (evita el problema clásico de mezcla con `nextLine()`).
- Errores de reglas de negocio (username duplicado, reserva sin vuelo ni hotel, etc.) se señalizan con `RuntimeException` y se capturan en el Controller.

## Cómo correr el proyecto

1. Clonar el repositorio y abrir la carpeta `ColumbiaViajes` como proyecto Maven en NetBeans.
2. Verificar que la clase principal configurada en las propiedades del proyecto (Run → Main Class) sea la que contiene el `main()`.
3. Ejecutar. Si es la primera vez, el sistema va a pedir crear el primer Administrador.
4. Los datos se persisten en la carpeta `data/` en la raíz del proyecto (se crea automáticamente).

## Pendiente / roadmap

- [ ] Completar menús de Cliente y Dueño.
- [ ] Descuento real de plazas disponibles (vuelo/hotel) al confirmar una reserva.
- [ ] Validar existencia de turista/sucursal/vuelo/hotel referenciados antes de crear una reserva.
- [ ] Opción "Asignar rol a un usuario" del menú de Administrador.
- [ ] Diagramas UML de caso de uso y de clases actualizados (formato PNG).
- [ ] Generar ejecutable `.jar`.

## Entregables requeridos por la cátedra

- [ ] Código fuente en GitHub.
- [ ] Ejecutable en formato `.jar`.
- [ ] Documentación generada con Javadoc.
- [ ] Diagramas UML de caso de uso y de clases (PlantUML o UMLetino, en `.png`).
