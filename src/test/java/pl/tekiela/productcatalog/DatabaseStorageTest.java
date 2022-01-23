package tekiela.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
public class DatabaseStorageTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void itStoreProduct() {
        //Arrange
        Product product = thereIsProduct();
        DatabaseProductStorage databaseProductStorage = thereIsDatabaseProductStorage();
        //Act
        databaseProductStorage.save(product);
        //Assert
        Product loadedProduct = databaseProductStorage.loadById(product.getProductId())
                .get();

        assertEquals(product.getName(), loadedProduct.getName());
        assertEquals(product.getProductId(), loadedProduct.getProductId());
    }

    private DatabaseProductStorage thereIsDatabaseProductStorage() {
        return new DatabaseProductStorage(productRepository);
    }

    private Product thereIsProduct() {
        Product product = new Product(UUID.randomUUID().toString(), "My Product", "Nice One", BigDecimal.ONE);

        return product;
    }
}
