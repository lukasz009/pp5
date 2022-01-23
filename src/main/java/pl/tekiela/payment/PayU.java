package tekiela.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {
    private RestTemplate http;
    private String merchantPosId;

    public PayU(RestTemplate http, String merchantPosId) {
        this.http = http;
        this.merchantPosId = merchantPosId;
    }

    public RegistrationResponse registerPayment(PaymentRequest request) {
        request.setMerchantPosId(merchantPosId);
        ResponseEntity<RegistrationResponse> response = http.postForEntity(
                "https://secure.snd.payu.com/api/v2_1/orders",
                request,
                RegistrationResponse.class
        );

        return response.getBody();
    }
}
