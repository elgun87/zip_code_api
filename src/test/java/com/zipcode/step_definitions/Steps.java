package com.zipcode.step_definitions;

import com.zipcode.base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Steps {
    Base base = new Base();
    RequestSpecification request;
    Response response;

    @Given("Accept type is JSON")
    public void acceptTypeIsJSON() {
        request = given().log().all().accept(ContentType.JSON);
    }

    @Given("path zipcode is {int}")
    public void path_zipcode_is(int zipCode) {
        request.pathParams("post code",zipCode);

    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String string) {
        response = request.get("{post code}");
    }

    @Then("status code must be {int}")
    public void status_code_must_be(Integer statusCode) {
        assertEquals(statusCode,response.statusCode());
    }

    @Then("content type must be {string}")
    public void content_type_must_be(String content_type) {
        assertEquals(content_type,response.contentType());
    }

    @Then("{string} header is {string}")
    public void server_header_is_cloudflare(String header,String value) {
        assertEquals(value,response.header(header));
    }

    @Then("{string} header exists")
    public void report_to_header_exists(String header) {
        assertFalse(header.isEmpty());
    }

    @Then("body should contains following information")
    public void body_should_contains_following_information(Map<String,Object> map) {
        Map map1 = response.as(Map.class);
        assertEquals(map.get("post code"),map1.get("post code"));
        assertEquals(map.get("country"),map1.get("country"));
        assertEquals(map.get("country abbreviation"),map1.get("country abbreviation"));

        Map map2 = response.path("places[0]");
        assertEquals(map.get("place name"),map2.get("place name"));
        assertEquals(map.get("state"),map2.get("state"));
        assertEquals(map.get("latitude"),map2.get("latitude"));

    }
}
