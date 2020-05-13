package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class stepDefinition extends Utils {
	RequestSpecification res;
	RequestSpecification reqSpecRequest;
	ResponseSpecification ResponseSpecification;
	 Response response;
	  static String place_id;
	 	TestDataBuild data = new TestDataBuild();
	
	  @Given("^Add Place Payload with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void add_Place_Payload(String name , String language , String address) throws IOException {
		//setting through spec builder
				//RestAssured.baseURI="https://rahulshettyacademy.com/";
				
		        
		       AddPlace addPlace= data.addPlaceBuild(name,language,address);
		       reqSpecRequest= requestSpecification();
				res= given()
				   //.log().all() - logging globally
				   .spec(reqSpecRequest)
				   .body(addPlace);
				
				//setting common validations for assert statements
				// ResponseSpecification= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON). build();
			         //this is not required , as we have seperate assertion
		
	}

	  @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_API_with_http_request(String resource,String method) {
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		ResponseSpecification= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON). build();
		   if(method.equals("POST")){
		      response=res.when()
			     .post(resourceAPI.getResource());
		   }else if(method.equals("GET")){
			   response=res.when()
			      .get(resourceAPI.getResource());
		   }else if(method.equals("PUT")){
			   response=res.when()
					      .put(resourceAPI.getResource());
		   }else if(method.equals("DELETE")){
			   response=res.when()
					      .delete(resourceAPI.getResource());
		   }
			    // .then()
			     // .spec(ResponseSpecification).extract().response();
	}

	@Then("the API call got success with Status code {int}")
	public void the_API_call_got_success_with_Status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@And("{string} in response body is  {string}")
	public void in_response_body_is(String key, String	 expectedValue) {
                     
            assertEquals(getJsonPath(response,key),expectedValue);
            
	}
	 @And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	    public void verify_placeid_created_maps_to_name_using_something(String expectedName, String resource) throws Throwable {
	       //prepare request spec
		 place_id=getJsonPath(response,"place_id");
		 res= given()
				   //.log().all() - logging globally
				   .spec(requestSpecification())
				    .queryParam("place_id", place_id);	 
		 user_calls_API_with_http_request(resource,"GET");
		 String  actualName=getJsonPath(response,"name");
		 assertEquals(actualName,expectedName);
		 
	    }
	 
	  @Given("^DeletePlace Payload$")
	    public void deleteplace_payload() throws Throwable {
		  res= given()
				   //.log().all() - logging globally
				   .spec(requestSpecification())
				   .body(data.deletePlacePayload(place_id));
				    
	    }



}
