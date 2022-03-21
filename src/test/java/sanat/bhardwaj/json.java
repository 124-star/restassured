package sanat.bhardwaj;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class json {
	@Test
	public void testcase()
	{
		Response response = RestAssured.delete("http://localhost:3000/posts/2");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.body());
		System.out.println(response.getHeaders());
	}
	@Test
	public void testcase2()
	{
		given().get("http://localhost:3000/posts").then().statusCode(20).log().all();
	}
		
	
}


