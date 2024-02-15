package api.test.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class C01_HamcrestMatcher {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void t1_getONeUser() {
        /** CT D05 TC01
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code should be 200
         * And content type  should be application/json; charset=UTF-8
         */

        given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8");
    }

    @Test
    public void t2_getOneUser_with_HamcrestMatcher() {

        given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8")
                .and()
                .body("id[0]", Matchers.equalTo(111),
                        "name[0]",Matchers.equalTo("Thomas Eduson"),
                        "job[0]",Matchers.equalTo("Developer"),
                        "skills[0][3]",Matchers.equalTo("Cypress"),
                        "education[0].school[1]", equalTo("Delft University"),
                        "education[0][1].school", equalTo("Delft University"),
                        "[0].education[1].school", equalTo("Delft University"),
                        "education.school[0][1]", equalTo("Delft University")
                    );
    }

    @Test
    public void t3_oneUser_verifyHeaderContent_with_HamcrestMatcher() {
        /**CT D05 TC03
         * given accept type is JSON
         * And path param id is 111
         * When user send a get request to /allusers/getbyid/{id}
         * Then status code 200
         * And content Type application/json; charset=UTF-8
         * And response header Content-Type should be application/json; charset=UTF-8
         * And response header Content-Length should be 606
         * And json data should have email equal "thomas@test.com"
         * And json data should have company equal "GHAN Software"
         */

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("userID",111)
                .when()
                .get("/allusers/getbyid/{userID}")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
                .and()
                .header("Content-Type",equalTo("application/json; charset=UTF-8"))
                .and()
                .header("Content-Length",equalTo("606"))
                .and()
                .header("Date",notNullValue())
                .assertThat()
                .body("email[0]",equalTo("thomas@test.com"),
                "company[0]",equalTo("GHAN Software"));
    }

    @Test
    public void t4_oneUser_hasItem_with_hamcrest() {

        // bütün email ler içinden verileni arar

        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
                .and()
                .body("email",hasItem("ghan@krafttechexlab.com"))
                .log().all();
    }

    @Test
    public void t5_oneUser_hasItems_with_hamcrest() {
        /** CT D05 TC05
         * given accept type is JSON
         * And query param pagesize is 50
         * And query param page is 1
         * When user sends a get request to /allusers/alluser
         * Then status code 200
         * And content Type application/json; charset=UTF-8
         * And response header Content-Type should be application/json; charset=UTF-8
         * And response header Server should be Apache/2
         * And response header has Date
         * And json data should have name equal "GHAN","Aegon Targaryen HTU","Mansimmo"
         * And json data should have  "bilkent" for school
         * And json data should have  "Junior Developer1" for first user's first experience
         */
        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(equalTo("application/json; charset=UTF-8"))
                .header("Server",equalTo("Apache/2"))
                .header("Date",notNullValue())
                .body("name",hasItems("GHAN","Aegon Targaryen HTU","Mansimmo"))
                .body("education[5].school",hasItem("bilkent"))
                .body("experience[0].job",hasItem("Junior Developer1"));

    }
}
