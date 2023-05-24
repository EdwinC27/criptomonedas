# Servicio de criptomonedas
Este servicio proporciona una API RESTful que permite consultar información sobre criptomonedas y tipos de cambio mediante solicitudes que contienen sus respectivos IDs como parámetros. Además, también es posible realizar búsquedas generales para obtener información sobre varias criptomonedas al mismo tiempo. Los resultados de las consultas se devuelven en formato JSON, proporcionando la información necesaria para cada caso.

## Uso de la aplicación
Para obtener la información actual sobre las criptomonedas, este servicio utiliza la API de CoinMarketCap. Con el fin de reducir las solicitudes a la API, se ha implementado un sistema de caché que almacena los datos obtenidos durante un período de 3 minutos. En caso de que la información solicitada ya se encuentre en caché, se devuelve directamente desde allí, evitando así una nueva llamada a la API.

Por otro lado, cada vez que se obtiene información de la API o se devuelve información desde la caché, se registra en una base de datos PostgreSQL la información relevante sobre la solicitud realizada, como el endpoint visitado, la hora de la solicitud y el estado de la respuesta. De esta manera, es posible realizar un seguimiento detallado de las operaciones realizadas en el servicio de criptomonedas y detectar posibles problemas en su funcionamiento.

<img src="https://github.com/EdwinC27/criptomonedas/blob/main/diagramaSecuencia.png">

Para mas informacion de arquitectura acceda a este documento <a href="https://github.com/EdwinC27/criptomonedas/blob/main/documentacionArquitectura.md">documentacionArquitectura.md</a> 


### Para utilizar el servicio, se pueden enviar solicitudes a través de estas URLs:

> http://localhost:8080/api/query?idCripto=id_ciudad : para obtener la informacion de una criptomoneda en particular.

> http://localhost:8080/api/query?convert=tipo-camvio : para obtener la informacion del tipo de cambio.

> http://localhost:8080/api/query :  para obtener la informacion en general de varias criptomoneda.

### Para explorar la documentación de la API, se pueden utilizar las siguientes URLs:

> http://localhost:8080/v3/api-docs: para acceder a la especificación OpenAPI en formato JSON.

> http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: para acceder a la interfaz de usuario Swagger UI.


### Para utilizar la API, primero deves ingresar a la siguiente URL para obtener un token:

> http://localhost:8080/login : esa URL es con el método post además de poner en el Body  una estructura similar a la siguiente :
 ```  
   {
     "userName": usuario,
     "password": contraseña
   }
 ```
 La respuesta que te regrese la deves de mandar en las peticiones anteriores

## Requisitos:
- Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente, por ejemplo desde la página de: https://adoptium.net/temurin/releases/.

- Es imprescindible contar con una base de datos PostgreSQL, en la carpeta **BaseDatos** esta el archivo <a href="https://github.com/EdwinC27/criptomonedas/blob/main/BaseDatos/schemaAPI.sql">schemaAPIl.sql</a> con los comandos para crear la tabla correspondiente. 

- Cambia el usuario y contraseña al igual que el nombre de la base de datos en el archivo <a href="https://github.com/EdwinC27/criptomonedas/blob/main/src/main/resources/application-dev.properties">application-dev.properties</a> que esta en la ruta **src/main/resources**.

- Modificar el archivo llamado <a href="https://github.com/EdwinC27/criptomonedas/blob/main/src/main/resources/keys.properties">keys.properties</a> de la carpeta **src/main/resources** donde tienes que poner los valores solicitados para la autenticacion. 

- Asegúrese de tener esta información correcta y actualizada antes de ejecutar el programa.


## Inicializacion
Para utilizar este proyecto, se pueden seguir una las siguientes instrucciones:

### Descarga el proyecto comprimido en .zip
1. Descarga el proyecto en formato .zip desde la opción "Code" de este repositorio.
2. Una vez descargado, descomprime el archivo y abre la carpeta del proyecto.
3. Modifica el archivo **keys.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia la API que vaya a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias.
4. Implementar la base de datos postgresql corespondiente 
   - Puedes cambiar el nombre de la base de datos.
   - En el directorio **BaseDatos** hay un archivo llamado **schemaAPI.sql** con los comandos para crear la tabla necesaria, (esta tabla se debe de llamar igual)
5. Finalmente, para ejecutar la aplicación, utiliza el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo, podrás comenzar a utilizarla.
     
  
### Clona el repositorio
1. Clona el repositorio de Git en tu PC con el siguiente comando:
    ```  
       git@github.com:EdwinC27/criptomonedas.git 
    ```
2. Una vez descargado, abre la carpeta del proyecto
3. Modifica el archivo **keys.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia la API que vaya a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias.
4. Implementar la base de datos postgresql corespondiente 
   - Puedes cambiar el nombre de la base de datos.
   - En el directorio **BaseDatos** hay un archivo llamado **schemaAPI.sql** con los comandos para crear la tabla necesaria, (esta tabla se debe de llamar igual)
5. Finalmente, para ejecutar la aplicación, utiliza el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo, podrás comenzar a utilizarla.
     

### Docker
1. Descarga el archivo <a href="https://github.com/EdwinC27/criptomonedas/blob/main/dockerDir/docker-compose.yml">docker-compose.yml </a> que se encuentra en la carpeta dockerDir
2. Una vez descargado, abre el archivo
3. Modifica los valores de los siguientes campos según tu entorno:
   ```  
      - spring.datasource.url=jdbc:postgresql://<nombre-de-tu-host>:<puerto-de-PostgreSQL>/<nombre-de-tu-base-de-datos>
      - spring.datasource.username=<tu-usuario-de-PostgreSQL>
      - spring.datasource.password=<tu-contraseña-de-PostgreSQL>
   ```
4. Abre una terminal y ubícate en la ruta donde se encuentra el archivo modificado anteriormente.
5. Ejecuta el siguiente comando en la terminal:
   ```  
      docker-compose up -d
   ```
   Este comando levantará los contenedores de Docker definidos en el archivo docker-compose.yml en segundo plano. Espera unos segundos hasta que los contenedores estén listos para ser utilizados.
       
