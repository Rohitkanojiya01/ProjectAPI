package resources;

public enum APIresources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIresources (String resource){    //It is a Constructor that creates object by passing parameter 
		this.resource=resource;        // resource = AddPlaceAPI
	}
	
	public String getResource() {
		return resource;
	}
		

}
