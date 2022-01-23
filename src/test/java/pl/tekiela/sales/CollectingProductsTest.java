package tekiela.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tekiela.sales.cart.Cart;
import tekiela.sales.cart.CartItem;
import tekiela.sales.cart.InMemoryCartStorage;
import tekiela.sales.offerting.OfferMaker;
import tekiela.sales.ordering.DummyPaymentGateway;
import tekiela.sales.ordering.InMemoryReservationStorage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CollectingProductsTest {

    private DummyProductDetailsProvider productDetailsProvider;
    private InMemoryCartStorage cartStorage;

    @BeforeEach
    void initializeSharedObjects() {
        productDetailsProvider = new DummyProductDetailsProvider();
        cartStorage = new InMemoryCartStorage();
    }

    @Test
    void itAllowsToAddProductsToCart() {
        //Arrange
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("product-1");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId);

        //Assert
        thereIsXProductInCustomersCart(1, customerId);
    }

    @Test
    void itAllowsToAddMultipleProductsToCart() {
        //Arrange
        String customerId = thereIsCustomer("Kuba");
        String productId1 = thereIsProduct("product-1");
        String productId2 = thereIsProduct("product-2");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId1);
        sales.addToCart(customerId, productId2);

        //Assert
        thereIsXProductInCustomersCart(2, customerId);
    }

    @Test
    void itIncreaseProductQuantityForTheSameProductAddedTwice() {
        //Arrange
        String customerId = thereIsCustomer("Kuba");
        String productId1 = thereIsProduct("product-1");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId1);
        sales.addToCart(customerId, productId1);

        //Assert
        thereIsXProductInCustomersCart(1, customerId);
        quantityOfProductXProductInCustomersCartEquals(productId1, customerId, 2);
    }

    private void quantityOfProductXProductInCustomersCartEquals(String productId, String customerId, int expectedQuantity) {
        Cart cart = cartStorage.loadForCustomer(customerId).get();

        CartItem loadedCartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .get();

        assertEquals(expectedQuantity, loadedCartItem.getQuantity());
    }


    private void thereIsXProductInCustomersCart(int productsCount, String customerId) {
        Optional<Cart> optionalCart = cartStorage.loadForCustomer(customerId);
        assertTrue(optionalCart.isPresent(), "There is no cart available");

        Cart cart = optionalCart.get();
        assertEquals(productsCount, cart.getProductsCount());
    }

    private SalesFacade thereIsSalesModule() {

        return new SalesFacade(cartStorage, productDetailsProvider, new OfferMaker(productDetailsProvider), new DummyPaymentGateway(), new InMemoryReservationStorage());
    }

    private String thereIsProduct(String productId) {
        productDetailsProvider.products.put(
                productId,
                new Product(productId, productId.toUpperCase(), BigDecimal.valueOf(10.10)));

        return productId;
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    class DummyProductDetailsProvider implements ProductDetailsProvider {
        HashMap<String, Product> products;

        public DummyProductDetailsProvider() {
            this.products = new HashMap<>();
        }

        @Override
        public Product getDetails(String productId) {
            return products.get(productId);
        }
    }
}
