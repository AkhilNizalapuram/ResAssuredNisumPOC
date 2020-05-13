package com.nisum.poc.Employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class POSTEmployee {

    private static Logger log = Logger.getLogger(String.valueOf(POSTEmployee.class));

    public static void createemployee() throws IOException {
        File file = new File("src/test/resources/jsonfile/CreateEmployee.json");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(FileUtils.readFileToString(file,"utf-8"))
                .post("/create");

        System.out.println("Response :" + response.asString());

        Assert.assertEquals("Did not get response", 200, response.getStatusCode());

        JSONObject ResponseBody = new JSONObject(response.asString());

        assertTrue(ResponseBody.getString("status").equals("success") );
        log.info("status verified from Response ");

        assertEquals(ResponseBody.getJSONObject("data").getString("name"), "Akhil Nizalapuram");
        log.info("name verified from Response ");

        assertEquals(ResponseBody.getJSONObject("data").getString("salary"), "25000");
        log.info("salary verified from Response ");

        assertEquals(ResponseBody.getJSONObject("data").getString("age"), "25");
        log.info("age verified from Response ");

    }

    }

