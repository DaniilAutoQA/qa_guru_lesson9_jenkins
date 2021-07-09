package test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void setAndReadFromProperties(){
        System.setProperty("our properties", "communizm");
        String value = System.getProperty("our properties", "democracy");
        System.out.println(value);
        //communizm
    }

    @Test
    void readDefaultFromProperties(){
        String value = System.getProperty("our properties", "democracy");
        System.out.println(value);
        //democracy
    }

    @Test
    void readGradleFromProperties(){
        System.getProperty("browser", "chrome");
        System.out.println(System.getProperty("browser"));
        //opera
    }

    @Test
    @Tag("terminal")
    void readTerminalFromProperties(){
        System.setProperty("browser","FF");
        System.out.println(System.getProperty("browser","FF"));
        System.out.println(System.getProperty("version", "91"));
    }

}
