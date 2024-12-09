import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SetUp {
    Properties property;
  @BeforeTest
    public void initConfig() throws IOException {
        property = new Properties();
        FileInputStream fi = new FileInputStream("./src/test/resources/config.properties");
        property.load(fi);
        System.out.println(property.getProperty("baseUrl"));
    }
}
