package de.swingbe.ahc;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class Main {

    static String URL = "https://dedriver.org";
    static String PORT = "42001";
    static String ROUTE = "/postdata";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        postHttps("uuid", 87.263783, 52.9019052,
                1642512736, "alias", "0");
        System.out.println("Done!");
    }

    static void postHttps(final String uuid, final double latitude, final double longitude,
                          final long timestamp, final String alias, final String vehicle) {
        KeyStore trustStore =
                null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            trustStore.load(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        SSLSocketFactory sf = null;
        try {
            sf = new CustomSSLSocketFactory(trustStore);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        sf.setHostnameVerifier(
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",
                PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", sf, 443));

        ClientConnectionManager ccm =
                new ThreadSafeClientConnManager(params, registry);

        HttpClient client = new DefaultHttpClient(ccm, params);

        /**TODO
         List nvps = new ArrayList();
         nvps.add(new BasicNameValuePair("key1", "val1"));
         nvps.add(new BasicNameValuePair("key2", "val3"));
         */

        String address = URL + ":" + PORT + ROUTE;
        System.out.println("address: " + address);

        //create a HTTP POST request
        //use web service endpoint or web site page as url
        HttpPost post =
                null;
        try {
            post = new HttpPost(new URI(address));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

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

        /**TODO
         try {
         post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
         } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
         }
         */

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
        if (response != null) {
            try {
                sc = new Scanner(response.getEntity().getContent());
            } catch (IOException e) {
                System.out.println("ERROR: IO error detected");
                e.printStackTrace();
            }
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
