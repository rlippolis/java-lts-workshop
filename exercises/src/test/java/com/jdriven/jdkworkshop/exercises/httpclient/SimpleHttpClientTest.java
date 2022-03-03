package com.jdriven.jdkworkshop.exercises.httpclient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;


import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@WireMockTest(httpPort = SimpleHttpClientTest.SERVER_PORT)
public class SimpleHttpClientTest {

    // The port on which the mock HTTP server in this test will run, change this if port 8080 is already in use on your local machine
    static final int SERVER_PORT = 8080;
    private static final String SERVER_ADDRESS = "http://localhost:" + SERVER_PORT;

    private final SimpleHttpClient simpleHttpClient = new SimpleHttpClient();

    @Test
    public void testRequestDataFromServerSynchronously() throws URISyntaxException, IOException, InterruptedException {
        String url = "/test-sync";
        String expectedResponse = "Sync Server Response";

        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(expectedResponse)));

        String response = simpleHttpClient.requestDataFromServerSynchronously(SERVER_ADDRESS + url);

        verify(getRequestedFor(urlEqualTo(url)));
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testRequestDataFromServerAsynchronously() throws URISyntaxException, InterruptedException, ExecutionException, TimeoutException {
        String url = "/test-async";
        String expectedResponse = "Async Server Response";

        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(expectedResponse)));

        CompletableFuture<String> futureResponse = simpleHttpClient.requestDataFromServerAsynchronously(SERVER_ADDRESS + url);

        String response = futureResponse.get(10, TimeUnit.SECONDS);
        verify(getRequestedFor(urlEqualTo(url)));
        assertThat(response).isEqualTo(expectedResponse);
    }
}
