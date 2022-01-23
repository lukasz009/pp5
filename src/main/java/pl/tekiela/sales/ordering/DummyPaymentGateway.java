package tekiela.sales.ordering;

import java.math.BigDecimal;
import java.util.UUID;

public class DummyPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(String reservationId, BigDecimal total, String customerEmail, String customerLastname) {

        return new PaymentDetails(UUID.randomUUID().toString(), "http://dummygateway.com/" + reservationId);
    }
}
