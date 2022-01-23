package tekiela.productcatalog;

import java.util.List;
import java.util.Optional;

public class DatabaseProductStorage implements ProductStorage {

    private ProductRepository productRepository;

    public DatabaseProductStorage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> loadById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
}
