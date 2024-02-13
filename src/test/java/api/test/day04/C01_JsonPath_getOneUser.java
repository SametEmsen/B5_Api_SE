package api.test.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class C01_JsonPath_getOneUser {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getUser_and_verify_with_jsonPath() {
 /*
            CT D04 TC01
            When user sends a GET request to /allusers/getbyid/{id}
            Given accept type is json
            And Path param user id is 111

            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
            And user's name should be Thomas Eduson
            And user's id should be 111
            And user's email should be thomas@test.com
         */
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");
//        Then the status Code should be 200
        Assert.assertEquals(response.statusCode(),200);
//        And Content type json should be "application/json; charset=UTF-8"
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
//        And user's name should be Thomas Eduson
        String actualName=response.path("name[0]");
        Assert.assertEquals(actualName,"Thomas Eduson");
//        And user's id should be 111
        int actualId = response.path("id[0]");
        Assert.assertEquals(actualId,111);
//        And user's email should be thomas@test.com
        String actualEmail=response.path("email[0]");
        Assert.assertEquals(actualEmail,"thomas@test.com");


        //make same verification with JsonPath
        // create a jsonPath object with respons

        JsonPath jsonPath = response.jsonPath();
        actualId=jsonPath.get("id[0]");
        Assert.assertEquals(actualId,111);

        actualName=jsonPath.get("name[0]");
        Assert.assertEquals(actualName,"Thomas Eduson");

        actualEmail=jsonPath.get("email[0]");
        Assert.assertEquals(actualEmail,"thomas@test.com");


    }
}
