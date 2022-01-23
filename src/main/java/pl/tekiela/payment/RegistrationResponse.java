package tekiela.payment;

public class RegistrationResponse {

    public static final String STATUS_OK = "SUCCESS";

    private PaymentStatus status;
    private String redirectUri;
    private String orderId;

    public RegistrationResponse() {}

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return getStatus().getStatusCode().equals(STATUS_OK);
    }

    class PaymentStatus {
        private String statusCode;

        public PaymentStatus() {}

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }
    }
}

//{
//  "status": {
//    "statusCode": "SUCCESS"
//  },
//  "redirectUri": "https://merch-prod.snd.payu.com/pay/?orderId=MPMVF29LXW220122GUEST000P01&token=eyJhbGciOiJIUzI1NiJ9.eyJvcmRlcklkIjoiTVBNVkYyOUxYVzIyMDEyMkdVRVNUMDAwUDAxIiwicG9zSWQiOiJvZzVIeTU5ZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQ0xJRU5UIl0sInBheWVyRW1haWwiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImV4cCI6MTY0MjkzNzk5NSwiaXNzIjoiUEFZVSIsImF1ZCI6ImFwaS1nYXRld2F5Iiwic3ViIjoiUGF5VSBzdWJqZWN0IiwianRpIjoiNTQyYjFmOTQtYTc0YS00MzU1LTgzN2MtNmIzOGI2NzY1YjJlIn0.FPDjgzUAqeLRfl6ysyBjoAxfvj225pSpr1poFbKRIeY",
//  "orderId": "MPMVF29LXW220122GUEST000P01"
//}