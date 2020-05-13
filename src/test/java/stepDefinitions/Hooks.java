package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
 
	 @Before("@DeletePlace")
	 public void beforeScenario() throws Throwable{
		 //write a code that will generate place id
		 //execute this code only when place id is null
		 
		 stepDefinition sd =new stepDefinition();
		 if(stepDefinition.place_id==null){
			 sd.add_Place_Payload("Ananth", "English", "Asia");
			 sd.user_calls_API_with_http_request("AddPlaceAPI", "POST");
			 sd.verify_placeid_created_maps_to_name_using_something("Ananth", "getPlaceAPI");
		 }
		 
	 }
	 
}
