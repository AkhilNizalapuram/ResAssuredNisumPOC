package com.nisum.poc.Weather;

import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
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

public class PUTWeather {

    private static Logger log = Logger.getLogger(String.valueOf(PUTWeather.class));

    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void updateWeather() throws IOException {
            File file = new File("src/test/resources/jsonfile/CreateWeather.json");
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject weather = new JSONObject(content);
            weather.put("external_id","HYD_TEST001");
            weather.put("Name","Hyderabad Test Station");
            weather.put("Latitude",17.49);
            weather.put("Longitude",79.97);
            weather.put("Altitude",552);
            Response response = RestAssured.given()
                    .when().queryParam("appid", prop.getProperty("APP_ID"))
                    .contentType(ContentType.JSON)
                    .body(FileUtils.readFileToString(file,"utf-8"))
                    .put("/stations/5e95966ecca8ce0001f1aada");
            org.junit.Assert.assertEquals("Did not get response", 200, response.getStatusCode());
            log.info("Verified status code");

        int statusCode = response.getStatusCode();
        Headers allHeaders = response.headers();
        String statusLine = response.getStatusLine();
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

        assertTrue(bodyAsString.contains("latitude"),"latitude received from Response " + JSONResponseBody.get("latitude").equals("17.49"));
        log.info("latitude received from Response ");

        assertTrue(bodyAsString.contains("longitude"),"longitude received from Response " + JSONResponseBody.get("longitude").equals("79.97"));
        log.info("longitude received from Response ");

        assertTrue(bodyAsString.contains("altitude"),"altitude received from Response " + JSONResponseBody.get("altitude").equals("552"));
        log.info("altitude received from Response ");

    }

}

