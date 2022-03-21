package sanat.bhardwaj;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ibmprojecttest1 {

		@Test(enabled = true)
		public void createUser(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			JSONObject obj = new JSONObject();
			obj.put("username", "sanu");
			obj.put("firstName", "sanu");
			obj.put("lastName", "jha");
			obj.put("email", "jha@123");
			obj.put("password", "jha123");
			obj.put("phone", "9856723423");
			
			String u_name="kakashi";
			
//			System.out.println(obj.toJSONString());
			
			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/user").
		then()
			.statusCode(200)
			.log()
			.all();
			
			val.setAttribute("username", u_name);
		}
		
		@Test(enabled = true, dependsOnMethods="createUser")
		public void login()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			given().
			when()
				.queryParam("username","sanu")
				.queryParam("password","sanu1234")
				.get("/user/login").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@Test(enabled = true, dependsOnMethods= "login")
		public void edit(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			JSONObject obj = new JSONObject();
			obj.put("username", "shinchan");
			obj.put("firstName", "shinchan");
			obj.put("lastName", "nohara");
			obj.put("email", "shinchan@123.com");
			obj.put("password", "shinchan1234");
			obj.put("phone", "9999922222");
			
			String u_name="shinchan";
			
//			System.out.println(obj.toJSONString());
			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.put("/user/"+val.getAttribute("username")).
		then()
			.statusCode(200)
			.log()
			.all();
//			System.out.println(val.getAttribute("username"));
			val.setAttribute("username", u_name);
			
		}
		
		@Test(enabled = true, dependsOnMethods= "edit")
		public void logout()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			given().
			when()
				.get("/user/logout").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@Test(enabled = true, dependsOnMethods="logout")
		public void delete(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			
//			System.out.println(obj.toJSONString());
//			System.out.println(val.getAttribute("username"));

			
			given().
			when()
			.delete("/user/"+val.getAttribute("username").toString()).
		then()
			.statusCode(200)
			.log()
			.all();
			

		}
		
}
