package greenlight.rogan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.runtime.Micronaut;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Singleton
public class MessageResponseMapper {

    private static Map<String, String> messageMap;
    private ObjectMapper objectMapper;

    @Inject
    public MessageResponseMapper(ObjectMapper objectMapper) throws IOException, URISyntaxException {
        System.out.println("loading resource");
        this.objectMapper = objectMapper;
        setup();
    }

    public String getResponse(String message) {
        String response = messageMap.getOrDefault(message, "");
        return response;
    }

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = MessageResponseMapper.class.getClassLoader().getResource(filename);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

    private void setup() throws IOException, URISyntaxException {
        Logger log = Logger.getAnonymousLogger();
        log.warning("loading resources");
        TypeReference<HashMap<String,String>> typeRef = new TypeReference<>(){};
        String json = new String(getClass().getClassLoader().getResourceAsStream("RoganDefaultCommands.json").readAllBytes());
        messageMap = objectMapper.readValue(json, typeRef);
    }


}
