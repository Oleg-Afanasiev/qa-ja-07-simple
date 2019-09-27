package com.acedemy.demo;

import com.google.common.reflect.TypeToken;
import gherkin.deps.com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDemo {
    public static void main(String[] args) {
        // Subscriber to json
        System.out.println("Subscriber to json");
        Subscriber subscriber1 = new Subscriber(1L, "Ivan", "Ivanov", "m");
        Subscriber subscriber2 = new Subscriber(2L, "Petr", "Petrov", "m");

        Gson gson = new Gson();
        String jsonString = gson.toJson(subscriber1);
        System.out.println(jsonString);

        // json to Subscriber
        System.out.println("json to Subscriber");
        Subscriber subscriber1FromJson = gson.fromJson(jsonString, Subscriber.class);
        System.out.println(subscriber1FromJson);

        // list to json
        System.out.println("list to json");
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);

        String jsonListString = gson.toJson(subscribers);
        System.out.println(jsonListString);

        // json to list
        System.out.println("json to list");
        Type listType = new TypeToken<List<Subscriber>>() {}.getType();
        List<Subscriber> subscribersFromJson = gson.fromJson(jsonListString, listType);

        System.out.println(subscribersFromJson);
    }
}
