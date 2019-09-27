package com.telesens.mobile.rest;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class SubscriberTests {

    @Test
    public void testGet() {
        System.out.println("Subscribers get");
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;

        ResponseBody body = RestAssured.given()
                .get("/subscribers")
                .body();

        String jsonSubscribers = body.asString();
        System.out.println(jsonSubscribers);
    }

    @Test
    public void testAdd() {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;
        JSONObject json = new JSONObject();
        json.put("id", 666);
        json.put("firstName", "Santa"); // Cast
        json.put("lastName", "Barbara");
        json.put("age", 25);
        json.put("gender", "m");

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");

    }
}
