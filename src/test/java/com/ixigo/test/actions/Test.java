package com.ixigo.test.actions;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {
	
	public static void main111(String[] args) throws IOException {
		Test t = new Test();
		String response = IOUtils.toString(t.getClass().getResourceAsStream("/Response.txt"),"UTF-8");
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(response);
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
		System.out.println("==="+ sortedMap.size());
		int i= 0;
		for(Entry<Integer, JsonObject> entry : sortedMap.entrySet()) {
			if(i++==20) {
				break;
			}
			JsonObject terms = entry.getValue();
			System.out.println(i+"> price = " + entry.getKey()+" flights : "+ terms.keySet().toString());
		}
		//FlightSearchApiResponse obj = new JsonSerializationAndDeserialization<FlightSearchApiResponse>().deserializeJson(response, FlightSearchApiResponse.class);
	}
}
