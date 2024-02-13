package api.test.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C02_JsonPAth_getAllUsers {
    @BeforeClass
    public void beforeClass() {
        RestAssured.baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t_getAllUsers_and_verify_with_jsonPath() {
          /*
            CT D04 TC02
            Given accept type is json
            When user sends a GET request to /allusers/alluser
            Then the status Code should be 200
            And Content type json should be "application/json; charset=UTF-8"
         */
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath = response.jsonPath();

        //verify that first id is equal to 1
        int actualId = jsonPath.get("id[0]");
        int expectedId=1;
        Assert.assertEquals(actualId,expectedId);

        //verify that 5th id is equal to 33
        int fifthActualId = jsonPath.get("id[4]");
        int fifthExpectedId=33;
        Assert.assertEquals(fifthActualId,fifthExpectedId);

        //verify that 5th name is equal to Sebastian
        String fifthActualName=jsonPath.get("name[4]");
        String fifthExpectedName="Sebastian";
        Assert.assertEquals(fifthActualName,fifthExpectedName);

        //verify that ids are 1,5,24,29,33
        List<Integer> actualId_List = jsonPath.getList("id");
        //first way for expectedList
        List<Integer> expectedId_List=new ArrayList<>();
        expectedId_List.add(1);
        expectedId_List.add(5);
        expectedId_List.add(24);
        expectedId_List.add(29);
        expectedId_List.add(33);
        Assert.assertEquals(actualId_List,expectedId_List);

        //second way for expectedList
        List<Integer> expectedId_List2=new ArrayList<>(Arrays.asList(1,5,24,29,33));
        Assert.assertEquals(actualId_List,expectedId_List2);

        //iterate ids and print
        for (Integer integer : actualId_List) {
            System.out.println("integer = " + integer);
        }

        //verify that first skills of first user is PHP

        //first way
        String actualFisrtSkillOfFirstUser=jsonPath.getString("skills[0][0]");
        String expectedFisrtSkillOfFirstUser="PHP";
        Assert.assertEquals(actualFisrtSkillOfFirstUser,expectedFisrtSkillOfFirstUser);

        //second way
        List<String> firstUserSkills = jsonPath.get("skills[0]");

        String actualFirstUserSkillOfFirstUserWithJsonPath=firstUserSkills.get(0);
        Assert.assertEquals(actualFirstUserSkillOfFirstUserWithJsonPath,expectedFisrtSkillOfFirstUser);

        //verify that first user's school is "School or Bootcamp"
        //first way
        Map<String,Object> map = jsonPath.get("education[0][0]");
        System.out.println("map.get(\"school\") = " + map.get("school"));
        String actualEducation=(String) map.get("school");  //map de object dönüş olduğu için string e casting yapmak gerek
        String expectedEducation="School or Bootcamp";
        Assert.assertEquals(actualEducation,expectedEducation);

        //second way
        String alternativeActualEdu=jsonPath.get("education[0].school[0]");
        Assert.assertEquals(alternativeActualEdu,expectedEducation);

        //third way
        List<Map<String,Object>> listOfMap=jsonPath.get("education[0]");
        String actualListOfMap=(String) listOfMap.get(0).get("school");
        Assert.assertEquals(actualListOfMap,expectedEducation);

    }

    @Test
    public void homework() {
           /*
            HWT D04 TC03
            Given accept type json
            When user sends a get request to https://bookstore.toolsqa.com/BookStore/v1/Books
            Then status code should be 200
            And content type should be application/json; charset=utf-8
            And the first book isbn should be 9781449325862
            And the first book publisher should be O'Reilly Media

            example with path method and jsonPath method
     */
    }
}
