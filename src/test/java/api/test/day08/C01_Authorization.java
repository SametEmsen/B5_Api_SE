package api.test.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class C01_Authorization {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_login() {
        String email="samet@gmail.com";
        String password="1234.Asdf";

        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/allusers/login");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token = response.path("token");
        System.out.println("token = " + token);

    }

    @Test
    public static String getToken() {
        String email="samet@gmail.com";
        String password="1234.Asdf";

        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/allusers/login");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token = response.path("token");
        return token;

    }

    @Test
    public static Map<String,Object> getToken(String email, String password) {

        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/allusers/login");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token = response.path("token");
        Map<String,Object> authorization=new HashMap<>();
        authorization.put("token",token);
        return authorization;

    }
}
