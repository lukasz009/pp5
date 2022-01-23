package tekiela.sales.cart;

import java.util.HashMap;
import java.util.Optional;

public class InMemoryCartStorage {
    private HashMap<String, Cart> carts;

    public InMemoryCartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> loadForCustomer(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId, cart);
    }
}
