package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {
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

    //1. Verify if the total is equal to 1561
    @Test
    public void test001() {
        response.body("total", equalTo(1561));
    }

    //Verify if the stores of limit is equal to 10
    @Test
    public void test002() {
        response.body("limit", equalTo(10));
    }

    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test003() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //Verify the storeId=7 inside storeservices of the third store of second services
    @Test
    public void test005() {
        response.body("data[2].services[2].storeservices.storeId", equalTo(7));
    }

    //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test006() {
        // response.body("data.findAll{it.name == 'Roseville'}",hasItem(hasEntry("createdAt"," storeservices")));
        response.body("data[2].services[2].storeservices", hasKey("createdAt"));

//        Map<String, Object> qParams = new HashMap<>();
//        qParams.put("createdAt", "Roseville");
//        Response response = given()
//                .queryParams(qParams)
//                .when()
//                .get("/stores");
//        response.prettyPrint();
//        response.then().statusCode(200);
    }

    //Verify the state = MN of forth store
    @Test
    public void test007() {
        response.body("data[4].state", equalTo("MN"));
    }

    //Verify the store name = Rochester of 9th store
    @Test
    public void test008(){
        response.body("data[8].name",equalTo("Rochester"));
    }

    //Verify the storeId = 11 for the 6th store
    @Test
    public void test009(){
        response.body("data[5].services[1].storeservices.storeId",equalTo(11));
    }

    //Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test010(){
        response.body("data[2].services[3].storeservices.serviceId",equalTo(4));

    }
}
