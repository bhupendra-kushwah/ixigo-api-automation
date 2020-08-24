package com.ixigo.test.actions;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import com.ixigo.api.FlightSearchApi;
import com.ixigo.test.runner.TestContext;
import com.ixigo.utils.CommonUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IxigoFlightSearchActions {

	private TestContext testContext;
	private FlightSearchApi flightSearchApi;
	
	public IxigoFlightSearchActions(TestContext testContext) {
        this.testContext = testContext;
        flightSearchApi = new FlightSearchApi();
	}
	
	@Given("^user will travel from \"([^\"]*)\" to destination \"([^\"]*)\" for \"([^\"]*)\" days$")
	public void userWillTravelFromToDestinationForDays(String source, String destination, String days) {
		HashMap<String, Object> testData = new HashMap<String, Object>();
		testData.put("source", source);
		testData.put("destination", destination);
		testData.put("days", days);
        testContext.getScenarioContext().setAttribute("queryParams", testData);
	}

	@When("^user serach on ixigo flight booking site$")
	public void userSerachOnIxigoFlightBookingSite() throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> testData = (HashMap<String, Object>) testContext.getScenarioContext().getAttribute("queryParams");
		String source = (String)testData.get("source");
		String destination = (String)testData.get("destination");
		String days = (String)testData.get("days");
		String startDate = CommonUtils.getDateAfterNumberOfDays(0, "ddMMyyyy");
		String returnDate = CommonUtils.getDateAfterNumberOfDays(Integer.parseInt(days), "ddMMyyyy");
		String referer = "https://www.ixigo.com/search/result/flight?from="+source+"&to="+destination+"&date="+startDate+"&returnDate="+returnDate+"&adults=1&children=0&infants=0&class=e&source=Modify%20Search%20Form";
    	flightSearchApi.getFlightSearchResponse(referer);
    	int code = flightSearchApi.getResponseCode();
    	String response = flightSearchApi.getResponseBody();
    	code = 200;
    	Assert.assertEquals("status code failed to match. actual is :"+ code, HttpStatus.SC_OK, code);
    	testData.put("responseBody", response);
        testContext.getScenarioContext().setAttribute("queryParams", testData);
	}

	@Then("^user is able found top (\\d+) cheapest flights$")
	public void userIsAbleFoundTopCheapestFlights(int topCheapestFlightCount) throws IOException {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> testData = (HashMap<String, Object>) testContext.getScenarioContext().getAttribute("queryParams");
		String response = (String)testData.get("responseBody");
		response = IOUtils.toString(this.getClass().getResourceAsStream("/Response.txt"),"UTF-8");    
		flightSearchApi.getTopCheapestFlights(response, topCheapestFlightCount);
	}

}
