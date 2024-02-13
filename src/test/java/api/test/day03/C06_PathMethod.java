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

    @Test
    public void t_allUsers_with_pathMethod() {
   /*
            CT TC022
            Given accept type json
            And query  parameter value pagesize 50
            And query  parameter value page 1
            When user sends GET request to /allusers/alluser
            Then response status code should be 200
            And response content-type application/json; charset=UTF-8
            Verify the first id is 1
            Verify the first name is MercanS
            Verify the last id is 102
            Verify the last name is GHAN
     */

        Response response = given().accept(ContentType.JSON)
                .and()
                .log().all()            //request sorgusunu verir
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");

        int firstId = response.path("id[0]");

        Assert.assertEquals(firstId,1);

       // int lastId=response.path("id[49]");
        int lastId=response.path("id[-1]");   // son sıradan başlar (yani sonuncuyu verir)
        Assert.assertEquals(lastId,102);

        String lastName=response.path("name[-1]");
        Assert.assertEquals(lastName,"GHAN");

        //multiDimentional olarak
        System.out.println("response.path(\"skills[0][1]\") = " + response.path("skills[0][1]"));


    }
}
