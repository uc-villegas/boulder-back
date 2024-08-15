# Gestion de roles

En la aplicación, cuando un usuario inicia sesión, el backend devolverá el rol del usuario junto con el resto de la información del usuario.
Esta información se usará para gestionar qué opciones de la interfaz de usuario se mostrarán.


## User

Tipo de usuario por defecto, que será creado cada vez que una persona se registre en la aplicación.
Un usuario normal no puede modificar su rol.

## Worker

Creado por un admin. Asociado a un rocodromo. Permite editar y borrar sus vías y videos.

## Admin

Permite crear usuarios de tipo Worker.