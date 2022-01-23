package tekiela.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    void itAllowsAssignLimitTOCC() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        //Assert
        assertEquals(BigDecimal.valueOf(1000), card.getLimit());
    }

    @Test
    void itAllowsAssignLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(2000));
        //Assert
        assertEquals(BigDecimal.valueOf(2000), card.getLimit());
    }

    @Test
    void itDenyAssignLimitBelowCertainThresholdV1() {
        CreditCard card = new CreditCard("1234-5678");
        try {
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("It should throw exception");
        } catch (LimitBelowCertainThresholdException e) {
            assertTrue(true);
        }
    }

    @Test
    void itAllowWithdraw() {
        //A
        CreditCard card = new CreditCard("1234-5678");
        card.assignCreditLimit(BigDecimal.valueOf(500));
        //A
        card.withdraw(BigDecimal.valueOf(200));
        //A
        assertEquals(BigDecimal.valueOf(300), card.getCurrentBalance());
    }

    @Test
    void cantWithdrawOverLimit() {
        //A
        CreditCard card = new CreditCard("1234-5678");
        card.assignCreditLimit(BigDecimal.valueOf(500));
        //A
        card.withdraw(BigDecimal.valueOf(200));
        card.withdraw(BigDecimal.valueOf(200));
        //A
        assertThrows(NotEnoughMoneyException.class, () -> {
            card.withdraw(BigDecimal.valueOf(200));
        });

    }

    @Test
    void itDenyAssignLimitBelowCertainThresholdV2() {
        CreditCard card = new CreditCard("1234-5678");
        assertThrows(LimitBelowCertainThresholdException.class, () -> {
            card.assignCreditLimit(BigDecimal.valueOf(50));
        });
    }


    @Test
    void testDoubleOrFloat() {
        double d1 = 0.3;
        double d2 = 0.2;
        double d1d2 = d1 - d2;
        System.out.println(d1d2);

        BigDecimal a = BigDecimal.valueOf(0.3);
        BigDecimal b = BigDecimal.valueOf(0.2);
        BigDecimal ab = a.subtract(b);

        System.out.println(ab);

    }
}
