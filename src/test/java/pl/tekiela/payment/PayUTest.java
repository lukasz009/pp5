package tekiela.payment;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    public static final String PAY_U_EMAPMPLE_MERCHANT_ID = "300746";

    @Test
    void itAllowsToRegisterPayment() {
        //A
        PaymentRequest request = examplePaymentRequest();
        //A
        PayU payU = thereIsPayU();
        RegistrationResponse response = payU.registerPayment(request);
        //A
        assertTrue(response.isSuccess());
    }

    private PaymentRequest examplePaymentRequest() {
        return PaymentRequest.builder()
                .notifyUrl("http://example.com/notify")
                .buyer(new PaymentRequest.Buyer("john.doe@example.com"))
                .currencyCode("PLN")
                .customerIp("127.0.0.1")
                .description("example description")
                .totalAmount(1000)
                .build();

    }

    private PayU thereIsPayU() {
        return new PayU(new RestTemplate(), PAY_U_EMAPMPLE_MERCHANT_ID);
    }
}
