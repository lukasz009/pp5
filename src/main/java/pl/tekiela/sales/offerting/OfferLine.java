package tekiela.sales.offerting;

import java.math.BigDecimal;

public class OfferLine {
    private final String productId;
    private final String productName;
    private final int qty;
    private final BigDecimal unitPrice;

    public OfferLine(String productId, String productName, int qty, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(qty));
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQty() {
        return qty;
    }
}
