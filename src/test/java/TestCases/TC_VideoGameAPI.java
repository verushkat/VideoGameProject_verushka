package TestCases;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Created by verushkat on 9/4/2020
 */
public class TC_VideoGameAPI {

        @Test (priority = 1)
        public void test_getAllVideoGames()
        {
            given()
            .when()
                    .get("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames")
            .then()
                    .statusCode(200);

        }


    @Test(priority = 2)
    public void test_postCreateNewVideoGame() {

        HashMap data = new HashMap();
        data.put("id", "105");
        data.put("name", "spider man");
        data.put("releaseDate", "2020-09-04T07:45:49.235Z");
        data.put("reviewScore", "5");
        data.put("category", "Adventure");
        data.put("rating", "Universal");


        Response res =
                given()
                        .contentType("application/json")
                        .body(data)
                        .when()
                        .post("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames")

                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
    }

    @Test (priority =3)
    public void test_getVideoGame(){



        given()
                .when()
                .get("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames/105")
                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id", equalTo("105"))
                .body("videoGame.name", equalTo("spider man"));

    }
    @Test(priority = 4)
    public void test_updateEditVideoGame(){


        HashMap data = new HashMap();
        data.put("id","105");
        data.put("name","Pacman");
        data.put("releaseDate","2020-09-04T07:45:49.235Z");
        data.put("reviewScore","4");
        data.put("category","Romance");
        data.put("rating","Universal");


        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames/100")
                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id",equalTo("105"))
                .body("videoGame.name",equalTo("Pacman"));


    }


    @Test(priority = 5)
    public void test_DeleteVideoGame(){

        Response res =

                given()

                        .when()
                        .delete("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames/105")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String jsonString=res.asString();
        Assert.assertEquals(jsonString.contains("Record Deleted Successfully"),true);

    }
}
