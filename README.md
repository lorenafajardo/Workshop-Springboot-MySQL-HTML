# Workshop-Springboot-MySQL-HTML
El presente proyecto trata sobre la elaboración de un CRUD para un sistema de control de contactos telefónicos. Los archivos del proyecto se encuentran en la carpeta workshop_springboot.

## Pre-requisitos

- La base de datos uilizada en el proyecto fue MySQL y se diseñó en la aplicación MySQL Workbench, ejecutando el script adjuntado en el repositorio con el nombre *contact_book.sql*
   
- Para la conexion con la base de datos se configuró el archivo applications.properties que se encuentra ubicado en la siguiente ruta: https://github.com/lorenafajardo/Workshop-Springboot-MySQL-HTML/blob/main/workshop_springboot/contact/src/main/resources/application.properties asignandole el username: root y password: 12345.

## Despliegue

Para el despliegue de la aplicación el proyecto se ejecuta como *Spring Boot App* . Una vez ejecutado se puede evideciar en [localhost:](http://localhost:8080/) la pagina prrincipal de la aplicacion 

![image](https://user-images.githubusercontent.com/87463011/170396653-34f28c4e-5336-4031-8d7b-9b61a378c707.png)

Al dar click en añadir usuario se redirecciona a la pagina http://localhost:8080/create, donde se encuentra el formulario de regisro
![image](https://user-images.githubusercontent.com/87463011/170396937-ded810b1-d156-4407-8c87-0d05665ea29d.png)

Al guardar un contacto se redirecciona a la pagina principal donde apareceran los contactos almacenados y las opciones editar, eliminacion permanente y elimiación temporal, donde el usuario puede escoger la que desee realizar

![image](https://user-images.githubusercontent.com/87463011/170397308-510f5f85-abf2-4872-9ae4-822f6d2d913b.png)


La parte de backend de la aplicacion se puede obsevar en la ruta: 
https://github.com/lorenafajardo/Workshop-Springboot-MySQL-HTML/tree/main/workshop_springboot/contact/src/main/java/com/contact  
donde se encuentran tres carpetas con el modelo, el controlador y el repositorio.  La parte de frontend se encuentra en la ruta https://github.com/lorenafajardo/Workshop-Springboot-MySQL-HTML/tree/main/workshop_springboot/contact/src/main/resources/templates 
donde se observan las diferentes vistas realizadas con thymeleaf, herramienta que permitió la implementación plantillas en HTML5. 
De esta manera el proyecto se acopla al patron de Modelo, Vista, Controlador -MVC.

#### Autor
Elaborado por: Lorena Fajardo Diaz
