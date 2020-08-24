package com.ixigo.api;

import okhttp3.Response;


import java.util.HashMap;
import java.util.Map;

import com.ixigi.enums.EnvParam;
import com.ixigo.utils.http.HttpClient;

public abstract class AbstractBaseApi {

        //Static stuff
        public static HttpClient client	= new HttpClient();

        //Class variables
        protected Response response;
        private Map<String, String> defaultHeaders = new HashMap<String, String>();
        private String requestData;
        private String responseData;
        
        //Default BASE URL and other variables value is fetched from environment variable if passed.
        protected static String BASE_URL	=	EnvParam.IXIGO_BASEURL.getValue();
        protected static String CLIENT_ID	=	EnvParam.IXIGO_Client_ID.getValue();
        
        //Constructors
        public AbstractBaseApi() {
            if(BASE_URL == null || BASE_URL.isEmpty())
            	BASE_URL = "http://localhost:8080";
        	this.setDefaultHeaders();
        }

        /*
         * Add headers which are common across all api's.
         */
        private void setDefaultHeaders() { 
           defaultHeaders.put("x-requested-with", "XMLHttpRequest");
           defaultHeaders.put("clientid", CLIENT_ID);
           defaultHeaders.put("apikey", "ixiwebu00212");
        }

        public String getBaseUrl() {
        	return BASE_URL;
        }
        
        protected  Map<String, String> getHeaders(Map<String, String> headers) {
            
        	Map<String, String> finalHeaders = new HashMap<String, String>();
            if(headers != null)
                finalHeaders.putAll(headers);
            finalHeaders.putAll(this.defaultHeaders);
            return finalHeaders;
        }

        public String getRequestData() {
            return requestData;
        }

        public void setRequestData(String requestData) {
            this.requestData = requestData;
        }

        public String getResponseData() {
            return responseData;
        }

        public void setResponseData(String responseData) {
            this.responseData = responseData;
        }
        
        //Abstract stuff
        
        //Keep a String variable in child api class that holds response code at all times and below method should return that value.
        public abstract int getResponseCode();


        //Keep a String variable in child api class that holds response body at all times and below method should return that value.
        public abstract String getResponseBody();
                
        public Response get(String url, Map<String, String> headers) {
            return client.GET(url, this.getHeaders(headers));
        }

        public Response post(String url, Map<String, String> headers, String payload) {
            return client.POST(url, this.getHeaders(headers), payload);
        }

        public Response delete(String url, Map<String, String> headers, String payload) {
            return client.DELETE(url, this.getHeaders(headers), payload);
        }
    
}

