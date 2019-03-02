package endpoints;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import configs.Config;
import models.pet.Pet;



public class PetStoreEndPoints {

    private RequestSpecification given() {
        return RestAssured
                .given()
                .log().path()
                .log().body()
                .baseUri(Config.BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getPetById(String Id) {
        return given()
                .pathParam("petId","22")
                .when()
                .get(Config.PET_BY_ID)
                .then()
                .extract().response();
    }

    public Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(Config.PET_BY_STATUS)
                .then()
                .extract().response();
    }

    public Response createPet (Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(Config.CREATE_PET)
                .then()
                .extract().response();
    }

    public Response deleteById (long id) {
        return given()
                .when()
                .delete(Config.CREATE_PET, id)
                .then().extract().response();
    }
}
