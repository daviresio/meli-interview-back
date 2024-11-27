package com.meli.interview.back.subscription_api.resource;

import com.meli.interview.back.subscription_api.subscription.resource.dto.CreateSubscriptionDTO;
import com.meli.interview.back.subscription_api.subscription.type.Partner;  // Importando o enum Partner
import com.meli.interview.back.subscription_api.subscription.service.SubscriptionService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SubscriptionService subscriptionService;

    @BeforeEach
    public void setup() {
        // Define a base URI para o RestAssured
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testCreateSubscription() {
        // Criação do objeto SubscriptionDTO com o Partner (enum), price e ID
        CreateSubscriptionDTO createSubscriptionDTO = new CreateSubscriptionDTO();
//        createSubscriptionDTO.setId(UUID.randomUUID());
//        createSubscriptionDTO.setPartner(Partner.DISNEY);  // Definindo um valor do enum Partner
//        createSubscriptionDTO.setPrice(19.99f);

        // Enviando a requisição POST para criar uma nova assinatura
        given()
                .contentType(ContentType.JSON)
                .body(createSubscriptionDTO)
                .when()
                .post("/api/v1/public/subscriptions")
                .then()
                .statusCode(200)
                .body("price", is(19.99f))
                .body("partner", is("DISNEY"));  // Validando que o partner é o enum 'DISNEY'
    }

    @Test
    public void testGetUserSubscriptions() {
        // Assumindo que o UUID do usuário seja "123e4567-e89b-12d3-a456-426614174000"
        String userId = "123e4567-e89b-12d3-a456-426614174000";

        given()
                .header("Authorization", "Bearer some-token")
                .when()
                .get("/api/v1/public/subscriptions?userId=" + userId)
                .then()
                .statusCode(200)
                .body("size()", is(0));  // Ajuste de acordo com os dados simulados ou esperados
    }

    @Test
    public void testGetTotalSubscriptionsCost() {
        String userId = "123e4567-e89b-12d3-a456-426614174000";
        String friendEmail = "friend@example.com";

        given()
                .queryParam("friendEmail", friendEmail)
                .header("Authorization", "Bearer some-token")
                .when()
                .get("/api/v1/public/subscriptions/cost?userId=" + userId)
                .then()
                .statusCode(200)
                .body(is(39.98f));  // Exemplo de custo total esperado
    }
}
