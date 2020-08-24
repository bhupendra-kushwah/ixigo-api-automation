package com.ixigo.api.pojo;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flights {
	 Map<String, CombinedFlights> combineFlights;

	public Map<String, CombinedFlights> getCombineFlights() {
		return combineFlights;
	}

	public void setCombineFlights(Map<String, CombinedFlights> combineFlights) {
		this.combineFlights = combineFlights;
	}
}
