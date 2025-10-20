package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetstoreUser;
import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PetstoreUserTest {

    @Test
    public void createPetstoreUser() throws Exception {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        PetstoreUser user = new PetstoreUser();
        user.setId(123);
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user.setPhone("1234567890");
        user.setUserStatus(1);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .extract().response();

        printAsPrettyJson(user); // نطبع ما أرسلناه كـ JSON
        System.out.println("Response: " + response.asString()); // الرد نفسه إذا احتجت
    }

    private void printAsPrettyJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(obj));
    }
}
