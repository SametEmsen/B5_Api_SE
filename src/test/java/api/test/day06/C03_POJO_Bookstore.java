package api.test.day06;

import api.test.POJOTEMPLATES.Book;
import api.test.POJOTEMPLATES.BookStore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class C03_POJO_Bookstore {
    private String URL="https://bookstore.toolsqa.com";

    @Test
    public void getAllBooks() {
        /**
         * CONVERT JSON OBJECT TO POJO(PLAIN OLD JAVA OBJECT -- CUSTOM JAVA CLASS) WITH AS() METHOD (DE-SERIALIZITION)
         * Send GET request to bookstore to get all books
         * Convert response(json body) to custom java class
         * Make validations...
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(URL+"/BookStore/v1/Books");

        BookStore bookStore = response.as(BookStore.class);
        List<Book> books = bookStore.getBooks();
        Book book = books.get(0);
        System.out.println("book.getTitle() = " + book.getTitle());

        //validate that first isbn is 9781449325862"
        String firstIsbn="9781449325862";
        String actualIsbn=book.getIsbn();
        Assert.assertEquals(actualIsbn,firstIsbn);

        //validate that last title of last book is "Understanding ECMAScript 6"
        String expectedLastTitle="Understanding ECMAScript 6";
        Book book1 = books.get(books.size() - 1);
        String actualLastTitle=book1.getTitle();
        Assert.assertEquals(actualLastTitle,expectedLastTitle);


    }
}
