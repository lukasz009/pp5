package tekiela.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tekiela.sales.cart.InMemoryCartStorage;
import tekiela.sales.offerting.Offer;
import tekiela.sales.offerting.OfferMaker;
import tekiela.sales.ordering.DummyPaymentGateway;
import tekiela.sales.ordering.InMemoryReservationStorage;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class MakingAnOfferTest {

    private DummyProductDetailsProvider productDetailsProvider;
    private InMemoryCartStorage cartStorage;

    @BeforeEach
    void initializeSharedObjects() {
        productDetailsProvider = new DummyProductDetailsProvider();
        cartStorage = new InMemoryCartStorage();
    }

    @Test
    void itGeneratesNewOfferBasedOnBasketItems() {
        //Arrange
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("product-1", BigDecimal.valueOf(10.10));
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId);
        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        //Assert
        assertEquals(BigDecimal.valueOf(20.20), offer.getTotal());
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    private String thereIsProduct(String productId, BigDecimal price) {
        productDetailsProvider.products.put(
                productId,
                new Product(productId, productId.toUpperCase(), price));

        return productId;
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                cartStorage,
                productDetailsProvider,
                new OfferMaker(productDetailsProvider),
                new DummyPaymentGateway(),
                new InMemoryReservationStorage());
    }
}
