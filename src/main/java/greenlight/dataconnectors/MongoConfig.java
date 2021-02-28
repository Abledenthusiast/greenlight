package greenlight.dataconnectors;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;

@ConfigurationProperties("greenlight.db.mongo")
public class MongoConfig {

    private String host;
    private int port;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }


}
