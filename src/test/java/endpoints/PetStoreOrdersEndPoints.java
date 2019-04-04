package endpoints;

import configs.Config;
import io.restassured.response.Response;
import models.order.Order;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetStoreOrdersEndPoints {

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

    public void assertJsonSchema(Response response, String path) {
        response
                .then()
                .body(matchesJsonSchemaInClasspath(path));
    }
}
