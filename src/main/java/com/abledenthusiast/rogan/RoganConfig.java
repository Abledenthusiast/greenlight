package greenlight.rogan;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("greenlight.db.rogan")
public class RoganConfig {

    private String clientId;
    private String clientSecret;
    private String publicKey;
    private String botToken;


    public String clientId() {
        return clientId;
    }

    public String clientSecret() {
        return clientSecret;
    }

    public String publicKey() {
        return publicKey;
    }

    public String botToken() {
        return botToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }


}
