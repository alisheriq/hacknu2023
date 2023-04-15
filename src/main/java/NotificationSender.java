import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class NotificationSender {
    private static String webHookURL = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4840075810561-4827352156723-HTBWMnDeZjCYRksA2MMVdXY5";
    private static String slackChannel = "notification";
    public static void sendMessage(String message)
    {
        try
        {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(message);
            Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();
            WebhookResponse wbResp = Slack.getInstance().send(webHookURL, payload);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
