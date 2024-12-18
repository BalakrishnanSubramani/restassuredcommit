package day1;


//static package import fro given,when,then
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import  static io.restassured.matcher.RestAssuredMatchers.*;

import org.testng.annotations.Test;

/*
 * given() --> prerequesties
 * contnet type,set cookies,add auth,add param, set headers info..etc
 * 
 * when() --> Request
 * get,post,put,delete
 * 
 * then() -- >post validation/response validation
 * validate status code,extract response,extract header cookies, and response body ... 
 * 
 * 
 */

public class HTTPRequests {
	int id ;
	
	@Test (priority =1)
	void getUser() {
		
		when().get("https://reqres.in/api/users?page=2")
		
		.then().statusCode(200).body("page", equalTo(2)).log().all();
	}
	
	@Test (priority =2)
	void createUSer() {
		
		HashMap ipdata = new HashMap();
		ipdata.put("name", "Bala");
		ipdata.put("job", "trainer");
		
		
		 id = given().contentType("application/json").body(ipdata)
		
		.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
	
	}
	
	@Test (priority = 3,dependsOnMethods = {"createUSer"})
	void updateUSer() {
		HashMap updata = new HashMap();
		updata.put("name", "subramani");
		updata.put("job", "trainer");
		
		 given().contentType("application/json").body(updata)
					
					.when().put("https://reqres.in/api/users/"+id)
					
					.then().statusCode(200).log().all();
	
	}
	@Test (priority = 4)
	void deleteUser() {
		 given()
			
			.when().delete("https://reqres.in/api/users/"+id)
			
			.then().statusCode(204).log().all();

	}
}
