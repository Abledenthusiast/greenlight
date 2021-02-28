package greenlight.rogan;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.scheduling.annotation.Scheduled;

import javax.inject.Singleton;

@Context
public class RoganConnector {

    private DiscordClientFactory clientFactory;
    private MessageResponseMapper messageResponseMapper;

    public RoganConnector(DiscordClientFactory clientFactory, MessageResponseMapper messageResponseMapper) {
        this.clientFactory = clientFactory;
        this.messageResponseMapper = messageResponseMapper;
    }

    public void start() {
        final GatewayDiscordClient gateway = connect();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            String mappedResponse = messageResponseMapper.getResponse(message.getContent());
            if (!stringNullOrEmpty(mappedResponse)) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage(mappedResponse).block();
            }
        });

        gateway.onDisconnect().block();
    }

    private GatewayDiscordClient connect() {
        DiscordClient client = clientFactory.newClient();
        return client.login().block();
    }

    @Scheduled(cron = "0 30 16 ? * SUN")
    void everyMondayAtTenFifteenAm() {
        final GatewayDiscordClient gateway = connect();
        System.out.println("Executing everyMondayAtTenFifteenAm()");
    }

    private boolean stringNullOrEmpty(String string) {
        return string == null || string.isBlank() || string.isEmpty();
    }
}
