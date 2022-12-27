package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[5].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test04() {
        List<String> listOfName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the store is : " + listOfName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test05() {
        List<Integer> listOfStoreId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + listOfStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test06() {
        List<Integer> data = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the size of the data list is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test07() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store where store name = St Cloud : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test08() {
        List<Integer> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store where store name = Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test09() {
        List<String> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store is : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test10() {
        List<List<String>> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices of the store where service name = Windows Store is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test11() {
        List<List<String>> storeId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the storeId of all the store is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test12() {
        List<List<String>> id = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the Id of all the store are : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test13() {
        List<List<String>> names = response.extract().path("data.findAll{it.state=='ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state = ND is : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test14() {
        List<List<String>> services = response.extract().path("data.findAll{it.name=='Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is :  " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15() {
        // List<HashMap<String, ?>> createdAt = response.extract().path("data.services.findAll{it.name=='Windows Store'}.createdAt");
        List<List<String>> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = “Windows Store” is :  " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” is :  " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //    17. Find the zip of all the store
    @Test
    public void test17() {
        List<Integer> zip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store are :  " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //     18. Find the zip of store name = Roseville
    @Test
    public void test18() {
        List<Integer> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville is :  " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //    19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test19() {
        List<String> storeServices = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater :  " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //    20. Find the lat of all the stores
    @Test
    public void test20() {
        List<String> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the lat of all the stores :  " + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
