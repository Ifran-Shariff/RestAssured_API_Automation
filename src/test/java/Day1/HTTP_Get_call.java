package Day1;
import org.openqa.selenium.devtools.v85.network.model.Request;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class HTTP_Get_call {
    @Test
    void getUser(){
         given()//given will not be used in get calls, because we are not sending any data as a pre-requesite.
                 .when()
                 .get("https://practice.expandtesting.com/notes/api/health-check")
                 .then()
                 .log().all()
                 .statusCode(200)
                 .body("status", equalTo(200))
                 .body("message", equalTo("Notes API is Running"));
    }
}
