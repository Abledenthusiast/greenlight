import greenlight.dataconnectors.MongoConnectorFactory;
import greenlight.dto.CreateRequest;
import greenlight.GreenlightController;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


class GreenlightControllerTest {


    private GreenlightController controller;
    private byte[] testFile;

    @BeforeClass
    public void setup() {
        controller = new GreenlightController(new MongoConnectorFactory(null), null);
        try {
            InputStream path = this.getClass().getClassLoader().getResourceAsStream("testfile.json");
            testFile = path.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setPdfBytes(testFile);
//        String result = controller.create(createRequest);

    }

}