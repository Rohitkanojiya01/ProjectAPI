Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
  	Given Add place payload with "<name>" "<address>" "<language>"
  	When user calls "AddPlaceAPI" with "post" https request
  	Then 	the API call got success with status code 200
  	And "status" in response is "OK"
  	And "scope" in response is "APP" 
  	And verify if place_id created maps to "<name>" using "getPlaceAPI"
  	
  	Examples:
  	
  					|name     |address           |language |
  					|Rahul    |lyt hill pune     |hindi    |
  				#	|BBhouse | Spanish  |Sea cross center  |

@DeletePlace @Regression
Scenario: Verify if place is being successfully deleted using DeletePlaceAPI
			Given Delete place payload 
			When user calls "deletePlaceAPI" with "post" https request
  		Then 	the API call got success with status code 200
 		 	And "status" in response is "OK"
  					
  					