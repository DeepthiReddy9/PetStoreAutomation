package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker fk;
	User userPayload;
	@BeforeClass
	public void setupData() {
		fk=new Faker();
		userPayload=new User();
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().emailAddress());
		userPayload.setPassword(fk.internet().password(5,10));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
	}
	@Test(priority=1)
	public void testPostUser(){
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2)
	public void getUserName() {
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Test(priority=3)
	public void testUpdateUser(){
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().emailAddress());

		Response response1=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		response1.then().log().all();
		Assert.assertEquals(response1.getStatusCode(), 200);
		
		Response response2=UserEndPoints.readUser(this.userPayload.getUsername());
		response2.then().log().all();
		Assert.assertEquals(response2.getStatusCode(), 200);
		
		
	}
	@Test(priority=4)
	public void deleteUserName() {
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
}
