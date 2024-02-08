package api.test.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGETRequest {
    String petSoreUrl="https://petstore.swagger.io/v2";

    String bookCartUrl="https://bookcart.azurewebsites.net";
    @Test
    public void t_basicGETRequest () {
        Response response=RestAssured.get(petSoreUrl+"/store/inventory");

        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body
        response.prettyPrint();
    }

    @Test
    public void t_getRequestWithBody() {
        /**
         * given except type is json
         * when user sends a GET request
         * Then verify that status code is 200
         * And body is json format
         */

       Response response= RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(petSoreUrl+"/store/inventory");
        //verify that status code
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void t_getRequest_withRestAssuredVerify() {
        //verify using restAssured
        RestAssured
                .given() // ---- > starts request
                .accept(ContentType.JSON)
                .when()
                .get(petSoreUrl+"/store/inventory")
                .then()  // ---- > starts validatable response
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json");
    }

    @Test
    public void t_getRequest_withContains() {
        //verify that body has Jonathan Maberry
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(bookCartUrl + "/api/Book");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);
      //  response.prettyPrint();

        //response validation method --> contains
        //first way
        Assert.assertTrue(response.prettyPrint().contains("Jonathan Maberry"));
        //second way
        Assert.assertTrue(response.body().asString().contains("Jonathan Maberry"));
    }
}
