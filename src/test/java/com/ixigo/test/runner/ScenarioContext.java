package com.ixigo.test.runner;


import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<String, Object>();
    }

    public void setAttribute(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getAttribute(String key) {
        return scenarioContext.get(key);
    }

    public Boolean isContains(String key) {
        return scenarioContext.containsKey(key);
    }


}
