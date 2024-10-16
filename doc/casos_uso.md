# Roles y casos de uso

## Roles

Hay tres: invitado, cliente, y trabajador.

El rol de administrador se deja sin hacer, asumimos que los trabajadores de rocódromos y los rocódromos se dan de alta en la base de datos de antemano, y nos centramos en lo demás (que para el tfg es más que de sobra)

## Casos de uso fundamentales (el core de la aplicación)

### Invitado

Sin tener una cuenta de cliente, un invitado puede:

- `SCAN QR`
  - Accede a detalles vía mediante QR. Esto le muestra las siguientes características:
  - Podrá ver detalles del rocódromo al que pertenece la via que ha escaneado. (Nombre, dirección, localidad, telefono...)
  - Podrá ver detalles de la propia via. (Nombre, dificultad, color, fecha de creación...).
  - Podrá ver la lista de vídeos que han sido subidos a esa vía.
  - Podrá ver datos de los videos (Nombre del autor...).
  - Podrá reproducir los vídeos desde la propia aplicación.
  - Podrá volver a la pantalla inicial de Login.
  - En ningun caso podrá navegar en la app para ver el resto de vías y rocódromos.

- `REGISTRARSE`
  - Accede a un formulario donde puede introducir los datos y credenciales de su usuario.
  - Volver a la pantalla inicial de Login.

- `INICIAR SESEIÓN`
  - Introduce su correo y contraseña para acceder a la app.

### Cliente

El cliente tendrá todos los casos de uso del invitado, menos obviamente los de iniciar sesión y registrarse.

- `Cerrar sesión`
- `SCAN QR`

- `Ver lista de rocódromos` (Mostrando los principales datos de cada uno).
  - Pulsando sobre cualquiera de ellos, accedemos a detalle rocódromo.
- `Ver detalle rocódromo` (Muestra los datos del rocódromo y su lista de Vias).
  - Pulsado sobre cualquier vía, accedemos a detalle Via (Mismo resultado que SCAN QR) 
  - Pulsando sobre el rocódromo, volvemos a la lista de rocódromos.
- `Ver detalle via`: Muestra detalles del rocódromo, la vía y la lista de videos que se pueden reproducir.
  - Pulsando sobre el rocódromo, accedemos a la lista de rocódromos.
  - Pulsando sobre la via, accedemos a la lista de vías del rocódromo.

- `Añadir vídeo a vía`: (Desde la pantalla principal y desde la pantalla DetallesVia)
- `Listar mis vídeos`: Muestra la lista de videos, viendo sus datos y pudiendo reproducirlos, editarlos y eliminarlos.
  - `Editar vídeo`: Se muestra un modal, donde aparecen los datos actuales del video, pudiendo modificarlos y guardar los cambios.
  - `Borrar vídeo`: Con su correspondiente mensaje de confirmación.

### Trabajador

  - `Ver rocódromo`: (Solo puede acceder a los datos de su rocódromo) Accede a la lista de vías.
  - `Edita vía`: Salta un modal, para editar los datos principales de la via.
  - `Eliminar vía`: Solo puede dar de baja una via que no tenga videos.
  - `Ver detalles via` (Mismo resultado que SCAN QR)
    - `Da de baja vídeo`: Elimina el video.
    - `Editar Via`. (Ya explicado)
    - `Eliminar Via`. (Ya explicado)
    - `Volver a la lista de vías`. (Pulsado sonre la vía o sobre el botón "Volver")
- `Da de alta vía`: Accede a un formulario para crear un vía de su rocódromo.
- `SCAN QR`
- `Cerrar sesión`

## Casos de uso extra

Todo esto es opcional/casos que se me han ocurrido sobre la marcha, se puede decidir que merece la pena hacerlo en base a lo que se descubra con el desarrollo de la parte fundamental, pero no hay ninguna obligación para hacerlo.

### Filtrado y ordenación de vías (Pdte)

Como cliente, se podría permitir a los usuarios ver vías antiguas, que ya hayan sido dadas de baja, y sus vídeos asociados.
Como cliente, quiero poder ordenar las vías, por nivel, fecha de creación...

### Ordenación de videos (Pdte)

Como cliente, quiero poder ordenar los videos por duración, para saber quién ha escalado la ruta más rápido.


### Filtrar por localidad (Pdte)

Como cliente, es interesante poder filtrar los rocódromos por localidad.

### Vías/Rocódromos favoritos (Pdte)

Para poder acceder rápidamente a ellos.

### Ver vídeos de un cliente concreto (Pdte)

Porque nos guste cómo escala.

## Modelo de datos 

Creo que está todo claro, menos quizá los detalles a almacenar de cada vía.

### Vía

- `id_via`: Identificador único de una vía, interno a la aplicación, numérico y único globalmente por sencillez.

- `qr_via`: Único globalmente. Formato `Rocódromo/Vía`  (Ej. Treparriscos/1) ~~Este QR puede que no sea único entre vías. Por ejemplo, si cada rocódromo compra unas etiquetas QR y en las etiquetas pone poco más que un "1", o un "Trepariscos/1", pues cuando esa vía se de de baja se podría reutilizar esa etiqueta en otro sitio. Esto me hace pensar en cómo sería el proceso de leer un QR: se debería indicar, aparte, en qué rocódromo nos encontramos? De esta forma, cualquier QR podría valer.~~

De todas formas, a nivel de este TFG podemos asumir que el QR de una vía es único y tiene el formato que queramos, en plan `Treparriscos-1`, y pensar que `Treparriscos` identifica únivocamente al rocódromo, y que no se usará ese código de vía más veces.

- `nombre`: si se le quiere poner un nombre, para que se use en la app, estaría bien. Las vías no tienen, pero igual en la app queda raro que no lo haya. Si no, se pone una foto y ya está.

- `foto`: (Pdte) habría que ver cómo gestionar esto en Postgres, o usar un sistema de terceros para subir las fotos programáticamente. Si bien los vídeos sí que nos apoyaremos en otras plataformas como Youtube, estas fotos creo que deberíamos gestionarlas nosotros.

- `tipo`: bloque (o "boulder", más conocido) o vía. Quizá esto nos haga pensar en buscar otro nombre que no sea vía, ya que una vía en sí es solo un tipo de ruta, las que vas atado con cuerda o autoasegurador. O en el código llamamos vía a todo, pero cuando lo pintemos en la app usamos el tipo, y ponemos bloque/boulder o vía concretamente.

- `presas`: esto sería el color. Creo que llamarlo presas será más adecuado porque el nivel también se suele marcar con un color, así se tiene claro de qué hablamos.

- `num_nivel`: numérico, del 1 al que sea. Esto evita problemas como los que comento en el siguiente punto.

- `texto_nivel`: pongo texto, pero generalmente es un color. Lo que pasa es que los colores no se usan consistentemente entre rocódromos. Por ejemplo, en el Quality, el Indian y el Treparriscos hay nivel `1(blanco)`. En cambio, los niveles 2 y 3 dependen de en cuál estés: en el Quality es `2(verde)` y `3(azul)`, y en el Indian y el Trepa son `2(azul)` y `3(verde)`. En el 4 y 5 vuelven a coincidir los tres, con `4(amarillo)` y `5(naranja)`. Por eso veo necesario un número que acompañe al texto, que será un color casi siempre. Esto no creo que sea maś que añadir filas de niveles específicas para cada rocódromo, y ya está.



### Video

- `id_video`: Identificador único del video, interno a la aplicación, numérico y único globalmente por sencillez.

- `descripción`:  Pequeño campo para decribir el video. Opcional.

- `fecha de publicación`: Fecha de publicación del video. Para poder filtrar los más recientes, por ejemplo.

- `titulo`: Titulo del vídeo.

- `url`: Url del vídeo. Solo admite videos de YouTube. Normales y Shorts.



### Rocódromo

- `id_rocódromo`: Identificador único del rocódromo, interno a la aplicación, numérico y único globalmente por sencillez.

- `dirección`:  Dirección exacta del rocódromo.

- `localidad`: Localidad donde está situado el rocódromo. Puede ser útil para filtrar.

- `email`: Correo electrónico para contactar con el rocódromo.

- `telefono 1`: Teléfono de contacto.

- `teléfono 2`: Opcional. En caso de que tenga fijo y móvil, o varios móviles.



### Usuario

- `id_user`: Identificador único del usuario, interno a la aplicación, numérico y único globalmente por sencillez.

- `apellido`:  Apellido del usuario

- `nombre`: Nombre del usuario

- `email`: Correo electrónico para contactar con el usuario.

- `contraseña`: Contraseña para acceder a su cuenta.

- `rol`: Solo puede completar este campo un admin. Identifica el tipo de usuario. Por defecto es de tipo USER (Usuario normal). Esto permite filtrar la accesibilidad de la aplicación.
- `rocódromo`: En caso de seleccionar un rol, suponemos que el usuario va a ser un trabajador. Con este campo indicamos para que rocódromo trabaja.
