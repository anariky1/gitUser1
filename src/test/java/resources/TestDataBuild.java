package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlaceBuild(String name , String language , String address){
		// we are passing POJO class to create request
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com");
		addPlace.setName(name);
		
		//add types
		List<String>typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");	
		addPlace.setTypes(typesList);
		
		
		//add location
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		return addPlace;
	}
	
	public String deletePlacePayload(String placeid){
		return "{\r\n" + 
				"    \"place_id\":\""+placeid+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
