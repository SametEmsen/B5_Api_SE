package api.test.day07;

import api.test.POJOTEMPLATES.BookCart;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class C02_GsonPractice {

    Gson gson=new Gson();
    @Test
    public void jsonToJava() {
        String book="{\n" +
                "    \"bookId\": 4,\n" +
                "    \"title\": \"HP4\",\n" +
                "    \"author\": \"JKR\",\n" +
                "    \"category\": \"Fiction\",\n" +
                "    \"price\": 321.00,\n" +
                "    \"coverFileName\": \"9d31690d-3b1d-4faa-a1d2-3a680a935008HP4.jpg\"\n" +
                "}";

        //print book with json format
        System.out.println("book = " + book);

        Map<String,Object> map = gson.fromJson(book, Map.class);
        System.out.println("map = " + map);
        System.out.println("map.get(\"author\") = " + map.get("author"));

        //convert json body to bookCart object

        BookCart bookCart=gson.fromJson(book, BookCart.class);
        System.out.println("bookCart.toString() = " + bookCart.toString());

    }

    @Test
    public void javaToJson() {
        //create a bookcart object
        BookCart bookCart=new BookCart(4,"HP4","JKR","Fiction",321.00,"9d31690d-3b1d-4faa-a1d2-3a680a935008HP4.jpg");
        System.out.println(bookCart.toString());

        String json = gson.toJson(bookCart);
        System.out.println("json = " + json);
    }
}
