# Contratos Front-Back

## Vista Cliente

- Get Rocódromos: Al dar al botón ver rocódromos. Para ver la lista de rocódromos.
    - GET http://localhost:8080/api/v1/boulders

    - JSON Response
      [
      {
      "idBoulder": 1,
      "name": "Treparriscos",
      "address": "C/Malasaña, 42",
      "locality": "Maliaño",
      "mail": "trepa@gmail.com",
      "phone": "942 111 111",
      "phone2": null
      }
      ]


- Get Vias: Para ver la lista de vías de un rocódromo seleccionado.
    - GET http://localhost:8080/api/v1/{idBoulder}/vias

    - JSON Response
      [
      {
      "idRoute": 1,
      "qrRoute": "Treparriscos/1",
      "name": "Via 1",
      "type": "BOULDER",
      "num_nivel": 1,
      "presa": "Azul",
      "creationDate": "2024-07-28"
      }, ...
      ]


- Get Vía: Para ver los datos de la via clickada, incluida su biblio de videos. Además, a través del QR, dependiendo del dato leído, haremos un getVia para mostrar sus datos y videos.
    - GET http://localhost:8080/api/v1/{idBoulder}/{idRoute}

    - JSON Response
      {
      "idRoute": 17,
      "qrRoute": "Rascamuros/1",
      "name": "Via 2",
      "type": "BOULDER",
      "num_nivel": 2,
      "presa": "Naranja",
      "creationDate": "28-07-2024",
      "videos": [MOSTRAR VIDEOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS, Y TAPAR/MOSTRAR LA LISTA CON UN CLICK]
      }


- Get Usuario: Para ver los videos que ha subido un usuario. En la lista de videos puede hacer click en su nombre.
    - GET http://localhost:8080/api/v1/{usuario}


- Post Video: Un usuario podrá subir un video de una via, mediante un botón.
    - POST http://localhost:8080/api/v1/{idBoulder}/{idRoute}

## Vista Trabajador

- Get Vías: Para ver la lista de vías del rocódromo donde trabaja.
  - Ver Get Vias Vista Cliente.


- Post Via: Crear un nueva via del rocódromo donde trabaja.

    - POST http://localhost:8080/api/v1/boulder/via/enrollment

    - JSON Request
      {
      "idBoulder": "1",
      "qrRoute": "Treparriscos/1",
      "name": "Via 1",
      "type": "BOULDER",
      "num_nivel": 1,
      "presa": "Azul"
      }


- Put Via: Editar una via del rocodromo donde trabaja.


- Delete Via: Deshabilitar una via del rocodromo donde trabaja.


- Delete Video: Borrar un video de una via del rocodromo donde trabaja.

## Admin

- Post Rocodromo: Crear rocódromo.

    - POST http://localhost:8080/api/v1/boulder/enrollment

    - JSON Request
      {
      "name": "Rompemuros",
      "address": "C/Malasaña, 42",
      "locality": "Santander",
      "mail": "rompe@gmail.com",
      "phone": "942 111 111"
      }

    - JSON Response
      {
      "idBoulder": 2,
      "name": "Rompemuros",
      "address": "C/Malasaña, 42",
      "locality": "Santander",
      "mail": "rompe@gmail.com",
      "phone": "942 111 111",
      "phone2": null,
      "rutas": null
      }
