package com.ixigo.api.pojo;
import java.util.Map;

public class GroupedFares {

	private Map<String, Map<String, CombinedFlights>> flights;

	public Map<String, Map<String, CombinedFlights>> getFlights() {
		return flights;
	}

	public void setFlights(Map<String, Map<String, CombinedFlights>> flights) {
		this.flights = flights;
	}	
}
