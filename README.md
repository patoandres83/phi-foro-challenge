# phi-foro-challenge
Repositorio para un desafío de crear un foro sólo en backend con Java y Spring Boot

Descripción del proyecto

Este proyecto se trata de la implementación de un foro, el cual está construido sobre una API REST Maven que interactúa como backend utilizando MySQL y Java, con las depencencias:

* data jpa
* security
* validation
* web
* flyway
* mysql
* devtools
* lombok
* com.auth0


Descripción de las funcionalidades del proyecto
-----------------------------------------------
La idea principal es crear un foro, el cual sea capaz de generar end points que permitan realizar las operaciones CRUD sobre los tópicos, validando a los usuario y un token generado al momento de realizar el login.


La base de datos del proyecto
-----------------------------

La base de datos del proyecto se llama "foro", esta contine las siguientes tablas

<img width="315" height="278" alt="image" src="https://github.com/user-attachments/assets/1b2826c0-66f6-4b9b-85b1-0c1fbc42c3e1" />

A continuación de describen cada una de las tablas para que funcione el proyecto

Tabla "perfil"

<img width="676" height="228" alt="image" src="https://github.com/user-attachments/assets/31c23f53-bcf3-40ba-944d-b9cb87aec7d1" />

Tabla "respuesta"

<img width="853" height="281" alt="image" src="https://github.com/user-attachments/assets/236d2d67-37f2-49a0-bedd-3554b6aa458b" />

Tabla "topico"

<img width="891" height="335" alt="image" src="https://github.com/user-attachments/assets/1e8cfaea-8222-4847-bf40-2a3b1dc3ca14" />

Tabla "usuario"

<img width="786" height="272" alt="image" src="https://github.com/user-attachments/assets/22ea9139-e932-43dc-8424-4d67b68f6534" />

Tabla "usuario_perfil"

<img width="562" height="196" alt="image" src="https://github.com/user-attachments/assets/22643121-7b83-419c-aad5-b00d439d2688" />

<strong>Nota: </strong>Se utiliza sólamente por extensión del proyecto la tabla usuario, usuario_perfil y topico.

Descripción de los endpoints
----------------------------


AuthController.java******************************************

Realizar login en la API (POST)
------------------------
/api/auth/login

<img width="1343" height="286" alt="image" src="https://github.com/user-attachments/assets/a57db565-65a1-4da0-a7b3-0445ebbe4d56" />

Si un usuario no se encuentra validado o el token está expirado (expira en 1800 seg), la API muestra el siguiente error

<img width="1115" height="211" alt="image" src="https://github.com/user-attachments/assets/098c7b04-934a-4abb-a3ae-5a278eeec5b8" />


TopicoController.java***************************************

Crear un tópico (POST)
---------------
/api/topicos

<img width="1346" height="522" alt="image" src="https://github.com/user-attachments/assets/c1950821-b5de-43fe-a44c-e231444431b2" />

Listar todos los tópicos (GET)
------------------------

<img width="1346" height="644" alt="image" src="https://github.com/user-attachments/assets/6b77c27b-0ced-43f8-9299-31680f0fa612" />

Buscar tópico por id (GET)
--------------------

Buscar un tópico por id, valida si el id es un integer, caso contrario muestra el siguiente error:

<img width="1345" height="304" alt="image" src="https://github.com/user-attachments/assets/70ddb0f5-dedd-4420-a8b2-269b7ad6acad" />

Si la búsqueda es exitosa muestra el siguiente mensaje:

<img width="1338" height="558" alt="image" src="https://github.com/user-attachments/assets/8f99ae18-30f9-46a3-93e7-ceee418f379d" />

Si no encuentra el id buscado:

<img width="1347" height="289" alt="image" src="https://github.com/user-attachments/assets/34271ebe-33fd-428f-b103-55d598d3a789" />

Actualizar un tópico (PUT)
--------------------------

<img width="1341" height="543" alt="image" src="https://github.com/user-attachments/assets/fa8e1674-20cb-4dea-92c3-12fa27d75af0" />

Desactivar un tópico(DEL)
-------------------------
Esta función sólo cambia el estado status del tópico a desactivado

<img width="1194" height="221" alt="image" src="https://github.com/user-attachments/assets/09e2c3a5-3e42-4394-82f4-3ca81922ec63" />

Eliminar un tópico (DEL)
------------------------
Esta función borra el tópico de la base de datos

<img width="1344" height="298" alt="image" src="https://github.com/user-attachments/assets/86c93816-bc4a-4621-86ea-aa810ee40271" />















