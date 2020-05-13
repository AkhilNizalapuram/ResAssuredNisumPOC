package com.nisum.poc.Weather;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class POSTWeather {

    private static Logger log = Logger.getLogger(String.valueOf(POSTWeather.class));
    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createWeather() throws IOException {
        File file = new File("src/test/resources/jsonfile/CreateWeather.json");
        Response response = RestAssured.given()
                .when().queryParam("appid", prop.getProperty("APP_ID"))
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file,"utf-8"))
                .post("/stations");
        org.junit.Assert.assertEquals("Did not get response", 201, response.getStatusCode());
        log.info("Verified status code");

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();

        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(bodyAsString.contains("external_id"),"external_id received from Response ");
        assertTrue(JSONResponseBody.get("external_id").equals("HYD_TEST001"));
        log.info("external_id received from Response ");

        String name = (String) JSONResponseBody.get("name");
        System.out.println("name received from Response " + name);
        Assert.assertEquals(name, "Hyderabad Test Station", "Correct name received in the Response");
        log.info("name received from Response ");

        assertTrue(bodyAsString.contains("latitude"),"latitude received from Response " + JSONResponseBody.get("latitude").equals("17.38"));
        log.info("latitude received from Response ");

        assertTrue(bodyAsString.contains("longitude"),"longitude received from Response " + JSONResponseBody.get("longitude").equals("78.47"));
        log.info("longitude received from Response ");

        assertTrue(bodyAsString.contains("altitude"),"altitude received from Response " + JSONResponseBody.get("altitude").equals("542"));
        log.info("altitude received from Response ");

    }
}




