package api.test.day06;

import api.test.POJOTEMPLATES.BookCart;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class C02_Pojo_BookCart {
    private String BOOKCARTURL="https://bookcart.azurewebsites.net";

    @Test
    public void test() {
        /**
         * CONVERT JSON OBJECT TO POJO (Plain Old Java Object - Custom Class)
         * WITH AS() METHOD (DE-SERILIZIATION)
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

        BookCart bookCart = response.as(BookCart.class);
        System.out.println("bookCart.getBookId() = " + bookCart.getBookId());
        System.out.println("bookCart.getTitle() = " + bookCart.getTitle());
        System.out.println("bookCart.getAuthor() = " + bookCart.getAuthor());
        System.out.println("bookCart.getCategory() = " + bookCart.getCategory());
        System.out.println("bookCart.getPrice() = " + bookCart.getPrice());
        System.out.println("bookCart.getCoverFileName() = " + bookCart.getCoverFileName());
    }
}
