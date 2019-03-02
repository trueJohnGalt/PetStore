package endpoints;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import configs.Config;
import models.order.Order;

public class PetStoreOrdersEndPoints {

    private RequestSpecification given() {
        return RestAssured
                .given()
                .log().path()
                .log().body()
                .baseUri(Config.BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getStoreOrders() {
        return given()
                .when()
                .get(Config.GET_INVENTORY)
                .then()
                .extract()
                .response();
    }

    public Response getStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .get(Config.GET_INVENTORY_BY_ORDER_ID)
                .then()
                .extract().response();
    }

    public Response deleteStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .delete(Config.GET_INVENTORY_BY_ORDER_ID)
                .then()
                .extract().response();
    }

    public Response createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(Config.CREATE_ORDER)
                .then()
                .extract().response();
    }
}
