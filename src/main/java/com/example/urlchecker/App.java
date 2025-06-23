package com.example.urlchecker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList(
                "https://www.google.com",
                "https://www.github.com",
                "https://www.thisurldoesnotexist1234.com"
        );

        for (String url : urls) {
            checkUrlStatus(url);
        }
    }

    public static void checkUrlStatus(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.connect();

            int code = conn.getResponseCode();
            if (code >= 200 && code < 400) {
                System.out.println(urlString + " is UP ");
            } else {
                System.out.println(urlString + " is DOWN  (HTTP Code: " + code + ")");
            }

        } catch (Exception e) {
            System.out.println(urlString + " is DOWN  (" + e.getMessage() + ")");
        }
    }
}
