package com.jdriven.jdkworkshop.exercises.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

/**
 * Traditionally, when creating HTTP connections, one would have to use the 'old' {@link java.net.HttpURLConnection}.
 * Often, people will just use an external library when doing HTTP calls, such as the Apache HttpClient.
 *
 * The new {@link HttpClient} (started as incubator project in Java 9, and now included in Java 11)
 * can now be used instead of the old HttpURLConnection. It supports HTTP/2, and both synchronous and asynchronous requests.
 */
public class SimpleHttpClient {

    /**
     * Using the new {@link HttpClient}, perform a GET request to the given url,
     * and return the response from the server.
     *
     * @see <a href="https://openjdk.java.net/groups/net/httpclient/intro.html">More information regarding the HttpClient usage</a>
     */
    public String requestDataFromServerSynchronously(final String url) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());

        return response.body();
    }

    public CompletableFuture<String> requestDataFromServerAsynchronously(final String url) throws URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(httpRequest, BodyHandlers.ofString());

        return response.thenApply(HttpResponse::body);
    }
}
