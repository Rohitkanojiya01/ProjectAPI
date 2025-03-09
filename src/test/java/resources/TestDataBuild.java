package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.DeleteInputPayload;
import pojo.InputPayload;
import pojo.Locate;

public class TestDataBuild {
	
	public InputPayload AddPlacePayLoad(String name, String address, String language) {
		
		InputPayload ap = new InputPayload();

		Locate l = new Locate();
		l.setLat((float) -38.383494);
		l.setLng((float) 33.427362);

		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");

		ap.setTypes(list);
		
		return ap;
	}
	
	public DeleteInputPayload deletePlacePayLoad(String place_id) {
		
		DeleteInputPayload dp = new DeleteInputPayload(); // changes from codebase at noon
		dp.setPlace_id(place_id);
		System.out.println("happy ending with git");
		return dp;
	}
	
}
