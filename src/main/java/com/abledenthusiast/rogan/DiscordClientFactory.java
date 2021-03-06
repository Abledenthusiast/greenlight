package greenlight.rogan;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;


@Singleton
public class DiscordClientFactory {

    private RoganConfig roganConfig;

    public DiscordClientFactory(RoganConfig roganConfig) {
        this.roganConfig = roganConfig;
    }

    public DiscordClient newClient() {
        final String token = roganConfig.botToken();
        final DiscordClient client = DiscordClient.create(token);
        return client;
    }

    public GatewayDiscordClient newGatewayClient() {
        final String token = roganConfig.botToken();
        System.out.println("Token " + token);
        final DiscordClient client = DiscordClient.create(token);
        return client.login().block();
    }
}
