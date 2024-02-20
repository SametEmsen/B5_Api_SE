package api.test.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.test.day08.C01_Authorization.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class C02_Post_Put_Patch_Delete_Experience_Flow {
    static int experienceID;
    static Response response;
    static String email = "samet@gmail.com";
    static String password = "1234.Asdf";

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test (priority = 1)
    public void t_addExperience() {
        String body = "{\n" +
                "  \"job\": \"Product Manager\",\n" +
                "  \"company\": \"TechKraft\",\n" +
                "  \"location\": \"Izmir\",\n" +
                "  \"fromdate\": \"2018-01-01\",\n" +
                "  \"todate\": \"2021-12-21\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .header("token", getToken())
                .body(body)
                .when()
                .post("/experience/add")
                .prettyPeek();

        assertEquals(response.statusCode(), 200);
        experienceID = response.path("id");
    }

    /**
     * {
     * "job": "Junior Developer",
     * "company": "Kraft Techex",
     * "location": "USA",
     * "fromdate": "YYYY-MM-DD",
     * "todate": "YYYY-MM-DD",
     * "current": "false",
     * "description": "Description"
     * }
     */
    @Test (priority = 2)
    public void t_updateExperience_PUT() {
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("job", "SDET");
        experienceBody.put("company", "Kraft Techex");
        experienceBody.put("location", "USA");
        experienceBody.put("fromdate", "2020-12-12");
        experienceBody.put("todate", "2022-10-10");
        experienceBody.put("current", "false");
        experienceBody.put("description", "SDET");

        response = given().accept(ContentType.JSON)
                .headers(getToken(email,password))
                .queryParam("id", experienceID)
                .body(experienceBody)
                .when()
                .put("/experience/updateput")
                .prettyPeek();
    }

    @Test (priority = 3)
    public void t_updateExperience_Patch() {
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("company", "Facebook");
        experienceBody.put("location", "NY");

        response=given().accept(ContentType.JSON)
                .pathParam("id",experienceID)
                .headers(getToken(email,password))
                .body(experienceBody)
                .when()
                .patch("/experience/updatepatch/{id}")
                .prettyPeek();

        assertEquals(response.statusCode(),200);

    }

    @Test (priority = 4)
    public void t_deleteExperience() {
        response=given().accept(ContentType.JSON)
                .pathParam("id",experienceID)
                .headers(getToken(email,password))
                .when()
                .delete("experience/delete/{id}")
                .prettyPeek();

        assertEquals(response.statusCode(),200);
    }
}
