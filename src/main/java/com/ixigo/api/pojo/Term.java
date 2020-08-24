package com.ixigo.api.pojo;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Term {
	private Map<String, Object> term;

	public Map<String, Object> getTerm() {
		return term;
	}

	public void setTerm(Map<String, Object> term) {
		this.term = term;
	}
}
