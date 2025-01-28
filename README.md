# Reto2025Movile

# Documentación 

## Realizado por el Equipo 
- Izan
- David
- Cristian
- Leila
- Luis



## Tabla de contenidos

1. [Introducción](#introducción)
2. [API](#api)
3. [Estructura ](#Estructura)
4. [Componentes](#Componentes)
5. [Navigation](#Navigation)
6. [ViewModel](#ViewModel)
7. [data](#data)
8. [Conclusión](#conclusión)

## Introducción
Este proyecto es una aplicación móvil de Android desarrollada en Kotlin, concretamente una aplicación encargada de gestionar las actividades extraescolares y complementarias de un centro educativo. Contiene varios módulos organizados en carpetas relacionadas con vistas, modelos de datos, servicios API, navegación y componentes reutilizables.

## API
[https://github.com/PauDiSan/ProyectoIntermodularEquipoA2/tree/main](https://github.com/PauDiSan/ProyectoIntermodularEquipoA2/tree/main)  

## Estructura
El proyecto está organizado en varias carpetas.A continuación, se describen las principales carpetas y sus propósitos:
- La carpeta *"API"*: que sirve para los servicios web (Retrofit).
- La carpeta *"Componentes"*: que contiene elementos reutilizables, como Usuario.kt.
- La carpeta *"Navigation"*: encargada de la gestión de la navegación en la aplicación (NavManager.kt, ItemsNav.kt).
- La carpeta *"ViewModel"*: que contiene la lógica de negocio y controladores de estado, como ProfesorViewModel.kt.
- La carpeta *"Views"*: que contiene archivos de interfaz de usuario para cada pantalla (HomeView.kt, PerfilView.kt, etc.).
- Y, por último, la carpeta *"data"*: que contiene modelos de datos y respuestas de la API (Actividad.kt, Curso.kt, etc.).
## Componentes
Esta carpeta contiene los siguientes elementos:
- Una clase llamada *"Componentes.kt"* que contiene elementos reutilizables.
- Y una clase llamada *"Usuario.kt"* , se trata de un modelo para gestionar usuarios.

### Navigation
Esta carpeta contiene los siguientes elementos:
- Una clase llamada *"ItemsNav.kt"*" que descripciónefine elementos de navegación.
- Y una clase llamada *"NavManager.kt"*: que se trata de un controlador encargado de gestionar la navegación.
## ViewModel
Esta carpeta contiene los siguientes elementos: 

**ActividadViewModel**
| **Sección del código**              | **Descripción**                                                                                                                                                                   |
|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ActividadViewModel`**      | Clase que extiende `ViewModel` y maneja la lógica relacionada con las actividades, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_actividad`**          | `MutableLiveData<Actividad>` que contiene una única actividad. Es privado para que solo pueda ser modificado dentro del `ViewModel`.                                              |
| **Propiedad `actividad`**           | `LiveData<Actividad>` que expone los datos de `_actividad` de forma inmutable para que la vista los observe.                                                                       |
| **Propiedad `_actividades`**        | `MutableLiveData<List<Actividad>>` que contiene una lista de actividades. Es privado para protegerlo de modificaciones externas.                                                  |
| **Propiedad `actividades`**         | `LiveData<List<Actividad>>` que expone los datos de `_actividades` de forma inmutable para que la vista observe la lista de actividades.                                           |
| **Propiedad `service`**             | Instancia del servicio Retrofit, creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con los datos del backend.                           |
| **Método `getActividades()`**       | Llama al método `getActividades()` del servicio para obtener la lista de actividades del backend. Actualiza `_actividades` y maneja excepciones registrándolas en el log.          |
| **Método `getActividadById(id)`**   | Obtiene una actividad específica por su ID usando el método `getActividadById(id)` del servicio. Actualiza `_actividad` con los datos obtenidos.                                   |
| **Método `updateActividad()`**      | Actualiza una actividad en el backend utilizando `updateActividad()` del servicio. Posteriormente actualiza `_actividad` con el nuevo valor usando `postValue`.                   |
| **Bloque `init`**                   | Llama automáticamente al método `getActividades()` al inicializar el `ViewModel`, cargando la lista inicial de actividades.                                                       |
| **Librerías usadas**                | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `Dispatchers.IO`, y Retrofit para la gestión reactiva, concurrencia y llamadas a servicios REST.                          |

**ContratoViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ContratoViewModel`**              | Clase que extiende `ViewModel` y maneja la lógica relacionada con los contratos, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_contratos`**                 | `MutableLiveData<List<Contrato>>` que contiene una lista de contratos. Es privado para que solo pueda ser modificado dentro del `ViewModel`.                               |
| **Propiedad `contratos`**                  | `LiveData<List<Contrato>>` que expone los datos de `_contratos` de forma inmutable para que la vista observe la lista de contratos.                                        |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los contratos.          |
| **Método `getContratos()`**                | Llama al método `getContratos()` del servicio para obtener la lista de contratos del backend. Actualiza `_contratos` y maneja excepciones registrándolas en el log.         |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                    |
| **Llamada a `service.getContratos()`**     | Realiza la llamada HTTP para obtener los contratos desde el backend. Si tiene éxito, asigna la respuesta a `_contratos`.                                                  |
| **Actualización de `_contratos.value`**   | Si la llamada es exitosa, la lista de contratos obtenida se asigna a `_contratos`, lo que notifica a los observadores que los datos han cambiado.                         |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                  |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**CursoViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `CursoViewModel`**                 | Clase que extiende `ViewModel` y maneja la lógica relacionada con los cursos, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_cursos`**                    | `MutableLiveData<List<Curso>>` que contiene una lista de cursos. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                                     |
| **Propiedad `cursos`**                     | `LiveData<List<Curso>>` que expone los datos de `_cursos` de forma inmutable para que la vista observe la lista de cursos.                                                |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los cursos.            |
| **Método `getCursos()`**                   | Llama al método `getCursos()` del servicio para obtener la lista de cursos del backend. Actualiza `_cursos` y maneja excepciones registrándolas en el log.                 |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                    |
| **Llamada a `service.getCursos()`**        | Realiza la llamada HTTP para obtener los cursos desde el backend. Si tiene éxito, asigna la respuesta a `_cursos`.                                                      |
| **Actualización de `_cursos.value`**      | Si la llamada es exitosa, la lista de cursos obtenida se asigna a `_cursos`, lo que notifica a los observadores que los datos han cambiado.                             |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                  |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**DepartamentoViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `DepartamentoViewModel`**          | Clase que extiende `ViewModel` y maneja la lógica relacionada con los departamentos, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_departamentos`**             | `MutableLiveData<List<Departamento>>` que contiene una lista de departamentos. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                     |
| **Propiedad `departamentos`**              | `LiveData<List<Departamento>>` que expone los datos de `_departamentos` de forma inmutable para que la vista observe la lista de departamentos.                          |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los departamentos.     |
| **Método `getDepartamentos()`**            | Llama al método `getDepartamentos()` del servicio para obtener la lista de departamentos del backend. Actualiza `_departamentos` y maneja excepciones registrándolas en el log. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getDepartamentos()`** | Realiza la llamada HTTP para obtener los departamentos desde el backend. Si tiene éxito, asigna la respuesta a `_departamentos`.                                         |
| **Actualización de `_departamentos.value`**| Si la llamada es exitosa, la lista de departamentos obtenida se asigna a `_departamentos`, lo que notifica a los observadores que los datos han cambiado.                |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                  |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**EmpTransporteViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `EmpTransporteViewModel`**         | Clase que extiende `ViewModel` y maneja la lógica relacionada con los transportes de empleados, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_empTransporte`**             | `MutableLiveData<List<EmpTransporte>>` que contiene una lista de transportes de empleados. Es privada para que solo pueda ser modificada dentro del `ViewModel`.             |
| **Propiedad `empTransporte`**              | `LiveData<List<EmpTransporte>>` que expone los datos de `_empTransporte` de forma inmutable para que la vista observe la lista de transportes de empleados.                 |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los transportes.       |
| **Método `getEmpTransporte()`**            | Llama al método `getTransportes()` del servicio para obtener la lista de transportes de empleados del backend. Actualiza `_empTransporte` y maneja excepciones registrándolas en el log. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getTransportes()`**   | Realiza la llamada HTTP para obtener los transportes de empleados desde el backend. Si tiene éxito, asigna la respuesta a `_empTransporte`.                               |
| **Actualización de `_empTransporte.value`**| Si la llamada es exitosa, la lista de transportes obtenida se asigna a `_empTransporte`, lo que notifica a los observadores que los datos han cambiado.                  |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                  |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**FotoViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `FotoViewModel`**                  | Clase que extiende `ViewModel` y maneja la lógica relacionada con las fotos, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_foto`**                      | `MutableLiveData<Response<ResponseBody>>` que contiene la respuesta de una foto específica. Es privada para que solo pueda ser modificada dentro del `ViewModel`.           |
| **Propiedad `foto`**                       | `LiveData<Response<ResponseBody>>` que expone los datos de `_foto` de forma inmutable para que la vista observe la foto específica.                                        |
| **Propiedad `_fotos`**                     | `MutableLiveData<List<Foto>>` que contiene una lista de fotos. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                                     |
| **Propiedad `fotos`**                      | `LiveData<List<Foto>>` que expone los datos de `_fotos` de forma inmutable para que la vista observe la lista de fotos.                                                   |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener las fotos.            |
| **Método `getFotoActividad(actividadId, id)`** | Llama al método `getFotoActividad()` del servicio para obtener una foto específica de una actividad del backend. Actualiza `_foto` con la respuesta.                      |
| **Método `getFotos()`**                    | Llama al método `getFotos()` del servicio para obtener la lista de fotos del backend. Actualiza `_fotos` con la lista obtenida.                                          |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getFotoActividad()`** | Realiza la llamada HTTP para obtener una foto específica de una actividad desde el backend. Si tiene éxito, asigna la respuesta a `_foto`.                              |
| **Llamada a `service.getFotos()`**         | Realiza la llamada HTTP para obtener la lista de fotos desde el backend. Si tiene éxito, asigna la respuesta a `_fotos`.                                                |
| **Actualización de `_foto.value`**        | Si la llamada es exitosa, la foto obtenida se asigna a `_foto`, lo que notifica a los observadores que los datos han cambiado.                                           |
| **Actualización de `_fotos.value`**       | Si la llamada es exitosa, la lista de fotos obtenida se asigna a `_fotos`, lo que notifica a los observadores que los datos han cambiado.                                |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante las llamadas al backend y lo maneja imprimiéndolo en la consola.                                                                |
| **Inicialización**                         | Llama automáticamente al método `getFotos()` al inicializar el `ViewModel`, cargando la lista inicial de fotos.                                                           |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**GrupoParticipanteViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `GrupoParticipanteViewModel`**     | Clase que extiende `ViewModel` y maneja la lógica relacionada con los grupos de participantes, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_grupoParticipante`**         | `MutableLiveData<GrupoParticipante>` que contiene un único grupo de participante. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                   |
| **Propiedad `grupoParticipante`**          | `LiveData<GrupoParticipante>` que expone los datos de `_grupoParticipante` de forma inmutable para que la vista observe el grupo de participante.                        |
| **Propiedad `_gruposParticipantes`**       | `MutableLiveData<List<GrupoParticipante>>` que contiene una lista de grupos de participantes. Es privada para que solo pueda ser modificada dentro del `ViewModel`.        |
| **Propiedad `gruposParticipantes`**        | `LiveData<List<GrupoParticipante>>` que expone los datos de `_gruposParticipantes` de forma inmutable para que la vista observe la lista de grupos de participantes.       |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los grupos de participantes. |
| **Método `getGruposParticipantes()`**      | Llama al método `getGruposParticipantes()` del servicio para obtener la lista de grupos de participantes del backend. Actualiza `_gruposParticipantes` y maneja excepciones registrándolas en el log. |
| **Método `updateGrupoParticipante(grupoParticipante)`** | Actualiza un grupo de participante en el backend utilizando `updateGrupoParticipante()` del servicio. Luego, actualiza `_grupoParticipante` con el nuevo valor. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getGruposParticipantes()`** | Realiza la llamada HTTP para obtener los grupos de participantes desde el backend. Si tiene éxito, asigna la respuesta a `_gruposParticipantes`.                        |
| **Llamada a `service.updateGrupoParticipante()`** | Realiza la llamada HTTP para actualizar un grupo de participante en el backend. Luego, asigna el grupo actualizado a `_grupoParticipante`. |
| **Actualización de `_grupoParticipante.value`** | Si la actualización es exitosa, se asigna el grupo actualizado a `_grupoParticipante`, lo que notifica a los observadores que los datos han cambiado. |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante las llamadas al backend y lo maneja imprimiéndolo en la consola.                                                                |
| **Uso de `CoroutineScope(Dispatchers.IO).launch {}`** | Utiliza `CoroutineScope` con `Dispatchers.IO` para realizar la operación de actualización de forma asíncrona en el hilo adecuado.                                          |
| **Log de actualizaciones**                 | Registra en el log un mensaje de éxito cuando un grupo de participante es actualizado correctamente.                                                                   |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch`, `CoroutineScope`, `Dispatchers.IO`, `Log` para la gestión reactiva, ejecución asíncrona y el registro de eventos. |

**GrupoViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `GrupoViewModel`**                 | Clase que extiende `ViewModel` y maneja la lógica relacionada con los grupos, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_grupos`**                    | `MutableLiveData<List<Grupo>>` que contiene una lista de grupos. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                                   |
| **Propiedad `grupos`**                     | `LiveData<List<Grupo>>` que expone los datos de `_grupos` de forma inmutable para que la vista observe la lista de grupos.                                               |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los grupos.           |
| **Método `getGrupos()`**                   | Llama al método `getGrupos()` del servicio para obtener la lista de grupos del backend. Actualiza `_grupos` con la lista obtenida y maneja excepciones registrándolas en el log. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getGrupos()`**        | Realiza la llamada HTTP para obtener los grupos desde el backend. Si tiene éxito, asigna la respuesta a `_grupos`.                                                      |
| **Actualización de `_grupos.value`**      | Si la llamada es exitosa, la lista de grupos obtenida se asigna a `_grupos`, lo que notifica a los observadores que los datos han cambiado.                              |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                           |

**ProfesorLoginViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ProfesorLoginViewModel`**         | Clase que extiende `ViewModel` y maneja la lógica de inicio de sesión para un profesor, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_loginResult`**               | `MutableLiveData<Profesor?>` que contiene el resultado del inicio de sesión, es decir, el objeto `Profesor` que se obtiene si las credenciales son correctas.               |
| **Propiedad `loginResult`**                | `LiveData<Profesor?>` que expone los datos de `_loginResult` de forma inmutable para que la vista observe el resultado del login.                                          |
| **Propiedad `_errorMessage`**              | `MutableLiveData<String?>` que contiene el mensaje de error en caso de que el inicio de sesión falle.                                                                    |
| **Propiedad `errorMessage`**               | `LiveData<String?>` que expone los datos de `_errorMessage` de forma inmutable para que la vista observe el mensaje de error en caso de fallo.                             |
| **Propiedad `_isLoading`**                 | `MutableLiveData<Boolean>` que indica si el proceso de inicio de sesión está en curso.                                                                                   |
| **Propiedad `isLoading`**                  | `LiveData<Boolean>` que expone los datos de `_isLoading` de forma inmutable para que la vista observe si el proceso está cargando.                                        |
| **Método `login(correo, password)`**       | Inicia el proceso de inicio de sesión llamando al método `login()` del servicio y pasando las credenciales del profesor.                                                  |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de inicio de sesión de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido. |
| **Simulación de retraso**                  | Aunque no es necesario, el comentario sugiere una simulación de retraso de 2 segundos para la llamada al backend, posiblemente para simular un tiempo de espera realista.   |
| **Llamada a `service.login()`**            | Realiza la llamada HTTP para autenticar al profesor usando el correo y la contraseña proporcionados. Si tiene éxito, asigna el objeto `Profesor` a `_loginResult`.           |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y actualiza `_errorMessage` con un mensaje indicando el fallo en el inicio de sesión.                        |
| **Actualización de `_isLoading.value`**   | Durante la ejecución del proceso de login, `_isLoading` se establece a `true` para indicar que el proceso está en curso. Se cambia a `false` una vez que la operación finaliza, ya sea con éxito o error. |
| **Uso de `withContext(Dispatchers.IO)`**   | Cambia al contexto de `IO` para realizar la operación de login en un hilo adecuado para operaciones de entrada/salida, como llamadas de red.                                |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch`, `Dispatchers.IO`, y `withContext` para la gestión reactiva, ejecución asíncrona y manejo de estados.      |

**ProfesorViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ProfesorViewModel`**              | Clase que extiende `ViewModel` y maneja la lógica relacionada con los profesores, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_profesores`**                | `MutableLiveData<List<Profesor>>` que contiene una lista de profesores. Es privada para que solo pueda ser modificada dentro del `ViewModel`.                           |
| **Propiedad `profesores`**                 | `LiveData<List<Profesor>>` que expone los datos de `_profesores` de forma inmutable para que la vista observe la lista de profesores.                                     |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los profesores.       |
| **Método `getProfesores()`**               | Llama al método `getProfesores()` del servicio para obtener la lista de profesores desde el backend. Actualiza `_profesores` con la lista obtenida y maneja excepciones registrándolas en el log. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getProfesores()`**    | Realiza la llamada HTTP para obtener los profesores desde el backend. Si tiene éxito, asigna la respuesta a `_profesores`.                                                |
| **Actualización de `_profesores.value`**  | Si la llamada es exitosa, la lista de profesores obtenida se asigna a `_profesores`, lo que notifica a los observadores que los datos han cambiado.                       |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                 |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                             |

**ProfParticipanteViewModel**
| **Sección del código**                     | **Descripción**                                                                                                                                                          |
|--------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ProfParticipanteViewModel`**      | Clase que extiende `ViewModel` y maneja la lógica relacionada con los profesores participantes, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_profesoresParticipantes`**   | `MutableLiveData<List<ProfParticipante>>` que contiene una lista de profesores participantes. Es privada para que solo pueda ser modificada dentro del `ViewModel`.      |
| **Propiedad `profesoresParticipantes`**    | `LiveData<List<ProfParticipante>>` que expone los datos de `_profesoresParticipantes` de forma inmutable para que la vista observe la lista de profesores participantes.    |
| **Propiedad `service`**                    | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los profesores participantes. |
| **Método `getProfesoresParticipantes()`**  | Llama al método `getProfesoresParticipantes()` del servicio para obtener la lista de profesores participantes desde el backend. Actualiza `_profesoresParticipantes` con la lista obtenida. |
| **Bloque `viewModelScope.launch {}`**      | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getProfesoresParticipantes()`** | Realiza la llamada HTTP para obtener los profesores participantes desde el backend. Si tiene éxito, asigna la respuesta a `_profesoresParticipantes`.                        |
| **Actualización de `_profesoresParticipantes.value`** | Si la llamada es exitosa, la lista de profesores participantes obtenida se asigna a `_profesoresParticipantes`, lo que notifica a los observadores que los datos han cambiado. |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                 |
| **Librerías usadas**                       | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                             |

**ProfResponsableViewModel**
| **Sección del código**                      | **Descripción**                                                                                                                                                          |
|---------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Clase `ProfResponsableViewModel`**        | Clase que extiende `ViewModel` y maneja la lógica relacionada con los profesores responsables, siguiendo el patrón MVVM. Utiliza `LiveData` para que la vista observe los cambios en los datos. |
| **Propiedad `_profesoresResponsables`**     | `MutableLiveData<List<ProfResponsable>>` que contiene una lista de profesores responsables. Es privada para que solo pueda ser modificada dentro del `ViewModel`.      |
| **Propiedad `profesoresResponsables`**      | `LiveData<List<ProfResponsable>>` que expone los datos de `_profesoresResponsables` de forma inmutable para que la vista observe la lista de profesores responsables.    |
| **Propiedad `service`**                     | Instancia del servicio Retrofit creada a través de `RetrofitServiceFactory`. Permite realizar llamadas HTTP para interactuar con el backend y obtener los profesores responsables. |
| **Método `getProfesoresResponsables()`**    | Llama al método `getProfesoresResponsables()` del servicio para obtener la lista de profesores responsables desde el backend. Actualiza `_profesoresResponsables` con la lista obtenida. |
| **Bloque `viewModelScope.launch {}`**       | Inicia una corutina en el `viewModelScope` para ejecutar la llamada de manera asíncrona, asegurando que se cancele cuando el `ViewModel` sea destruido.                   |
| **Llamada a `service.getProfesoresResponsables()`** | Realiza la llamada HTTP para obtener los profesores responsables desde el backend. Si tiene éxito, asigna la respuesta a `_profesoresResponsables`.                        |
| **Actualización de `_profesoresResponsables.value`** | Si la llamada es exitosa, la lista de profesores responsables obtenida se asigna a `_profesoresResponsables`, lo que notifica a los observadores que los datos han cambiado. |
| **Manejo de excepciones `catch (e: Exception)`** | Captura cualquier error ocurrido durante la llamada al backend y lo maneja imprimiéndolo en la consola.                                                                 |
| **Librerías usadas**                        | Utiliza `LiveData`, `MutableLiveData`, `viewModelScope`, `launch` para la gestión reactiva y la ejecución asíncrona de tareas.                                             |

## data

**Actividad**
| **Propiedad**           | **Tipo**               | **Descripción**                                                                                                     |
|-------------------------|------------------------|---------------------------------------------------------------------------------------------------------------------|
| **Clase**               | `Actividad`            | Representa una actividad o evento con detalles sobre su tipo, fechas, requisitos logísticos, estado y ubicación.     |
| `id`                    | `Int?`                 | Identificador único de la actividad (puede ser nulo).                                                              |
| `titulo`                | `String`               | Título de la actividad.                                                                                             |
| `tipo`                  | `String`               | Tipo de actividad: "Complementaria" o "Extraescolar".                                                               |
| `descripcion`           | `String?`              | Descripción opcional de la actividad.                                                                               |
| `fini`                  | `String`               | Fecha de inicio de la actividad (formato de fecha como cadena).                                                     |
| `ffin`                  | `String`               | Fecha de finalización de la actividad (formato de fecha como cadena).                                               |
| `hini`                  | `String`               | Hora de inicio de la actividad (formato de hora como cadena).                                                       |
| `hfin`                  | `String`               | Hora de finalización de la actividad (formato de hora como cadena).                                                 |
| `previstaIni`           | `Boolean`              | Indica si la actividad tiene una fecha de inicio prevista.                                                          |
| `transporteReq`         | `Boolean`              | Indica si la actividad requiere transporte.                                                                         |
| `comentTransporte`      | `String?`              | Comentarios opcionales sobre el transporte.                                                                         |
| `alojamientoReq`        | `Boolean`              | Indica si la actividad requiere alojamiento.                                                                        |
| `comentAlojamiento`     | `String?`              | Comentarios opcionales sobre el alojamiento.                                                                        |
| `comentarios`           | `String?`              | Comentarios adicionales sobre la actividad.                                                                         |
| `estado`                | `String`               | Estado de la actividad (por ejemplo, "SOLICITADA", "APROBADA", etc.).                                               |
| `comentEstado`          | `String?`              | Comentarios opcionales sobre el estado de la actividad.                                                             |
| `incidencias`           | `String?`              | Información sobre incidencias que hayan ocurrido durante la actividad.                                              |
| `urlFolleto`            | `String?`              | URL al folleto o documento relacionado con la actividad.                                                           |
| `solicitante`           | `Profesor`             | Profesor que solicita la actividad (es una referencia a la clase `Profesor`).                                        |
| `importePorAlumno`      | `Double?`              | Importe que se asignará a cada alumno para la actividad (puede ser nulo).                                           |
| `latitud`               | `String?`              | Latitud de la ubicación de la actividad (puede ser nula).                                                           |
| `longitud`              | `String?`              | Longitud de la ubicación de la actividad (puede ser nula).                                                          |

**ActividadResponse**
| **Propiedad**  | **Tipo**           | **Descripción**                                                                                     |
|----------------|--------------------|-----------------------------------------------------------------------------------------------------|
| **Clase**      | `ActividadResponse` | Representa una respuesta de la API que contiene una lista de actividades y el estado de la solicitud. |
| `data`         | `List<Actividad>`   | Lista de objetos `Actividad` que representan las actividades obtenidas de la API (por defecto está vacía). |
| `status`       | `String`           | Estado de la respuesta de la API (por ejemplo, puede indicar si la solicitud fue exitosa o no).       |

**Contrato**
| **Propiedad**       | **Tipo**            | **Descripción**                                                                                           |
|---------------------|---------------------|-----------------------------------------------------------------------------------------------------------|
| **Clase**           | `Contrato`          | Representa un contrato para una actividad, incluyendo detalles sobre transporte, importe y documentación.  |
| `id`                | `Int`               | Identificador único del contrato.                                                                         |
| `actividad`         | `Actividad`         | Objeto que representa la actividad asociada a este contrato.                                              |
| `empTransporteId`   | `EmpTransporte`     | Objeto que representa la empresa de transporte asociada al contrato.                                       |
| `contratada`        | `Boolean`           | Indica si el servicio de transporte ha sido contratado (`true`) o no (`false`).                          |
| `importe`           | `Double`            | Importe del contrato.                                                                                     |
| `urlPresupuesto`    | `String?`           | URL del presupuesto asociado al contrato (puede ser `null` si no está disponible).                        |
| `urlFactura`        | `String?`           | URL de la factura asociada al contrato (puede ser `null` si no está disponible).                          |

**ContratoResponse**
| **Propiedad** | **Tipo**          | **Descripción**                                                                                      |
|----------------|-------------------|------------------------------------------------------------------------------------------------------|
| **Clase**     | `ContratoResponse` | Representa la respuesta de una API que devuelve una lista de contratos y un estado de la solicitud.  |
| `data`        | `List<Contrato>`   | Lista de objetos `Contrato`, que contiene los contratos obtenidos.                                    |
| `status`      | `String`           | Estado de la respuesta, usualmente utilizado para indicar si la operación fue exitosa o fallida.      |

**Curso**
| **Propiedad**   | **Tipo**      | **Descripción**                                                                                          |
|-----------------|---------------|----------------------------------------------------------------------------------------------------------|
| **Clase**       | `Curso`       | Representa un curso educativo, con detalles sobre su identificación, nivel y estado.                      |
| `id`            | `Int`         | Identificador único del curso.                                                                             |
| `codCurso`      | `String`      | Código único que identifica al curso.                                                                      |
| `titulo`        | `String`      | Título del curso, describe su nombre o temática.                                                           |
| `etapa`         | `String`      | Etapa educativa en la que se encuentra el curso (por ejemplo, primaria, secundaria, etc.).                 |
| `nivel`         | `Int`         | Nivel del curso, representado por un número (por ejemplo, 1 para primer nivel, 2 para segundo nivel, etc.).|
| `activo`        | `Boolean`     | Indica si el curso está activo (`true`) o no (`false`).                                                   |

**CursoResponse**
| **Propiedad**   | **Tipo**      | **Descripción**                                                                                          |
|-----------------|---------------|----------------------------------------------------------------------------------------------------------|
| **Clase**       | `CursoResponse` | Representa la respuesta de una API o servicio que contiene información sobre cursos.                     |
| `data`          | `List<Curso>` | Lista de objetos `Curso` que contiene la información de los cursos.                                         |
| `status`        | `String`      | Estado de la respuesta, utilizado para indicar si la solicitud fue exitosa o si ocurrió un error.         |

**Departamento**
| **Propiedad**   | **Tipo**      | **Descripción**                                                                                          |
|-----------------|---------------|----------------------------------------------------------------------------------------------------------|
| **Clase**       | `Departamento`| Representa un departamento en una organización o institución.                                            |
| `id`            | `Int`         | Identificador único del departamento.                                                                     |
| `codigo`        | `String`      | Código único que identifica al departamento.                                                              |
| `nombre`        | `String`      | Nombre del departamento, describe la unidad dentro de la organización.                                    |

**DepartamentoResponse**
| **Propiedad** | **Tipo**       | **Descripción**                                                                                             |
|---------------|----------------|-------------------------------------------------------------------------------------------------------------|
| **Clase**     | `DepartamentoResponse` | Representa una respuesta que contiene una lista de departamentos y el estado de la respuesta.                 |
| `data`        | `List<Departamento>` | Lista de objetos `Departamento` que contiene todos los departamentos que fueron solicitados.                |
| `status`      | `String`       | Estado de la respuesta, generalmente usado para indicar si la solicitud fue exitosa u ocurrió algún error.   |

**EmpTransporte**
| **Propiedad** | **Tipo**       | **Descripción**                                                                                             |
|---------------|----------------|-------------------------------------------------------------------------------------------------------------|
| **Clase**     | `EmpTransporte` | Representa una empresa de transporte con detalles sobre su identidad y contacto.                           |
| `id`          | `Int`          | Identificador único de la empresa de transporte.                                                              |
| `nombre`      | `String`       | Nombre de la empresa de transporte.                                                                          |
| `cif`         | `String`       | Código de identificación fiscal de la empresa.                                                               |
| `direccion`   | `String?`      | Dirección física de la empresa (puede ser nula).                                                             |
| `cp`          | `String?`      | Código postal de la empresa (puede ser nulo).                                                                |
| `localidad`   | `String?`      | Localidad o ciudad donde se encuentra la empresa (puede ser nula).                                           |
| `contacto`    | `String?`      | Información de contacto de la empresa (puede ser nula).                                                      |

**EmpTransporteResponse**
| **Propiedad** | **Tipo**       | **Descripción**                                                                                             |
|---------------|----------------|-------------------------------------------------------------------------------------------------------------|
| **Clase**     | `EmpTransporteResponse` | Representa la respuesta de una solicitud relacionada con las empresas de transporte.                     |
| `data`        | `List<EmpTransporte>` | Lista de objetos `EmpTransporte` que contienen la información de las empresas de transporte.               |
| `status`      | `String`       | Estado de la respuesta, por ejemplo, "exitoso" o "error", que indica el resultado de la operación.         |

**Foto**
| **Propiedad**   | **Tipo**    | **Descripción**                                                                 |
|-----------------|-------------|---------------------------------------------------------------------------------|
| **Clase**       | `Foto`      | Representa una foto asociada a una actividad.                                   |
| `id`            | `Int`       | Identificador único de la foto.                                                  |
| `urlFoto`       | `String`    | URL donde se encuentra almacenada la foto.                                      |
| `descripcion`   | `String`    | Descripción breve de la foto.                                                    |
| `actividadId`   | `Int`       | Identificador de la actividad con la que está relacionada la foto.               |

**FotoResponse**
| **Propiedad**   | **Tipo**    | **Descripción**                                                                 |
|-----------------|-------------|---------------------------------------------------------------------------------|
| **Clase**       | `FotoResponse` | Representa una respuesta de la API que contiene una lista de fotos.              |
| `data`          | `List<Foto>`| Lista de objetos `Foto`, que son las fotos asociadas a actividades.              |
| `status`        | `String`    | Estado de la solicitud, generalmente se usa para indicar si la respuesta fue exitosa o no. |

**Grupo**
| **Propiedad**    | **Tipo**    | **Descripción**                                                                 |
|------------------|-------------|---------------------------------------------------------------------------------|
| **Clase**        | `Grupo`     | Representa un grupo dentro de un curso.                                          |
| `id`             | `Int`       | Identificador único del grupo.                                                   |
| `curso`          | `Curso`     | Objeto que representa el curso al que pertenece el grupo.                       |
| `codGrupo`       | `String`    | Código único del grupo dentro del curso.                                         |
| `numAlumnos`     | `Int`       | Número de alumnos en el grupo.                                                   |
| `activo`         | `Boolean`   | Indica si el grupo está activo o no.                                             |
| `tutor`          | `Profesor`  | Profesor que actúa como tutor del grupo.                                         |

**GrupoResponse**
| **Propiedad**    | **Tipo**    | **Descripción**                                                       |
|------------------|-------------|-----------------------------------------------------------------------|
| **Clase**        | `GrupoResponse` | Representa la respuesta de una API para obtener grupos.                |
| `data`           | `List<Grupo>`    | Lista de objetos `Grupo` que representan los grupos disponibles.       |
| `status`         | `String`    | Estado de la respuesta, típicamente usado para indicar si la operación fue exitosa o si hubo algún error. |

**GrupoParticipante**
| **Propiedad**        | **Tipo**            | **Descripción**                                                             |
|----------------------|---------------------|-----------------------------------------------------------------------------|
| **Clase**            | `GrupoParticipante`  | Representa la participación de un grupo en una actividad.                   |
| `id`                 | `Int?`               | Identificador único de la participación del grupo (puede ser nulo).          |
| `actividades`        | `Actividad`         | Objeto de tipo `Actividad` que representa la actividad a la que pertenece el grupo. |
| `grupo`              | `Grupo`             | Objeto de tipo `Grupo` que representa el grupo que participa en la actividad.|
| `numParticipantes`   | `Int`               | Número de participantes en la actividad del grupo.                          |
| `comentario`         | `String?`           | Comentario opcional relacionado con la participación del grupo en la actividad. |

**GrupoParticipanteResponse**
| **Propiedad**        | **Tipo**            | **Descripción**                                                             |
|----------------------|---------------------|-----------------------------------------------------------------------------|
| **Clase**            | `GrupoParticipanteResponse`  | Representa la respuesta que contiene una lista de `GrupoParticipante`. |
| `data`               | `List<GrupoParticipante>` | Lista de objetos `GrupoParticipante` que contiene los grupos participantes. |
| `status`             | `String`            | Estado de la solicitud (por ejemplo, "success", "error").                    |

**Profesor**
| **Propiedad**        | **Tipo**            | **Descripción**                                                             |
|----------------------|---------------------|-----------------------------------------------------------------------------|
| **Clase**            | `Profesor`          | Representa a un profesor en el sistema.                                     |
| `uuid`               | `String`            | Identificador único del profesor.                                            |
| `dni`                | `String`            | Documento Nacional de Identidad del profesor.                               |
| `nombre`             | `String`            | Nombre del profesor.                                                        |
| `apellidos`          | `String`            | Apellidos del profesor.                                                     |
| `correo`             | `String`            | Dirección de correo electrónico del profesor.                               |
| `password`           | `String`            | Contraseña del profesor (debería ser encriptada en una implementación real). |
| `rol`                | `String`            | Rol del profesor, como "Profesor", "Jefe de Departamento", etc.              |
| `activo`             | `Boolean`           | Estado de actividad del profesor (si está activo o no).                      |
| `urlFoto`            | `String?`           | URL de la foto del profesor (opcional).                                      |
| `esJefeDep`          | `Boolean?`          | Indica si el profesor es jefe de departamento (opcional).                   |
| `depart`             | `Departamento`      | Objeto que representa el departamento al que pertenece el profesor.         |

**ProfesorResponse**
| **Propiedad**        | **Tipo**             | **Descripción**                                                             |
|----------------------|----------------------|-----------------------------------------------------------------------------|
| **Clase**            | `ProfesorResponse`   | Contenedor para la respuesta de una solicitud de profesores.                |
| `data`               | `List<Profesor>`     | Lista de objetos `Profesor` que contiene los detalles de los profesores.    |
| `status`             | `String`             | Estado de la respuesta, generalmente utilizado para indicar si la solicitud fue exitosa o fallida (ejemplo: "success", "error"). |

**ProfParticipante**
| **Propiedad**        | **Tipo**             | **Descripción**                                                             |
|----------------------|----------------------|-----------------------------------------------------------------------------|
| **Clase**            | `ProfParticipante`   | Representa la relación entre un profesor y una actividad.                   |
| `id`                 | `Int`                | Identificador único para la relación entre profesor y actividad.            |
| `actividad`          | `Actividad`          | El objeto `Actividad` que indica a qué actividad está asociado el profesor. |
| `profesor`           | `Profesor`           | El objeto `Profesor` que representa al docente que participa en la actividad.|

**ProfParticipanteResponse**
| **Propiedad**        | **Tipo**             | **Descripción**                                                             |
|----------------------|----------------------|-----------------------------------------------------------------------------|
| **Clase**            | `ProfParticipanteResponse` | Representa una respuesta que contiene una lista de objetos `ProfParticipante`. |
| `data`               | `List<ProfParticipante>` | Lista de objetos `ProfParticipante`, cada uno representando una relación entre un profesor y una actividad. |
| `status`             | `String`             | Estado de la respuesta de la API, por ejemplo, "success" o "error".         |

**ProfResponsable**
| **Propiedad**        | **Tipo**             | **Descripción**                                                             |
|----------------------|----------------------|-----------------------------------------------------------------------------|
| **Clase**            | `ProfResponsable`     | Representa la relación entre un profesor y una actividad, donde el profesor es responsable de esa actividad. |
| `id`                 | `Int`                | Identificador único de la relación entre el profesor y la actividad.         |
| `actividad`          | `Actividad`          | Objeto que representa la actividad en la cual el profesor es responsable.   |
| `profesor`           | `Profesor`           | Objeto que representa al profesor que es responsable de la actividad.       |

**ProfResponsableResponse**
| **Propiedad**        | **Tipo**             | **Descripción**                                                             |
|----------------------|----------------------|-----------------------------------------------------------------------------|
| **Clase**            | `ProfResponsableResponse` | Representa la respuesta de una API que contiene una lista de objetos `ProfResponsable`. |
| `data`               | `List<ProfResponsable>` | Lista de objetos `ProfResponsable`, que representan la relación entre un profesor y una actividad. |
| `status`             | `String`             | Estado de la respuesta de la API, generalmente indica si la solicitud fue exitosa o falló (por ejemplo, "OK", "Error"). |

# Conclusión
El proyecto tiene una arquitectura bien organizada, separando responsabilidades por carpetas (modelo, vistas, lógica de negocio y navegación). Se usa Retrofit para la interacción con APIs y Jetpack Compose o XML para la interfaz de usuario. Las vistas y los ViewModels manejan bien la separación de la lógica y el estado de la interfaz.

