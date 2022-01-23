package tekiela.sales.ordering;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentDetails registerPayment(String reservationId, BigDecimal total, String customerEmail, String customerLastname);
}
