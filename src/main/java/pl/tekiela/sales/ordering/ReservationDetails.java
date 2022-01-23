package tekiela.sales.ordering;

public class ReservationDetails {
    private final String reservationId;
    private final String paymentId;
    private final String paymentUrl;

    public ReservationDetails(String reservationId, String paymentId, String paymentUrl) {
        this.reservationId = reservationId;
        this.paymentId = paymentId;
        this.paymentUrl = paymentUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getReservationId() {
        return reservationId;
    }
}
