package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqSpecRequest;  //since we are going to use this object for 2nd and 3rd iteration
	                                                    //we are making this as static
	public static String getGlobalValue(String key) throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\frien\\workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
	    prop.load(fis);
	    String value=prop.getProperty(key);
	    System.out.println(value);
	   return value ;
	}
	
	
	  public  RequestSpecification requestSpecification() throws IOException{
		  if(reqSpecRequest==null){
			  PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			  reqSpecRequest =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					   .addQueryParam("key", "qaclick123")
					   .addFilter(RequestLoggingFilter.logRequestTo(log))
					   .addFilter(ResponseLoggingFilter.logResponseTo(log))
					   .setContentType(ContentType.JSON)
					   .build();
		      return reqSpecRequest;
		  }else{
			  //this will execute for 2nd / 3rd set of data - no need else
			  return reqSpecRequest;
		  }
	  }
	  
	  public String getJsonPath(Response response , String key){
		  String resp=response.asString();
		  System.out.println(resp);
         JsonPath js = new JsonPath(resp); 
         return js.get(key).toString();
	  }
	  
	  
	  
	  
	  
	  
}
