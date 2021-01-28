package pl.mczuma.vouchershop.sales.basket;

public class BasketItem {
    private final String productId;
    private final Integer quantity;

    public BasketItem(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
