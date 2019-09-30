package com.telesens.mobile.rest;

import com.acedemy.demo.Subscriber;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.specification.ProxySpecification.host;

public class SubscriberTests {

    @BeforeClass
    public void setUp() {
        proxy = host("127.0.0.1").withPort(8888);
        baseURI = "http://localhost/rest/json";
        port = 8081;
    }

    @Test
    public void testGet() {
        System.out.println("Subscribers get");

        ResponseBody body = given()
                .get("/subscribers")
                .body();

        String jsonSubscribers = body.asString();
        System.out.println(jsonSubscribers);
    }

    @Test
    @Ignore
    public void testAdd() {
        JSONObject json = new JSONObject();
        json.put("id", 666);
        json.put("firstName", "Santa"); // Cast
        json.put("lastName", "Barbara");
        json.put("age", 25);
        json.put("gender", "m");

        given()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");

    }

    @Test
    @Ignore
    public void testPut() {
        JSONObject json = new JSONObject();
        json.put("id", 666);
        json.put("firstName", "Maria");
        json.put("lastName", "Ivanova");
        json.put("age", 24);
        json.put("gender", "f");

        given()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");

    }

    @Test(dataProvider="subscriberProvider")
    @Ignore
    public void testDelete(Subscriber subscriber) {

        // delete
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId())
                .then().assertThat()
                .statusCode(200);

    }
    @DataProvider
    private Object[] subscriberProvider() {
        return new Object[] {
                new Subscriber(342L, "Агнесса", "Александрова", "f")
//                        .id(342L)
//                        .firstName("Агнесса")
//                        .lastName("Александрова")
//                        .age(35)
//                        .gender(Gender.FEMALE)
//                        .build()
        };
    }
}
