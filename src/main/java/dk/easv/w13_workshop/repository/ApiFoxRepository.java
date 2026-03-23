package dk.easv.w13_workshop.repository;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * REPOSITORY PATTERN – Concrete repository (HTTP API emulator).
 *
 * Sends commands to the fox emulator at http://10.5.10.10:8080/ using
 * Java's built-in HttpClient.  The API expects two query/body parameters:
 *   command  – e.g. "wr start"
 *   value    – e.g. "1300"
 *
 * Swap this class for TelnetFoxRepository (or any other implementation)
 * without touching a single line outside this package.
 */
public class ApiFoxRepository implements FoxRepository {

    private static final String BASE_URL = "http://10.5.10.10:8080";
    private final HttpClient client;

    public ApiFoxRepository() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Override
    public void sendCommand(int groupNumber, String command, String value) {
        try {
            // Build the POST body: command=wr+start&value=1300&group=1
            String body = "command=" + encode(command)
                    + "&value="   + encode(value)
                    + "&group="   + groupNumber;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/command"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "API returned HTTP " + response.statusCode()
                                + ": " + response.body());
            }

            System.out.printf("[API] Sent  '%s %s'  →  HTTP %d%n",
                    command, value, response.statusCode());

        } catch (Exception e) {
            // Wrap so the CommandManager can report it to the UI
            throw new RuntimeException("Failed to send command: " + e.getMessage(), e);
        }
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}

