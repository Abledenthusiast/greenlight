package greenlight.dataconnectors;

import java.util.Objects;

public class MongoConnector {

    public MongoConnector(String host, String username, String password, int port) {
        Objects.requireNonNull(host);
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

    }
}
