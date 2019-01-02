package SHARED;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {


    public static Properties loadPropertiesFromFile() throws FileNotFoundException,IOException,Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//project_properties.properties"));
        return prop;
    }
}
