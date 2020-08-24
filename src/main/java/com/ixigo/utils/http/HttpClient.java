package com.ixigo.utils.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.ixigo.utils.CommonUtils;
import com.ixigo.utils.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;

public class HttpClient {

    private static int                  timeout     = 300;
    private static OkHttpClient.Builder httpClient  = new OkHttpClient.Builder()
                                                            .connectTimeout(timeout, TimeUnit.SECONDS)
                                                            .readTimeout(timeout, TimeUnit.SECONDS)
                                                            .writeTimeout(timeout, TimeUnit.SECONDS);

    private OkHttpClient                client      = httpClient.build();
    public static final MediaType       JSON        = MediaType.parse("application/json");

    private static boolean              networkLogs = true;

    public Response GET(String url, Map<String, String> headers) {

        Logger.info(networkLogs, "Url - " + url + "and Headers - " + CommonUtils.toPrettyJson(headers));
        Builder builder = new Request.Builder().url(url);
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response POST(String url, Map<String, String> headers, String payload) {
        Logger.info(networkLogs, "Url - " + url + " and Headers - " + CommonUtils.toPrettyJson(headers) + " and payload - " + payload);

        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
		RequestBody body = RequestBody.create(contentType, payload.getBytes());

        Builder builder = new Request.Builder();
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.url(url).post(body).build();
        Logger.info(networkLogs, "RequestObject - " + CommonUtils.toPrettyJson(request));

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response DELETE(String url, Map<String, String> headers, String payload) {
        Logger.info(networkLogs, "Url - " + url + " and Headers - " + CommonUtils.toPrettyJson(headers) + " and payload - " + payload);

        RequestBody body = RequestBody.create(JSON, payload.getBytes());

        Builder builder = new Request.Builder();
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.url(url).delete(body).build();
        Logger.info(networkLogs, "RequestObject - " + CommonUtils.toPrettyJson(request));

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
