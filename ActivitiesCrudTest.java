package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ActivitiesCrudTest {

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Test(priority = 1)
    public void createActivity() throws Exception {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";

        Map<String, Object> body = new HashMap<>();
        body.put("id", 0);
        body.put("title", "Learn API Testing");
        body.put("dueDate", "2025-10-25T00:00:00");
        body.put("completed", false);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/Activities");

        response.then().statusCode(200);


        System.out.println("Sent:");
        System.out.println(mapper.writeValueAsString(body));
        System.out.println("Response:");
        System.out.println(mapper.writeValueAsString(response.jsonPath().getMap("")));
    }

    @Test(priority = 2)
    public void readActivity() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";

        int existingActivityId = 1;

        given()
                .when()
                .get("/Activities/" + existingActivityId)
                .then()
                .statusCode(200);

        System.out.println(" Successfully read activity with ID: " + existingActivityId);
    }

    @Test(priority = 3)
    public void updateActivity() throws Exception {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";

        Map<String, Object> updatedBody = new HashMap<>();
        updatedBody.put("id", 0);
        updatedBody.put("title", "Learn REST API with Java");
        updatedBody.put("dueDate", "2025-10-30T00:00:00");
        updatedBody.put("completed", true);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .put("/Activities/0");
        response.then().statusCode(200);

        System.out.println("Updated:");
        System.out.println(mapper.writeValueAsString(updatedBody));
        System.out.println("Response:");
        System.out.println(mapper.writeValueAsString(response.jsonPath().getMap("")));
    }

    @Test(priority = 4)
    public void deleteActivity() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";

        int fakeIdToDelete = 0;

        given()
                .when()
                .delete("/Activities/" + fakeIdToDelete)
                .then()
                .statusCode(200);

        System.out.println("Sent delete request for ID: " + fakeIdToDelete);
    }
}
