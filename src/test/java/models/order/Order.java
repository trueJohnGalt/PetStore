package models.order;


public class Order {
    private int id;
    private int petId;
    private int quantity;
    private Status status;
    private boolean complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public static Order createDefaultOrder() {
        Order order = new Order();
        order.setId((int)(Math.random()*1000));
        order.setPetId(2);
        order.setQuantity(1);
        order.setStatus(Status.delivered);
        order.setComplete(false);
        return order;
    }
}
