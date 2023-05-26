# DroneService
This is maven project hence build using maven.

H2 inbuild test DB has been configured.
Can connect to H2 DB through http://localhost:8080/h2-console after starting the project.
	*Driver Class : org.h2.Driver
	*JDBC URL : jdbc:h2:mem:drone
	*Username : musala
	*Password : musala
	
Swagger Has been configured and API documentation can be viewed from http://localhost:8080/swagger-ui/index.html.





Assumptions

* Drone weight is the maximum limit to add medications.
* If sum of medication exceed the drones weight an error will be given.
* Maximum drone can be added upto 10 and that value can be change in application.properties.
* If the drone battery is lesser than 25 its not possible to load the medications to the drone.
* Initially drones will be created in the IDLE state if state is not provided while creating the drone.
* When loading state will be changed to loading and if drone weight is equal to its loaded medication, state will be changed as loaded. 
* While checking the availability for drone, drone with IDLE & LOADING state will be considered as available drones





