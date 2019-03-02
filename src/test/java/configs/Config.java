package configs;

public class Config {
    public static final String BASE_URI = "https://petstore.swagger.io/v2";

    public static final String PET_BY_ID = "/pet/{petId}";

    public static final String PET_BY_STATUS = "/pet/findByStatus";

    public static final String CREATE_PET = "/pet";

    public static final String GET_INVENTORY = "/store/inventory";

    public static final String GET_INVENTORY_BY_ORDER_ID = "/store/order/{orderId}";

    public static final String CREATE_ORDER = "/store/order";
}
