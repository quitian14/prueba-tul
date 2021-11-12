# README #


### Que se implemento? ###

* cache con redis a la busqueda de un producto con el eviction cuando se edita
* Los siguientes Endpoints
    * crear usuario (/api/test-ms/users)
    * login (/api/test-ms/users/login)
    * Lista de todos los productos
    * buscar un producto
    * Agregar, eliminar y modificar productos
    * Lista de todos los productos de un carrito
    * Agregar, eliminar y modificar productos al carrito
    * Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a completado.
* Autorización basada en roles en los controladores usando una anotacion (@Secured) creada para tal fin
* se generó token con JWT y las operaciones que necesitan identificar al usuario dicha identificacion se saca del Bearer
* para usar las operaciones primero se debe loguear y usar el token en el header de Authorization
* Pruebas unitarias de la gestion de usuarios.
* Swagger: http://localhost:8090/api/test-ms/swagger-ui/index.html?configUrl=/api/test-ms/v3/api-docs/swagger-config
* para ejecutarlo ejecute el comando docker-compose up e ingrese al swagger
