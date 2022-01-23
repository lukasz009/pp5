package tekiela.sales.cart;

public class CartItem {
    private String productId;
    private int qty;

    public CartItem(String productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return qty;
    }
}
