package com.nisum.poc.Employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class DeleteEmployee {

    private static java.util.logging.Logger log = Logger.getLogger(String.valueOf(DeleteEmployee.class));

    public static void deleteEmployee(int empid) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("/delete/" + empid);
        Assert.assertEquals("Did not get response", 200, response.getStatusCode());
        log.info("Verified status code");

        Headers allHeaders = response.headers();
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();


        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

        String contentType = response.header("Content-Type");
        assertEquals(contentType /* actual value */, "application/json;charset=utf-8" /* expected value */);
        log.info("Verified Content-Type in Header");

        String serverType = response.header("Server");
        assertEquals(serverType /* actual value */, "nginx/1.16.0" /* expected value */);
        log.info("Verified serverType in Header");

        JSONObject JSONResponseBody = new JSONObject(response.asString());
        log.info("status verified from Response ");

        log.info("Deleted Employee record of Id: " + empid);

    }

}
