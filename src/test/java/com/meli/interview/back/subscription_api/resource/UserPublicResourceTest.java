package com.meli.interview.back.subscription_api.resource;

import com.meli.interview.back.subscription_api.user.resource.dto.CreateUserDTO;
import com.meli.interview.back.subscription_api.user.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserPublicResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testCreateUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
//        createUserDTO.setName("John Doe");
//        createUserDTO.setUsername("john_doe_01");
//        createUserDTO.setPassword("Password123!");
//        createUserDTO.setEmail("john.doe@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(createUserDTO)
                .when()
                .post("/api/v1/public/users")
                .then()
                .statusCode(200)
                .body("name", is("John Doe"))
                .body("username", is("john_doe_01"))
                .body("email", is("john.doe@example.com"));
    }

    @Test
    public void testCreateUserInvalidEmail() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
//        createUserDTO.setName("Invalid User");
//        createUserDTO.setUsername("invalid_user");
//        createUserDTO.setPassword("Password123!");
//        createUserDTO.setEmail("invalid-email");

        given()
                .contentType(ContentType.JSON)
                .body(createUserDTO)
                .when()
                .post("/api/v1/public/users")
                .then()
                .statusCode(400)  // Esperando erro devido ao email inválido
                .body("message", is("Email must be a valid email address."));
    }
}
