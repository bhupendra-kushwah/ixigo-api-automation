package com.ixigo.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightSearchApiResponse {

	@JsonProperty("results")
	private Results results;

	public Results getResult() {
		return results;
	}

	public void setResult(Results result) {
		this.results = result;
	} 
}
