package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class C04_QueryParameter {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void queryParam01() {
/**
 * QUERY PARAMETER...FIRST WAY
 * Get specific user with QUERY PARAMETER
 * Send GET request to kraft
 * Content type is application/json
 * name: Thomas Eduson
 * skills: Cypress
 * pagesize: 50
 * page: 1
 * Query parameter would be given inside the query parameter method
 * Validate that status code is 200
 * Validate that content-type is application/json
 * Validate that response body contains Developer
 */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("name", "Thomas Eduson")
                .queryParam("skills", "Cypress")
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertTrue(response.asString().contains("Developer"));
    }

    @Test
    public void queryParamsWithMap() {
        /**
    * QUERY PARAMETER...SECOND WAY
    * Get specific user with QUERY PARAMETER
    * Send GET request to kraft
    * Content type is application/json
    * name: Thomas Eduson
    * skills: Cypress
    * pagesize: 50
    * page: 1
    * Query parameter would be given inside a map
    * Validate that status code is 200
    * Validate that content-type is application/json
    * Validate that response body contains Developer
    */

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Thomas Eduson");
        map.put("skills", "Cypress");
        map.put("pagesize", 50);
        map.put("page", 1);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParams(map)
                .when()
                .get("/allusers/alluser");


        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        Assert.assertTrue(response.asString().contains("Developer"));

    }
}
