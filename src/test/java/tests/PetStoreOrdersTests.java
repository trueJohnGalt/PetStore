package tests;

import endpoints.PetStoreOrdersEndPoints;
import io.restassured.response.Response;
import models.order.Order;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;


public class PetStoreOrdersTests extends BaseTest {

    private static final PetStoreOrdersEndPoints PET_STORE_ORDERS_END_POINTS = new PetStoreOrdersEndPoints();

    @Test
    public void createOrder(){
        //given
        Order order = Order.createDefaultOrder();
        //when
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        //then
        int createdOrderId = orderFromResponse.body().as(Order.class).getId();
        Order createdOrderFromService = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId).as(Order.class);

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(createdOrderFromService.getPetId()).isEqualTo(order.getPetId());
        assertions.assertThat(createdOrderFromService.getQuantity()).isEqualTo(order.getQuantity());
        assertions.assertThat(createdOrderFromService.getStatus()).isEqualTo(order.getStatus());
        assertions.assertThat(createdOrderFromService.isComplete()).isEqualTo(order.isComplete());
        assertions.assertThat(orderFromResponse.getStatusCode()).isEqualTo(200);
        assertions.assertAll();
    }

    @Test
    public void readOrder(){
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        int createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        Response createdOrderFromService = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId);
        Order createdOrderFromServiceObject = createdOrderFromService.as(Order.class);
        //then

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(orderFromResponse.getStatusCode()).isEqualTo(200);
        assertions.assertThat(createdOrderFromServiceObject.getPetId()).isEqualTo(order.getPetId());
        assertions.assertThat(createdOrderFromServiceObject.getQuantity()).isEqualTo(order.getQuantity());
        assertions.assertThat(createdOrderFromServiceObject.getStatus()).isEqualTo(order.getStatus());
        assertions.assertThat(createdOrderFromServiceObject.isComplete()).isEqualTo(order.isComplete());
        PET_STORE_ORDERS_END_POINTS.assertJsonSchema(createdOrderFromService, JSON_SCHEMA);
        assertions.assertAll();
    }

    @Test
    public void deleteOrder(){
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        int createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        PET_STORE_ORDERS_END_POINTS.deleteStoreOrderById(createdOrderId);
        //then
        Response orderById = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(orderById.getStatusCode()).isEqualTo(404);
        assertions.assertAll();
    }
}
