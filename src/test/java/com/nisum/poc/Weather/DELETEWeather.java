package com.nisum.poc.Weather;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DELETEWeather {
    private static Logger log = Logger.getLogger(String.valueOf(GETWeather.class));

    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteWeather() {
        Response response = RestAssured.given()
                .when().queryParam("appid", prop.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .delete("/stations");
        Assert.assertEquals("Did not get response", 404, response.getStatusCode());

    }
}