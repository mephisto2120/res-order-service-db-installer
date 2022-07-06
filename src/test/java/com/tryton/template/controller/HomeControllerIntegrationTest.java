package com.tryton.template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HomeControllerIntegrationTest {

    private static String URI;
    @LocalServerPort
    private int port;

    @PostConstruct
    private void init() {
        URI = "http://localhost:" + port;
    }

    @Test
    void shouldReturnMessage() {
        when().get(URI + "/home")
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(1),
                        "nested.name", equalTo("OHH! It works!"));
    }

    @Test
    void shouldCallDB() {
        when().get(URI + "/home/db")
                .then()
                .statusCode(OK.value())
                .body(equalTo("1"));
    }

    @Test
    void shouldCallDBWithXmlMapper() {
        when().get(URI + "/home/db-xml")
                .then()
                .statusCode(OK.value())
                .body(equalTo("123"));
    }

    @Test
    void shouldHandleError() {
        when().get(URI + "/home/error")
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .body(equalTo("Something went wrong. Sorry... It's not you. It's Us."));
    }
}
