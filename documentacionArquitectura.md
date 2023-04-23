# Documentation


## Package “configuration”
* The implementation of ConfigurationCache is a Spring component that uses the @EnableScheduling annotation to schedule tasks in the application. This class manages a cache of obtained results. The clearCache method runs every 3 minutes to delete old results and ensure that cached results do not take up unnecessary space in memory.


## Package “controllers”
* The implementation of ControllerQuery is a REST controller that handles GET requests for a cryptocurrency API. The query() method handles the GET request and returns a JSONObject object containing the query results. The results are obtained through two injected services: serviceCoinmarketCap and serviceDataBase. The method also saves information about the request made in the database using the service serviceDataBase. If any error occurs, the results object is updated with an error message.


## Package “entity”
* The implementation of EntityInfo represents information stored in a database table. The class has three fields (hora, endpoint, and status) and getter and setter methods for each field. It is annotated with @Entity and @Table to indicate that it will be used as a database entity and specify the name of the table, respectively.


## Package “repository”
* The implementation of RepositoryInfo is an interface called RepositoryInfo that extends JpaRepository, indicating that it is a Spring Data JPA repository that provides methods to access data from the EntityInfo entity.


## Package “services”
* The implementation of GenerateJSON  which provides methods to accommodate JSON data for different types of cryptocurrency-related results. 
   * The method accommodateJSONAllResults takes in a JSONArray called dataArray and loops through it to filter and create a new JSONObject for each element in the array. 
   * The filtered JSONObject includes certain key-value pairs such as the symbol, circulating supply, last updated date, total supply, rank, price and percentage change in 24 hours. 
   * All these key-value pairs are then added to the final JSONObject called jsonObject, with the name of each coin as the key. The method accommodateJSONIdCriptoResults takes in a JSONObject called data and creates a new JSONObject called result. 
   * It then adds certain key-value pairs such as the symbol, description, contracts, tags and social data to the new JSONObject and returns it. Finally, the method accommodateJSONValorCriptoResults takes in a JSONObject called coin and creates a new filtered JSONObject. 
   * This new JSONObject includes key-value pairs such as eth_dominance, active_cryptocurrencies, stablecoin_market_cap, derivatives_volume_24h, last_updated, defi_volume_24h, active_exchanges, and so on. All these key-value pairs are then returned in the filteredCoin JSONObject.

* The implementation of ServiceCoinmarketCap,  It has several methods that interact with a web API for retrieving information about cryptocurrencies.
   * The class uses the Spring framework, which provides features such as dependency injection and caching.
   * The "getAllResults" method sends a request to the API to retrieve the latest cryptocurrency listings. It then processes the data and returns a JSON object with the results.
   * The "getIdCriptoResults" method sends a request to the API to retrieve information about a specific cryptocurrency, identified by its ID. It processes the data and returns a JSON object with the results.
   * The "getValorCriptoResults" method sends a request to the API to retrieve the latest cryptocurrency quotes, converted to a specified currency. It processes the data and returns a JSON object with the results.
   * The "getPeticion" method is a helper method that sends a REST API request and returns the response as a JSON object.



* The implementation of ServiceDataBase,  It is marked with the "@Service" annotation to indicate that it should be managed by the Spring container.
   * The class has one method called "guardarMiEntidad" that takes two string parameters: "Endpoint" and "Status".
   * Inside the method, an object of "EntityInfo" class is created, and its "Endpoint", "Hora", and "Status" properties are set. The "Hora" property is set to the current time using the "LocalTime.now()" method.
   * The "RepositoryInfo" class is injected into this class using the "@Autowired" annotation, which allows Spring to automatically wire in the appropriate dependency. The "save" method of the repository is called with the "entityInfo" object as a parameter, which saves the object to a database.
   * Therefore, this code saves an instance of the "EntityInfo" class to a database using the "RepositoryInfo" class.

