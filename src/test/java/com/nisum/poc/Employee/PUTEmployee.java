package com.nisum.poc.Employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class PUTEmployee {
    private static Logger log = Logger.getLogger(String.valueOf(PUTEmployee.class));
    public static void updateEmployee() throws IOException {
        File file = new File("src/test/resources/jsonfile/UpdateEmployee.json");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file,"utf-8"))
                .put("/update/21");

        Assert.assertEquals("Did not get response", 200, response.getStatusCode());

     ResponseBody body = response.getBody();
     String bodyAsString = body.asString();
     JSONObject ResponseBody = new JSONObject(response.asString());

     assertTrue(bodyAsString.contains("status"),"status received from Response ");
     assertTrue(ResponseBody.get("status").equals("success"));
     log.info("status received from Response ");

 }
}