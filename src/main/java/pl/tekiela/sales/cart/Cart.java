package tekiela.sales.cart;

import tekiela.sales.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public int getProductsCount() {
        return products.size();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<CartItem> getItems() {
        return products.stream()
                .map(p -> new CartItem(p.getProductId(), 1))
                .collect(Collectors.toList());
    }
}
