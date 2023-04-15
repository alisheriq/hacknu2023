import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class PersonData {
    public static String getFullName(String iin, String token) throws Exception {
        String url = "http://hakaton-fl.gov4c.kz/api/persons/" + iin;
        String authHeader = "Bearer " + token;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", authHeader)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JSONObject personData = new JSONObject(response.body());
            String firstName = personData.getString("firstName");
            String lastName = personData.getString("lastName");
            return firstName + " " + lastName;
        } else {
            return null;
        }
    }
}





