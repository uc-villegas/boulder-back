# Gestion de roles

En la aplicación, cuando un usuario inicia sesión, el backend devolverá el rol del usuario junto con el resto de la información del usuario.
Esta información se usará para filtrar los elementos de la interfaz accesibles a cada tipo de usuario.


## User

Tipo de usuario por defecto, que será creado cada vez que una persona se registre en la aplicación.
Un usuario normal no puede modificar su rol.

## Worker

Creado por un admin. Gestiona los videos y vías de un solo rocódromo, permitiendo editar y borrar sus vías y videos.

## Admin

Gestiona la aplicación. Permite crear rocódromos y usuarios de tipo Worker.