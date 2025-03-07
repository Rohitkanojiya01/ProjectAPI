package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import pojo.InputPayload;
import pojo.Locate;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		req = given().spec(requestSpecification()).body(data.AddPlacePayLoad(name, address, language));
	}

	@When("user calls {string} with {string} https request")
	public void user_calls_add_place_api_with_post_https_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		APIresources api = APIresources.valueOf(resource);
		System.out.println(api.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST") && resource.equalsIgnoreCase("AddPlaceAPI")) {
			response = req.when().post(api.getResource());
			place_id = getJsonPath(response,"place_id");
//			System.out.println(deletePlaceId);
		} else if (method.equalsIgnoreCase("GET"))
			response = req.when().get(api.getResource());
		else if(method.equalsIgnoreCase("POST"))
			response = req.when().post(api.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("verify if place_id created maps to {string} using {string}")
	public void verify_if_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		String place_id = getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_add_place_api_with_post_https_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
//		System.out.println(actualName);
		assertEquals(actualName, ExpectedName);
//		System.out.println(deletePlaceId);
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String key, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, key), ExpectedValue);
	}

	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
//		System.out.println(deletePlaceId);
		req = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
//		user_calls_add_place_api_with_post_https_request("deletePlaceAPI", "POST");
//		the_api_call_got_success_with_status_code(200);
//		in_response_is("status", "OK");
	}

}
