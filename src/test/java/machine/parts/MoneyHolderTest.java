package machine.parts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyHolderTest {
    private static final int INITIAL_AMOUNT = 100;
    private MoneyHolder moneyHolder = new MoneyHolder(INITIAL_AMOUNT);

    @Test
    public void take() {
        int amount = 100;
        moneyHolder.put(amount);

        Assert.assertEquals(amount + INITIAL_AMOUNT, moneyHolder.getMoney());
    }

    @Test
    public void put() {
        int take = moneyHolder.take();

        Assert.assertEquals(INITIAL_AMOUNT, take);
        Assert.assertEquals(0, moneyHolder.getMoney());
    }
}