package api.test.day07;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class C01_GpathSyntaxPractice {

    private String karaftBaseUrl="https://www.krafttechexlab.com/sw/api/v1";
    @Test
    public void gpathPractice() {

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get(karaftBaseUrl + "/allusers/alluser");

        //get name of first user
        String nameOfFirstUser=response.path("name[0]");
        System.out.println("nameOfFirstUser = " + nameOfFirstUser);

        // get id of second user
        int idOfSecondUser = response.path("id[1]");
        System.out.println("idOfSecondUser = " + idOfSecondUser);

        //get email of third user
        JsonPath jsonPath = response.jsonPath();
        String emailOfThirdUser = jsonPath.getString("email[2]");
        System.out.println("emailOfThirdUser = " + emailOfThirdUser);

        //get all names
        List<String> nameList = response.path("name");
        System.out.println("nameList = " + nameList);

        Assert.assertEquals(nameList.size(),50);

        //get all names with jsonPath
        List<String> nameListJson = jsonPath.getList("name");
        System.out.println("nameListJson = " + nameListJson);

        //get skills of first user
        List<String> skillsOfFirstUser = response.path("skills[0]");
        System.out.println("skillsOfFirstUser = " + skillsOfFirstUser);

        List<String> list = jsonPath.getList("skills[0]");
        System.out.println("list = " + list);

        //get first skill of first user
        String skill1 = response.path("skills[0][0]");

        //get second skill of first user
        String skill2 = response.path("skills[0][1]");

        //get education of firstUser
        List<Map<String,Object>> eduListOfMap=response.path("education[0]");
        Integer id1 = (Integer) eduListOfMap.get(0).get("id");
        System.out.println("id1 = " + id1);

        //
        Map<String,Object> secondEduOfFirstUser=response.path("education[0][1]");
        Integer id2 = (Integer) secondEduOfFirstUser.get("id");
        System.out.println("id2 = " + id2);


    }
}
