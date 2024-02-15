package api.test.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class C01_DeSerializationExample {

    private String BOOKCARTURL="https://bookcart.azurewebsites.net";

    @Test
    public void test() {
        /**
         * CONVERT JSON OBJECT TO JAVA COLLECTION WITH AS() METHOD (DE-SERILIZIATION)
         * Send GET request to bookCart
         * Content type is application/json
         * Path parameter: 2
         * Validate that title is "HP2"
         * Validate that author is "JKR"
         * Validate that category is "Mystery"
         * Validate that coverFileName is "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
         * Validate that price is "235.00"
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when()
                .get(BOOKCARTURL + "/api/Book/{id}");

        //path Method (GPATH SYNTAX)
        String expectedTitle="HP2";
        String actualTitle=response.path("title");
        Assert.assertEquals(actualTitle,expectedTitle);

        // JsonPath

        JsonPath jsonPath = response.jsonPath();
        String expectedAuthor="JKR";
        String actualAuthor= jsonPath.getString("author");
        Assert.assertEquals(actualAuthor,expectedAuthor);

        //JsonToJava ==> De-serialization
        Map<String,Object> book=response.as(Map.class);
        String expectedCategory="Mystery";
        String actualCategory= (String) book.get("category");
        Assert.assertEquals(actualCategory,expectedCategory);

        //validate price
        Double actualPrice= (double) book.get("price");
        Double expectedPrice=235.00;
        Assert.assertEquals(actualPrice,expectedPrice);



    }

}
