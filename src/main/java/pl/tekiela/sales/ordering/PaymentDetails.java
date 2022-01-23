package tekiela.sales.ordering;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentDetails {
    private String paymentId;
    private String url;

    PaymentDetails() {};

    public PaymentDetails(String paymentId, String url) {
        this.paymentId = paymentId;
        this.url = url;
    }

    public String getId() {
        return paymentId;
    }

    public String getUrl() {
        return url;
    }
}
