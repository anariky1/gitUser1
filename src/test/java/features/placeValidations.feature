Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being added sucessfully using AddPlace API

  Given Add Place Payload with "<name>" and "<language>" and "<address>"
  When user calls "AddPlaceAPI" with "POST" http request
  Then the API call got success with Status code 200
  And "status" in response body is  "OK"
  And "scope" in response body is  "APP"  
  And verify place_id created maps to "<name>" using "getPlaceAPI"
  Examples:
     | name   | language | address |
     | Ananth | English  | World Cross Center |
     |Priya   | Tamil    | USA                |
     |Kirithik | English | USA                |
 
@DeletePlace   
Scenario: Verify if Delete functionality is working

Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with Status code 200

     
   


