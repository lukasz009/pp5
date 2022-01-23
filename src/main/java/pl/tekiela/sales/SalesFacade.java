package tekiela.sales;

import tekiela.sales.cart.Cart;
import tekiela.sales.cart.InMemoryCartStorage;
import tekiela.sales.offerting.Offer;
import tekiela.sales.offerting.OfferMaker;
import tekiela.sales.ordering.*;


public class SalesFacade {
    InMemoryCartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private OfferMaker offerMaker;
    private PaymentGateway paymentGateway;
    private ReservationStorage reservationStorage;

    public SalesFacade(
            InMemoryCartStorage cartStorage,
            ProductDetailsProvider productDetailsProvider,
            OfferMaker offerMaker,
            PaymentGateway paymentGateway,
            ReservationStorage reservationStorage
    ) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
        this.offerMaker = offerMaker;
        this.paymentGateway = paymentGateway;
        this.reservationStorage = reservationStorage;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = cartStorage.loadForCustomer(customerId)
                .orElse(Cart.empty());

        Product product = productDetailsProvider.getDetails(productId);

        cart.addProduct(product);

        cartStorage.save(customerId, cart);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.loadForCustomer(customerId)
                .orElse(Cart.empty());

        return offerMaker.createAnOffer(cart);
    }

    public ReservationDetails acceptOffer(String customerId, CustomerData customerData) {
        Cart cart = cartStorage.loadForCustomer(customerId)
                .orElse(Cart.empty());
        Offer currentOffer = offerMaker.createAnOffer(cart);

        Reservation reservation = Reservation.of(
                currentOffer,
                cart.getItems(),
                customerData
        );

        reservation.registerPayment(paymentGateway);

        reservationStorage.save(reservation);

        return new ReservationDetails(
                reservation.getId(),
                reservation.getPaymentId(),
                reservation.getPaymentUrl()
        );
    }
}
