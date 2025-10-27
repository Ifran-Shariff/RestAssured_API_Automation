package Day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.text.AbstractDocument;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTP_post_call {
    //working on API- https://practice.expandtesting.com/notes/api/api-docs/
    String token, name, phone;
    Response response;

    @BeforeClass
    public void setupBaseurl(){
    RestAssured.baseURI = "https://practice.expandtesting.com/notes/api";
    }

    //@Test(testName = "usercreate")
    public void createUser() {
        System.out.println("------------Starting create user execution-----------");
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus2");
        requestBody.put("email", "mo2@test.com");
        requestBody.put("password", "123456789A2");

                response = given()
                .log().all()
                .contentType(ContentType.JSON)   // important!
                .body(requestBody)  //updating the request body before hitting the call in when method

                .when()
                .post("/users/register") //this will update the post url and hit the call

                .then()
                .log().body()
                //.log().all()
                .statusCode(201)
                .extract().response();

                //storing id from the response body
        String id = response.jsonPath().getString("data.id");
        System.out.println("Generated ID: " + id);
    }

    @DataProvider(name = "loginData")
    public Object[][] getloginData(){
        return new Object[][]{
                {"mo2@test.com", "123456789A2"},    //Valid data validation
                //{"moX2@test.com", "123456789A2X"}       //invalid data validation
        };
    }

    @Test(testName = "loginuser", dataProvider = "loginData", dataProviderClass = HTTP_post_call.class)
    public void userLogin(String email, String password){
        System.out.println("------------Starting login execution-----------");
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
             response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)

                .when()
                .post("/users/login")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

                token = response.jsonPath().getString("data.token");



    }

    @Test(testName = "updateUserDetails", dependsOnMethods = "userLogin")
    public void updateUserDetails(){
        System.out.println("------------Starting update user execution-----------");
        String company = "Expand Testing";
        Map<String, String> updateUserbody = new HashMap<>();
        updateUserbody.put("name", "ifra");
        updateUserbody.put("phone", "99898989");
        updateUserbody.put("company", "infy");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("x-auth-token", token)
                .body(updateUserbody)
                .when()
                .patch("users/profile")
                .then()
                .log().all()
                .statusCode(200);
    }
    //get user details after login
    @Test(testName = "getUserdetails" ,dependsOnMethods = "updateUserDetails" )
    public void getUserdetails(){
        System.out.println("------------Starting get user execution-----------");
           response= given()
                   .log().all()
                .contentType(ContentType.JSON)
                .header("x-auth-token", token)

                .when()
                .get("users/profile")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();

                name = response.jsonPath().getString("data.name");      //we are saving this for update details call
                phone = response.jsonPath().getString("data.phone");
                System.out.println("name: "+name+" phone: "+phone);//we are saving this for update details call
        }





        //delete login - user logout
    @Test(testName="deleteUser", priority = 4)
    public void userLogout(){
        System.out.println("------------Starting logout execution-----------");
        response = given()
                .header("x-auth-token", token)
                .when()
                .delete("users/logout")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("User has been successfully logged out"))
                .extract().response();
    }
}

