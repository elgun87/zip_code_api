package com.zipcode.base;

import io.cucumber.java.BeforeStep;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

public class Base {
    static {
        RestAssured.baseURI = "https://www.zippopotam.us/";
        RestAssured.basePath = "us/";
    }
}
