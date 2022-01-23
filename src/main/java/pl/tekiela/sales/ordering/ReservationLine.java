package tekiela.sales.ordering;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReservationLine {
    @Id
    @GeneratedValue
    private int id;

    private String productId;
    private int quantity;

    @ManyToOne
    private Reservation reservation;

    ReservationLine() {};


    public ReservationLine(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
