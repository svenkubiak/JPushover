package de.svenkubiak.jpushover.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.OptionalLong;

/**
 * 
 * @author svenkubiak
 *
 */
public class PushoverRequest {
    
    public PushoverResponse push(String url, NavigableMap<String, String> body, String proxyHost, int proxyPort) throws IOException, InterruptedException {
        Objects.requireNonNull(url, "API URL can not be null");
        Objects.requireNonNull(body, "body can not be null");
        
        var httpResponse = getResponse(toJson(body), url, proxyHost, proxyPort);
        var jPushoverResponse = new PushoverResponse().isSuccessful(false);
        
        jPushoverResponse
            .httpStatus(httpResponse.statusCode())
            .response(httpResponse.body())
            .isSuccessful((httpResponse.statusCode() == 200) ? true : false)
            .limit(getHeaderValue(httpResponse, "X-Limit-App-Limit").orElse(0))
            .remaining(getHeaderValue(httpResponse, "X-Limit-App-Remaining").orElse(0))
            .reset(getHeaderValue(httpResponse, "X-Limit-App-Reset").orElse(0));

        return jPushoverResponse;
    }

    private HttpResponse<String> getResponse(String body, String url, String proxyHost, int proxyPort) throws IOException, InterruptedException {
        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        var httpClientBuilder = HttpClient.newBuilder();

        if (proxyHost != null && proxyPort > 0) {
            httpClientBuilder.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)));
        }

        return httpClientBuilder.build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
    
    private String toJson(NavigableMap<String, String> body) {
        var buffer = new StringBuilder();
        buffer.append("{");
        for (Map.Entry<String, String> entry : body.entrySet()) {
            buffer.append("\"").append(entry.getKey()).append("\"");
            buffer.append(":");
            buffer.append("\"").append(entry.getValue()).append("\"");
            buffer.append(",");
        }
        buffer.append("}");

        return buffer.toString().replace(",}", "}");
    }

    private OptionalLong getHeaderValue(HttpResponse<String> httpResponse, String header) {
        return httpResponse.headers().firstValueAsLong(header);
    }
}