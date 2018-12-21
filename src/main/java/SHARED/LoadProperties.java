package SHARED;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {


    public static Properties loadPropertiesFromFile(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir")+"//src//main//java//project_properties.properties"));
        } catch (FileNotFoundException e){
            System.out.println("Soubor project_properties.properties nenalezen !!!"+e);
        }
        catch (IOException e) {
            System.out.println("Chyba při práci se souborem project_properties.properties !!!"+e);
        }
      return prop;
    }
}
