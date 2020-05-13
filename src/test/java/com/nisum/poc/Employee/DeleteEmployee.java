package com.nisum.poc.Employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.logging.Logger;

public class DeleteEmployee {

    private static java.util.logging.Logger log = Logger.getLogger(String.valueOf(DeleteEmployee.class));

    public static void deleteEmployee() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("/delete/2");
        Assert.assertEquals("Did not get response", 200, response.getStatusCode());
        log.info("deleted employee records");
    }

}
