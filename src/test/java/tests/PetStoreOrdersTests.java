package tests;

import com.jayway.restassured.response.Response;
import endpoints.PetStoreOrdersEndPoints;
import models.order.Order;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PetStoreOrdersTests {

    public static final PetStoreOrdersEndPoints PET_STORE_ORDERS_END_POINTS = new PetStoreOrdersEndPoints();

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
        assertions.assertThat(orderFromResponse.getStatusCode()).isEqualTo(200);
        assertions.assertThat(createdOrderFromService.getPetId()).isEqualTo(order.getPetId());
        assertions.assertThat(createdOrderFromService.getQuantity()).isEqualTo(order.getQuantity());
        assertions.assertThat(createdOrderFromService.getStatus()).isEqualTo(order.getStatus());
        assertions.assertThat(createdOrderFromService.isComplete()).isEqualTo(order.isComplete());
    }

    @Test
    public void readOrder(){
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        int createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        Order createdOrderFromService = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId).as(Order.class);
        //then

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(orderFromResponse.getStatusCode()).isEqualTo(200);
        assertions.assertThat(createdOrderFromService.getPetId()).isEqualTo(order.getPetId());
        assertions.assertThat(createdOrderFromService.getQuantity()).isEqualTo(order.getQuantity());
        assertions.assertThat(createdOrderFromService.getStatus()).isEqualTo(order.getStatus());
        assertions.assertThat(createdOrderFromService.isComplete()).isEqualTo(order.isComplete());
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
    }
}
