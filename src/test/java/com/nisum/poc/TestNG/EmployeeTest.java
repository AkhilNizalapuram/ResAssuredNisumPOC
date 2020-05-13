package com.nisum.poc.TestNG;

import com.nisum.poc.Employee.DeleteEmployee;
import com.nisum.poc.Employee.GETEmployee;
import com.nisum.poc.Employee.POSTEmployee;
import com.nisum.poc.Employee.PUTEmployee;
import com.nisum.poc.ReadProperties;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class EmployeeTest {
    private static Logger log = Logger.getLogger(String.valueOf(EmployeeTest.class));
    protected static Properties prop = null;
    static {
        try {
            prop = ReadProperties.loader_properties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeClass(description = "Initializes URI",enabled = true)
    public void setUp(){
        RestAssured.baseURI = prop.getProperty("EmployeeURI");
    }

    @Test(description = "This method is used for validation of retriving weather details ",priority=1,enabled = true,groups= {"Exclude Group"})
    public void validateGetEmployee() {
        GETEmployee.retriveemployee();
    }

    @Parameters({ "Method" })
    @Test(description = "This method is used for validation of creating employee details ",priority=2,enabled = true,groups= {"Include Group"})
    public void validatecreateEmployee(String Method) throws IOException {
        POSTEmployee.createemployee();
        log.info("The name of the method is "+ Method);
    }

    @Test(description = "This method is used for validation of creating employee details ",priority=3,enabled = true,groups= {"Include Group"})
    public void validateupdateEmployee() throws IOException {
        PUTEmployee.updateEmployee();
    }

    @Test (dataProvider="getData",priority=5)
    public void loginTest(String Method, String Group){
        System.out.println("First Method name is "+ Method);
        System.out.println("Group name used is  "+ Group);
    }

    @DataProvider(name="getData")
    public Object[][] getData(){

        Object [][] data = new Object [2][2];

        data [0][0] = "GetEmployee";
        data [0][1] = "Exclude Group";

        data[1][0] = "PostEmployee";
        data[1][1] = "Include Group";

        return data;

    }
    @Test(description = "This method is used for validation of deleting employee details ",priority=4,enabled = true,groups= {"Include Group"})
    public void validateDeleteEmployee() {
        DeleteEmployee.deleteEmployee();

    }

}