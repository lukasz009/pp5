package tekiela.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    public static final int CREDIT_MIN_VALUE = 100;
    private BigDecimal limit;
    private BigDecimal balance;
    private String cardName;

    public CreditCard(String cardName) {
        this.cardName = cardName;
    }

    public void assignCreditLimit(BigDecimal creditLimit) {
        if (isBelowCreditLimit(creditLimit)) {
            throw new LimitBelowCertainThresholdException();
        }
        this.limit = creditLimit;
        this.balance = creditLimit;
    }

    private boolean isBelowCreditLimit(BigDecimal creditLimit) {
        return BigDecimal.valueOf(CREDIT_MIN_VALUE).compareTo(creditLimit) > 0;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void withdraw(BigDecimal money) {
        if (balance.compareTo(money) < 0) {
            throw new NotEnoughMoneyException();
        }
        balance = balance.subtract(money);
    }

    public BigDecimal getCurrentBalance() {
        return balance;
    }
}
