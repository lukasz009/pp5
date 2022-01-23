package tekiela.productcatalog;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {
    void save(Product product);

    Optional<Product> loadById(String productId);

    List<Product> allProducts();
}
