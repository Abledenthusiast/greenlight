package greenlight.dataconnectors;

import javax.inject.Singleton;

@Singleton
public class MongoConnectorFactory {

    private final MongoConfig mongoConfig;

    public MongoConnectorFactory(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    public MongoConnector newConnection() {
        return new MongoConnector(mongoConfig.getHost(), mongoConfig.getUsername(), mongoConfig.getPassword(), mongoConfig.getPort());
    }
}
