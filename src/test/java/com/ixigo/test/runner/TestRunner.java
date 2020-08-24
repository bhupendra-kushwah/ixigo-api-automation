package com.ixigo.test.runner;

import org.junit.runner.RunWith;
import cucumber.api.SnippetType;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(   glue = { "com/ixigo/test/actions"},
                    snippets = SnippetType.CAMELCASE,
                    features = "features/",
                    strict = false,
                    plugin = {
                    			"pretty:STDOUT", "html:target/cucumber",
                                "junit:target/report.xml",
                                "com.cucumber.listener.ExtentCucumberFormatter:target/extent-report/report.html"
                             }
                )
public class TestRunner {
	
    }