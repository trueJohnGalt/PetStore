package endpoints;

import configs.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.order.Order;

public class PetStoreOrdersEndPoints {

    private RequestSpecification given() {
        return RestAssured
                .given()
                .log().all()
                .baseUri(Config.BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getStoreOrders() {
        return given()
                .when()
                .get(Config.GET_INVENTORY);
    }

    public Response getStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .get(Config.GET_INVENTORY_BY_ORDER_ID);
    }

    public Response deleteStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .delete(Config.GET_INVENTORY_BY_ORDER_ID);
    }

    public Response createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(Config.CREATE_ORDER);
    }
}
