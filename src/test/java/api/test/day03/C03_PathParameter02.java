package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C03_PathParameter02 {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
    @Test
    public void getUserById() {
        /**
         * Send GET request to kraftexlab
         * Get all information of a specific user with path param
         * PathParameter: 1
         * Validate that status code is 200
         * Validate that response contains 'MercanS'
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/1");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("MercanS"));

    }

    @Test
    public void getUserByIdNegative() {
        /**
         * Send GET request to kraftexlab
         * Get all information of a specific user with path param
         * PathParameter: 0
         * Validate that status code is 404
         * Validate that response contains 'No User Record Found...'
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/0");

        Assert.assertEquals(response.statusCode(),404);
        Assert.assertTrue(response.asString().contains("No User Record Found..."));
        response.prettyPrint();
    }
}
