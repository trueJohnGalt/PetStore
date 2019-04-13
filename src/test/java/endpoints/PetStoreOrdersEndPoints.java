package endpoints;

import io.restassured.response.Response;
import models.order.Order;
import properties.PropertiesController;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetStoreOrdersEndPoints {

    private static final String GET_INVENTORY = PropertiesController.getProperty("petstore.get.inventory");
    private static final String GET_INVENTORY_BY_ORDER_ID = PropertiesController.getProperty("petstore.get.inventory.by.id");
    private static final String CREATE_ORDER = PropertiesController.getProperty("petstore.create.order");
    private static final String JSON_SCHEMA = "json_schema/order_schema.json";

    public Response getStoreOrders() {
        return given()
                .when()
                .get(GET_INVENTORY);
    }

    public Response getStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .get(GET_INVENTORY_BY_ORDER_ID);
    }

    public Response deleteStoreOrderById(int id) {
        return given()
                .when()
                .pathParam("orderId", id)
                .delete(GET_INVENTORY_BY_ORDER_ID);
    }

    public Response createOrder(Order order) {
        Response response = given()
                .body(order)
                .when()
                .post(CREATE_ORDER);

        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(JSON_SCHEMA));

        return response;
    }
}
