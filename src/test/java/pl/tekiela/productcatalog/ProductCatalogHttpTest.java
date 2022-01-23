package tekiela.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCatalogHttpTest {

    @LocalServerPort
    int localServerPort;

    @Autowired
    TestRestTemplate httpClient;

    @Autowired
    ProductCatalog productCatalog;

    @Test
    void itListAddedProducts() {
        //Arrange
        thereIsReadyToSellProduct("product-1");
        thereIsReadyToSellProduct("product-2");
        thereIsReadyToSellProduct("product-3");
        thereIsDraftProduct("product-4");
        //Act
        ResponseEntity<Product[]> response = callApiForProducts();
        //Assert
        responseIsSuccess(response);
        thereIsXProductsAvailableInHttpResponse(3, response);
    }

    private void thereIsDraftProduct(String productId) {
        productCatalog.addProduct(productId, "sample name", "description");
    }

    private void thereIsReadyToSellProduct(String productId) {
        productCatalog.addProduct(productId, "sample name", "description");
        productCatalog.updatePrice(productId, BigDecimal.valueOf(10.10));
        productCatalog.publish(productId);
    }

    private void responseIsSuccess(ResponseEntity<Product[]> response) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private ResponseEntity<Product[]> callApiForProducts() {
        String url = String.format("http://localhost:%s/api/products", localServerPort);
        return httpClient.getForEntity(url, Product[].class);
    }

    private void thereIsXProductsAvailableInHttpResponse(int productsCount, ResponseEntity<Product[]> response) {
        Product[] products = response.getBody();
        assertEquals(productsCount, products.length);
    }

}
