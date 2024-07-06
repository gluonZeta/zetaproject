package gluon.projects.utilities;

import gluon.projects.myexception.PropertieFileException;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesGetter {

    @Generated("jacoco exclude")
    private PropertiesGetter() {
        throw new IllegalStateException("Utility class");
    }

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try(InputStream inputStream = PropertiesGetter.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PropertieFileException(e);
        }
        return properties;
    }

}
