package endpoints;

import configs.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.pet.Pet;



public class PetStoreEndPoints {

    private RequestSpecification given() {
        return RestAssured
                .given()
                .log().all()
                .baseUri(Config.BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getPetById(String id) {
        return given()
                .pathParam("petId",id)
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
