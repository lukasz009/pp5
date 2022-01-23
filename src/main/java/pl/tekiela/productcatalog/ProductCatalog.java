package tekiela.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalog {
    private ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts()
                .stream()
                .filter(product -> product.isPublished())
                .collect(Collectors.toList());
    }

    public void addProduct(String productId, String name, String description) {
        Product product = new Product(productId, name, description);
        productStorage.save(product);
    }

    public void updatePrice(String productId, BigDecimal price) {
        Product loaded = productStorage.loadById(productId)
                        .orElseThrow(() -> new ProductDoesNotExistsException());
        loaded.setPrice(price);
        productStorage.save(loaded);
    }

    public void publish(String productId) {
        Product loaded = productStorage.loadById(productId)
                .orElseThrow(() -> new ProductDoesNotExistsException());
        loaded.publish();
        productStorage.save(loaded);
    }

    public Product loadProduct(String productId) {
        return productStorage.loadById(productId)
                .orElseThrow(() -> new ProductDoesNotExistsException());
    }

    public void assignImage(String productId, String productImage) {
        Product loaded = productStorage.loadById(productId)
                .orElseThrow(() -> new ProductDoesNotExistsException());
        loaded.setImageUrl(productImage);
        productStorage.save(loaded);
    }
}
