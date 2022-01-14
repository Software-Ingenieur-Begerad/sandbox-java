package de.swingbe.ahc;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    static String URL = "https://dedriver.org";
    static String PORT = "42001";
    static String ROUTE = "/postdata";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        post("uuid", 87.263783, 52.9019052, 0, "alias", "vehicle");
        System.out.println("Done!");
    }

    static void post(final String uuid, final double latitude, final double longitude,
                     final long timestamp, final String alias, final String vehicle) {

        String address = URL + ":" + PORT + ROUTE;
        System.out.println("address: " + address);

        //create a HTTP POST request
        //use web service endpoint or web site page as url
        HttpPost post = new HttpPost(address);

        //set request headers for request data in JSON format
        Header[] headers = {
                new BasicHeader("Content-type", "application/json"),
        };
        //the request payload is in JSON format

        //set request headers
        post.setHeaders(headers);

        //create payload
        //create request data in JSON format
        //create JSON object
        JSONObject payload = new JSONObject();
        try {
            payload.put("uuid", uuid);
            payload.put("latitude", latitude);
            payload.put("longitude", longitude);
            payload.put("timestamp", timestamp);
            payload.put("alias", alias);
            payload.put("vehicle", vehicle);
        } catch (JSONException e) {
            System.out.println("ERROR: JSON error detected");
            e.printStackTrace();
        }

        //set the payload
        HttpEntity entity;
        entity = new ByteArrayEntity(payload.toString().getBytes(StandardCharsets.UTF_8));
        post.setEntity(entity);

        //create a HTTP client
        HttpClient client = HttpClients.custom().build();

        //send request
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            System.out.println("ERROR: IO error detected");
            e.printStackTrace();
        }

        //read response status
        Scanner sc = null;
        try {
            if (response != null) {
                sc = new Scanner(response.getEntity().getContent());
            }
        } catch (IOException e) {
            System.out.println("ERROR: IO error detected");
            e.printStackTrace();
        }

        //print status line
        if (response != null) {
            System.out.println("status line: " + response.getStatusLine());
        }
        if (sc != null) {
            while (sc.hasNext()) {
                System.out.println("status line: " + sc.nextLine());
            }
        }

        //close interaction
        try {
            if (response != null) {
                response.getEntity().getContent().close();
            }
        } catch (IOException e) {
            System.out.println("ERROR: IO error detected");
            e.printStackTrace();
        }

        //verify response
        int responseCode = 0;
        if (response != null) {
            responseCode = response.getStatusLine().getStatusCode();
            System.out.println("responseCode: " + responseCode);
        }
        String statusPhrase = null;
        if (response != null) {
            statusPhrase = response.getStatusLine().getReasonPhrase();
            System.out.println("statusPhrase: " + statusPhrase);
        }

    }
}
/**
 * TODO tidy up
 * if (post != null) {
 * //todo string or byte entity?
 * HttpEntity entity = null;
 * try {
 * entity = new ByteArrayEntity(postData.toString().getBytes("UTF-8"));
 * } catch (UnsupportedEncodingException e) {
 * Timber.e("doInBackground: HTTP entity unavailable: %s", e);
 * e.printStackTrace();
 * }
 * <p>
 * if (entity != null) {
 * post.setHeader("Content-Type", "application/json");
 * post.setEntity(entity);
 * <p>
 * HttpResponse httpResponse = null;
 * try {
 * httpResponse = httpClient.execute(post);
 * } catch (IOException e) {
 * Timber.e("doInBackground: execute post failed");
 * e.printStackTrace();
 * return "execute post failed";
 * }
 * //TODO Why is it necessary to consume response?
 * HttpEntity entityRsp = httpResponse.getEntity();
 * if (entityRsp != null) {
 * try {
 * entityRsp.consumeContent();
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * } else {
 * Timber.w("doInBackground: HTTP entity unavailable");
 * }
 * } else {
 * Timber.w("doInBackground: http post instance unavailable");
 * }
 */
