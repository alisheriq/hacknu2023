import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SmsSender {

    public static String sendSms(String phoneNumber, String message, String token) throws IOException, InterruptedException {
        String url = "http://hak-sms123.gov4c.kz/api/smsgateway/send";
        String payload = String.format("{\"phone\":\"%s\",\"smsText\":\"%s\"}", phoneNumber, message);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String getToken() throws IOException, InterruptedException {
        String url = "http://hakaton-idp.gov4c.kz/auth/realms/con-web/protocol/openid-connect/token";
        String payload = String.format("username=%s&password=%s&client_id=%s&grant_type=%s",
                URLEncoder.encode("test-operator", StandardCharsets.UTF_8),
                URLEncoder.encode("DjrsmA9RMXRl", StandardCharsets.UTF_8),
                URLEncoder.encode("cw-queue-service", StandardCharsets.UTF_8),
                URLEncoder.encode("password", StandardCharsets.UTF_8));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().split("\"")[3];
    }
}
