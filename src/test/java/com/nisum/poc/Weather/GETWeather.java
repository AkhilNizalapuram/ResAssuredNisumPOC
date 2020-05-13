package com.nisum.poc.Weather;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class GETWeather {

    private static Logger log = Logger.getLogger(String.valueOf(GETWeather.class));

    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getweather() {
        Response response = RestAssured.given()
                .when().queryParam("appid", prop.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .get("/stations/816a");
        Assert.assertEquals("Did not get response", 200, response.getStatusCode());
        log.info("Verified status code");

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.toLowerCase().contains("external_id"), "Response body contains external_id");
        assertTrue(bodyAsString.contains("SF_TEST001"), "Response body contains SF_TEST001");
        log.info("external_id received from Response " + JSONResponseBody.get("external_id"));

        assertTrue(bodyAsString.toLowerCase().contains("name"), "Response body contains name");
        assertTrue(bodyAsString.contains("San Francisco Test Station"), "Response body contains San Francisco Test Station");
        log.info("name received from Response " + JSONResponseBody.get("name"));

        assertTrue(bodyAsString.toLowerCase().contains("longitude"), "Response body contains longitude");
        assertTrue(bodyAsString.contains("-122.43"), "Response body contains -122.43");
        log.info("longitude received from Response " + JSONResponseBody.get("longitude"));

        assertTrue(bodyAsString.toLowerCase().contains("latitude"), "Response body contains latitude");
        assertTrue(bodyAsString.contains("37.76"), "Response body contains 37.76");
        log.info("latitude received from Response " + JSONResponseBody.get("latitude"));

        assertTrue(bodyAsString.toLowerCase().contains("altitude"), "Response body contains altitude");
        assertTrue(bodyAsString.contains("150"), "Response body contains 150");
        log.info("altitude received from Response " + JSONResponseBody.get("altitude"));

    }
}

