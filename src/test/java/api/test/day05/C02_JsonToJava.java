package api.test.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class C02_JsonToJava {
  String exlabUrl="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void t1_allUsersToList() {
        /**CT D05 TC06
         * given accept type is JSON
         * And query param pagesize is 50
         * And query param page is 1
         * When user sends a get request to /allusers/alluser
         * Then status code 200
         * put all response body inside a list by as() method
         * make several verifications
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get(exlabUrl + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        //put all body inside the json body into a list of map
        //convert jsonBody to Java Collection ==> De-serialization
        List<Map<String,Object>> allUsers=response.as(List.class); //Cannot parse object because no JSON deserializer
                        // found in classpath. Please put either Jackson (Databind) or Gson in the classpath.

        //verify that first users email is "afmercan@gmail.com"
        String actualEmail= (String) allUsers.get(0).get("email");
        String expectedEmail="afmercan@gmail.com";
        Assert.assertEquals(actualEmail,expectedEmail);

        //verify that first users job is "Manuel Tester"

        String actualJob= (String) allUsers.get(0).get("job");
        String expectedJob="Manual Tester";
        Assert.assertEquals(actualJob,expectedJob);

        //verify that second skills of first user is "java"
        List<String> skills= (List<String>) allUsers.get(0).get("skills");
        String actualSkills=skills.get(1);
        Assert.assertEquals(actualSkills,"Java");

        //verify that the third id of experience is 189
        List<Map<String,Object>> experience= (List<Map<String, Object>>) allUsers.get(0).get("experience");
        double actualId= (double) experience.get(2).get("id");
        Assert.assertEquals(actualId,189.0);

    }
}
