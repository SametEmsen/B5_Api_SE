package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C06_PathMethod {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_UserGetRequest_with_PathMethod() {
        Response response = given().accept(ContentType.JSON)        //RestAssured yukarıya static olarak koyduk
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //print each value of response

        System.out.println("response.body().path(\"name\").toString() = "
                + response.body().path("name").toString());

        System.out.println("response.body().path(\"email\").toString() = "
                + response.body().path("email").toString());

        System.out.println("response.body().path(\"id\").toString() = "
                + response.body().path("id").toString());

        int id = response.path("id[0]");
        String name = response.path("name[0]");
        String email=response.path("email[0]");

        Assert.assertEquals(id,111);
        Assert.assertEquals(name,"Thomas Eduson");
        Assert.assertEquals(email,"thomas@test.com");
    }
}
