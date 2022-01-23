package tekiela.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tekiela.sales.cart.InMemoryCartStorage;
import tekiela.sales.offerting.OfferMaker;
import tekiela.sales.ordering.*;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderingTest {
    private DummyProductDetailsProvider productDetailsProvider;
    private InMemoryCartStorage cartStorage;
    private InMemoryReservationStorage reservationStorage;

    @BeforeEach
    void initializeSharedObjects() {
        productDetailsProvider = new DummyProductDetailsProvider();
        cartStorage = new InMemoryCartStorage();
        reservationStorage = new InMemoryReservationStorage();
    }

    @Test
    void itAllowsToOrderProducts() {
        //Arrange
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("product-1", BigDecimal.valueOf(10.10));
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId);
        sales.addToCart(customerId, productId);
        sales.getCurrentOffer(customerId);

        CustomerData customerData = exampleCustomerData();
        ReservationDetails reservationDetails = sales.acceptOffer(customerId, customerData);

        //Assert
        itContainsPaymentURL(reservationDetails);
        itContainsReservationId(reservationDetails);
        thereIsPendingReservationWithId(reservationDetails.getReservationId());
        reservationContainsCustomerDetails(reservationDetails.getReservationId(), customerData);
    }

    private void reservationContainsCustomerDetails(String reservationId, CustomerData customerData) {
        Reservation reservation = reservationStorage.load(reservationId).get();

        assertEquals(customerData.getEmail(), reservation.getCustomerEmail());
        assertEquals(customerData.getLastname(), reservation.getCustomerLastname());
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        Optional<Reservation> reservation = reservationStorage.load(reservationId);
        assertTrue(reservation.isPresent());

        assertTrue(reservation.get().isPending());
    }

    private void itContainsReservationId(ReservationDetails reservationDetails) {
        assertNotNull(reservationDetails.getReservationId());
    }

    private void itContainsPaymentURL(ReservationDetails reservationDetails) {
        assertNotNull(reservationDetails.getPaymentUrl());
        assertTrue(reservationDetails.getPaymentUrl().contains("http://dummygateway.com"));
    }

    private CustomerData exampleCustomerData() {
        return new CustomerData("john.doe@example.com", "john", "doe");
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
                reservationStorage);
    }
}
