package com.nisum.poc.Employee;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Assert;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;


public class GETEmployee {

    private static Logger log = Logger.getLogger(String.valueOf(GETEmployee.class));

    public static void retriveemployee() {
        Response response = RestAssured.given()
                .when()
                .get("/employees");
        System.out.println("Response :" + response.asString());
        Assert.assertEquals("Did not get response", 200, response.getStatusCode());

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        JSONObject JSONResponseBody = new JSONObject(response.asString());

        assertTrue(JSONResponseBody.getString("status").equals("success") );
        log.info("status verified from Response ");

    }

}
