import static io.restassured.RestAssured.given;

import org.testng.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

public class DogAPI {

    @Test
    public void apiTest() {

        RestAssured.baseURI = "https://dog.ceo";

         System.out.println("********************** List of all breeds **********************");
         given().when().get("/api/breeds/list/all")
                 .then().assertThat().log().all().statusCode(200);

//---------------------------------------------------------------------------------------------------------------------------------------------
       /*
        System.out.println("********************** Verify retriever **********************");

       String getList = given().when().get("/api/breeds/list/all")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(getList); //for parsing Json
        String placeID = js.getString("message.retriever");
        System.out.println("place_ID = " + placeID);
*/
        //-------------------------------------------------------------------------------------------------------------------------------
        System.out.println("********************** Produce list of sub-breed for retriever **********************");
        given().when().get("/api/breed/retriever/list")
                .then().assertThat().log().all().statusCode(200);

        //-------------------------------------------------------------------------------------------------------------------------------
        System.out.println("********************** Produce Random image for Golden sub-breed **********************");
         given().when().get("/api/breed/retriever/golden/images/random")
                    .then().log().all().assertThat().statusCode(200);

        //-------------------------------------------------------------------------------------------------------------------------------------
    }

}
