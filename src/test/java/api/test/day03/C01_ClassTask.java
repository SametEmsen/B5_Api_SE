package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C01_ClassTask {

    String bookStoreUrl="https://bookstore.toolsqa.com";
    @Test
    public void test_GetAllBooks() {
        /**
         * Send GET request to bookstore
         * Endpoint: "/Books"
         * Validate that status code 200
         * Validate that response body is return as JSON format
         * Validate that response body has "Eric Elliott"
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(bookStoreUrl + "/BookStore/v1/Books");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.body().asString().contains("Eric Elliott"));

    }
}
