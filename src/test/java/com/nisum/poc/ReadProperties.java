package com.nisum.poc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    public static Properties loader_properties() throws IOException {

        File file = new File("src/test/resources/configurations/config.properties");
        InputStream fileInput = null;

        fileInput = new FileInputStream(file);

        Properties prop = new Properties();
        prop.load(fileInput);

        return prop;

    }

    public static Properties assertion_properties() throws IOException {

        File file = new File("src/test/resources/configurations/config.properties");
        InputStream fileInput = null;

        fileInput = new FileInputStream(file);

        Properties prop1 = new Properties();
        prop1.load(fileInput);

        return prop1;
    }
}