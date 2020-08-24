package com.ixigo.api.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComR {
	@JsonProperty("groupedFares")
	private GroupedFares groupedFares;

	public GroupedFares getGroupedFares() {
		return groupedFares;
	}

	public void setGroupedFares(GroupedFares groupedFares) {
		this.groupedFares = groupedFares;
	}
}
