package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination sd = new StepDefination();
		if (sd.place_id == null) {
			sd.add_place_payload_with("janny", "21 golden road", "Spanish");
			sd.user_calls_add_place_api_with_post_https_request("AddPlaceAPI", "Post");
			sd.verify_if_place_id_created_maps_to_using("janny", "getPlaceAPI");
		}
	}
}
