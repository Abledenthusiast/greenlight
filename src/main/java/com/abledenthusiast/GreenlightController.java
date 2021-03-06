package greenlight;

import greenlight.dataconnectors.MongoConnectorFactory;
import greenlight.rogan.RoganConnector;
import io.micronaut.context.annotation.Context;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Context
@Controller("/greenlight")
public class GreenlightController {

    private static final Logger log = LoggerFactory.getLogger(GreenlightController.class);

    private MongoConnectorFactory mongoConnectorFactory;
    private RoganConnector roganConnector;

    @Inject
    public GreenlightController(MongoConnectorFactory mongoConnectorFactory, RoganConnector roganConnector) {
        this.mongoConnectorFactory = mongoConnectorFactory;
        this.roganConnector = roganConnector;
        mongoConnectorFactory.newConnection();
    }

    @PostConstruct
    public void startClients() {
        roganConnector.start();
    }

    @Get
    public String status() {
        return "OK";
    }

    @Post(value = "/documents", processes = MediaType.ALL)
    public HttpResponse<String> create(CompletedFileUpload file) {
        Path tempFile;
        try {
            tempFile = Files.createTempFile(file.getFilename(), "temp");
        } catch (IOException e) {
            return HttpResponse.badRequest("Upload Failed");
        }

        return HttpResponse.ok("uploaded");
    }
}
