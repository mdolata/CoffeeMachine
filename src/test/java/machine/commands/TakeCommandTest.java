package machine.commands;

import machine.parts.MoneyHolder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class TakeCommandTest {

    private static final int MONEY = 100;
    private TakeCommand takeCommand = (TakeCommand) CommandFactory.get("take", new Scanner(new CustomSystemIn()));

    @Test
    public void apply() {
        MoneyHolder moneyHolder = new MoneyHolder(MONEY);

        Message message = takeCommand.apply(moneyHolder, null, null);

        Assert.assertEquals(0, moneyHolder.getMoney());
        Assert.assertEquals(String.format("I gave you $%s", MONEY), message.getContent().orElse(""));
    }
}