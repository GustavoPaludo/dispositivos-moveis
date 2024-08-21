package com.udesc.projetofinal.servicews;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = "HttpHandler";
    private static final int CONNECTION_TIMEOUT = 3000;

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECTION_TIMEOUT);

            int responseCode = conn.getResponseCode();
            Log.d(TAG, "GET Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
                response = sb.toString();
                Log.d(TAG, "Response: " + response);
            } else {
                Log.e(TAG, "GET request failed. Response Code: " + responseCode);
            }
        } catch (SocketTimeoutException e) {
            Log.e(TAG, "Connection timed out", e);
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage(), e);
        }
        return response;
    }

    public String makePostRequest(String reqUrl, Object postData) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);

            Gson gson = new Gson();
            String jsonInputString = gson.toJson(postData);

            Log.d(TAG, "POST Data: " + jsonInputString);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            Log.d(TAG, "POST Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line.trim());
                }
                in.close();
                response = sb.toString();
                Log.d(TAG, "Response: " + response);
            } else {
                Log.e(TAG, "POST request failed. Response Code: " + responseCode);
            }
        } catch (SocketTimeoutException e) {
            Log.e(TAG, "Connection timed out", e);
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage(), e);
        }
        return response;
    }
}
