package tekiela.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryProductStorage implements ProductStorage {
    Map<String, Product> products;

    public InMemoryProductStorage() {
        this.products = new HashMap<>();
    }

    @Override
    public void save(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public Optional<Product> loadById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> allProducts() {
        return products.values()
                .stream()
                .collect(Collectors.toList());

    }
}
