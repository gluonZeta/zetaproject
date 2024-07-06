package gluon.projects.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesGetter {

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try(InputStream inputStream = PropertiesGetter.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
