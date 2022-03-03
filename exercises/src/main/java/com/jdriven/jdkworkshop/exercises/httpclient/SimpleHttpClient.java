package com.jdriven.jdkworkshop.exercises.httpclient;

import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;


import com.jdriven.jdkworkshop.util.TODO;

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
    public String requestDataFromServerSynchronously(final String url) {
        return TODO.implementMe();
    }

    public CompletableFuture<String> requestDataFromServerAsynchronously(final String url) {
        return TODO.implementMe();
    }
}
