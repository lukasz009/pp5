package tekiela.sales.ordering;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservationStorage implements ReservationStorage {
    Map<String, Reservation> reservationMap;

    public InMemoryReservationStorage() {
        this.reservationMap = new HashMap<>();
    }

    @Override
    public Optional<Reservation> load(String reservationId) {
        return Optional.ofNullable(reservationMap.get(reservationId));
    }

    @Override
    public void save(Reservation reservation) {
        reservationMap.put(reservation.getId(), reservation);
    }
}
