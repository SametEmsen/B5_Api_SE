package api.test.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_PathParameter {
    String bookCartUrl="https://bookcart.azurewebsites.net";
    @Test
    public void test_PathParameter01() {
        /**
         * PATH PARAMETER...FIRST WAY
         * Get specific book with PATH PARAMETER
         * Send GET request to bookCart
         * Content type is application/json
         * Path parameter: 2
         * Path parameter would be given inside the GET method
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(bookCartUrl + "/api/Book/2");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.asString().contains("HP2"));

        response.prettyPrint();
    }

    @Test
    public void test_PathParameter2() {
        /**
         * PATH PARAMETER...SECOND WAY
         * Get specific book with PATH PARAMETER
         * Send GET request to bookCart
         * Content type is application/json
         * Path parameter: 2
         * Path parameter would be given inside the pathParam() method
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",2)
                .when()
                .get(bookCartUrl + "/api/Book/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.asString().contains("HP2"));

        response.prettyPrint();
    }
}
