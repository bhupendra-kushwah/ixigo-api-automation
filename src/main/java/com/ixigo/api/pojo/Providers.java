package com.ixigo.api.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Providers {

	@JsonProperty("comR")
	private ComR comR;

	public ComR getComR() {
		return comR;
	}

	public void setComR(ComR comR) {
		this.comR = comR;
	}
}
