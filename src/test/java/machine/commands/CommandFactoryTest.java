package machine.commands;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFactoryTest {

    @Test
    public void getBuyCommand() {
        Command command = CommandFactory.get("buy", null);

        Assert.assertEquals(BuyCommand.class, command.getClass());
    }

    @Test
    public void getTakeCommand() {
        Command command = CommandFactory.get("take", null);

        Assert.assertEquals(TakeCommand.class, command.getClass());
    }

    @Test
    public void getFillCommand() {
        Command command = CommandFactory.get("fill", null);

        Assert.assertEquals(FillCommand.class, command.getClass());
    }

    @Test
    public void getDefaultCommand() {
        Command command = CommandFactory.get("notSupported", null);

        Assert.assertNotEquals(BuyCommand.class, command.getClass());
        Assert.assertNotEquals(FillCommand.class, command.getClass());
        Assert.assertNotEquals(TakeCommand.class, command.getClass());
    }
}