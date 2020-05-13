package com.nisum.poc.TestNG;

import com.nisum.poc.ReadProperties;
import com.nisum.poc.Weather.DELETEWeather;
import com.nisum.poc.Weather.GETWeather;
import com.nisum.poc.Weather.POSTWeather;
import com.nisum.poc.Weather.PUTWeather;
import io.restassured.RestAssured;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class WeatherTest {

    private static Logger log = Logger.getLogger(String.valueOf(EmployeeTest.class));
    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(description = "Initializes URI",enabled = true)
    public void setUp(){
        RestAssured.baseURI = prop.getProperty("WeatherURI");
    }

    @Test(description = "This method is used for validation of retriving weather details ",enabled = false,groups= {"Exclude Groups"})
    public void validategetWeather() {
        GETWeather.getweather();
    }

    @Parameters({ "Method" })
    @Test(description = "This method is used for validation of creating weather details ",priority=1,enabled = true,groups= {"Include Groups"})
    public void validatecreateWeather(String Method) throws IOException {
        POSTWeather.createWeather();
        log.info("The name of the method is "+ Method);
    }
    @Test(description = "This method is used for validation of creating weather details ",priority=2,enabled = true,groups= {"Include Groups"})
    public void validateupdateWeather() throws IOException {
        PUTWeather.updateWeather();
    }

    @Test(description = "This method is used for validation of deleting weather details ",priority=3,enabled = true,groups= {"Include Groups"})
    public void validateDeleteWeather() {
        DELETEWeather.deleteWeather();

    }

    @Test (dataProvider="getData",priority=4)
    public void loginTest(String Method, String Group){
        System.out.println("First Method name is "+ Method);
        System.out.println("Group name used is  "+ Group);
    }

    @DataProvider(name="getData")
    public Object[][] getData(){

        Object [][] data = new Object [2][2];

        data [0][0] = "GetWeather";
        data [0][1] = "Exclude Groups";

        data[1][0] = "PostWeather";
        data[1][1] = "Include Groups";

        return data;

    }
    @AfterClass(description = "This method is used for validation of weather test class",enabled = true)
    public void complete(){
        log.info("Validated weather test class successfully");
    }
}
