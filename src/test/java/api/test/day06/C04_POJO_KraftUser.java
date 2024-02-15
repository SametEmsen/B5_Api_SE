package api.test.day06;

import api.test.POJOTEMPLATES.Education;
import api.test.POJOTEMPLATES.Experience;
import api.test.POJOTEMPLATES.KraftUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class C04_POJO_KraftUser {
private String KRAFTBASEURL ="https://www.krafttechexlab.com/sw/api/v1";
    @Test
    public void getSpecificUser() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(KRAFTBASEURL + "/allusers/getbyid/1");
        response.prettyPrint();

        //burada array kulllanmamızın sebebi response objesi
        // Json tipinde {} ile başlamadı da [] array ile başladı

        KraftUser[] kraftUsers = response.as(KraftUser[].class);
        System.out.println("kraftUsers.length = " + kraftUsers.length);

        //validate that name is "MercanS"
        String actualName = kraftUsers[0].getName();
        String expectedName="MercanS";
        Assert.assertEquals(actualName,expectedName);

        //get skills
        //validate that second skill is java
        List<String> skills = kraftUsers[0].getSkills();
        String actualSkill = skills.get(1);
        String expectedSkill="Java";
        Assert.assertEquals(actualSkill,expectedSkill);

        //get education
        //validate that first education's id is 44

        List<Education> education = kraftUsers[0].getEducation();
        Education education1 = education.get(0);
        Integer actualID = education1.getId();
        Integer expectedID=44;
        Assert.assertEquals(actualID,expectedID);

        //validate  that second education's school is "School"
        Education education2 = education.get(1);
        String actualSchool = education2.getSchool();
        String expectedSchool="School";
        Assert.assertEquals(actualSchool,expectedSchool);

        //get experience
        //validate that

        List<Experience> experience = kraftUsers[0].getExperience();
        Experience experience1 = experience.get(0);
        String actualJob = experience1.getJob();
        String expectedJob="Junior Developer1";
        Assert.assertEquals(actualJob,expectedJob);
    }
}
