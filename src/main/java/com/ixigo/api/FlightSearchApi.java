package com.ixigo.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ixigo.utils.Logger;

import okhttp3.Response;

public class FlightSearchApi extends AbstractBaseApi {

	private String responseBody;
	private int responseCode;
	
	public static Map<String, String> apiHeader = new HashMap<String, String>();
	
	public String getSearchApiUrl() {
		return "/api/flights/search/poll/1q4h30lhdnpntphtupntphdhphphqh8ojqh96ohtpputptpdtspdndvk?searchProviderIds=1026";
	}

	@Override
	public int getResponseCode() {
		return responseCode;
	}
	
	public String getResponseBody() {
		return responseBody;
	}

	public Response getFlightSearchResponse(String referer)throws Exception {
		FlightSearchApi.apiHeader.put("Content-Type", "application/json");
		FlightSearchApi.apiHeader.put("authority", "www.ixigo.com");
		FlightSearchApi.apiHeader.put("ixisrc", "ixiweb");
		FlightSearchApi.apiHeader.put("uuid", "af76be22d4f14a9b96a9");
		FlightSearchApi.apiHeader.put("referer", referer);
		
		response = get(BASE_URL+ getSearchApiUrl(), getHeaders(apiHeader));		
		this.responseBody = response.body().string();
		this.responseCode = response.code();
		return response;
	}
	
	public void getTopCheapestFlights(String jsonResponse, int count) throws JsonParseException, JsonMappingException, IOException {
		//String response = IOUtils.toString(this.getClass().getResourceAsStream("/Response.txt"),"UTF-8");
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(jsonResponse);
		JsonObject groupFares = jsonObject.get("results").getAsJsonObject().get("1026").getAsJsonObject().get("comR").getAsJsonObject().get("groupedFares").getAsJsonObject();
		Set<Entry<String, JsonElement>> objects =  groupFares.entrySet();
		TreeMap<Integer, JsonObject> sortedMap = new TreeMap<Integer, JsonObject>();
		for (Entry<String, JsonElement> entry : objects) {
			Set<Entry<String, JsonElement>> innerObjects = ((JsonObject)entry.getValue()).entrySet();
			for (Entry<String, JsonElement> innerEntry : innerObjects) {
				int totalFare = ((JsonObject)innerEntry.getValue()).get("total").getAsInt();
				sortedMap.put(totalFare, ((JsonObject)innerEntry.getValue()).get("term").getAsJsonObject());
			}
		}
		int i= 0;
		for(Entry<Integer, JsonObject> entry : sortedMap.entrySet()) {
			if(i++==count) {
				break;
			}
			JsonObject terms = entry.getValue();
			Logger.info(i+" -> price = " + entry.getKey()+" flights : "+ terms.keySet().toString());
		}	
	}
}
