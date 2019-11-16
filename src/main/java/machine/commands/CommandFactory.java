package machine.commands;

import machine.config.CoffeeTypeConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;
import java.util.Scanner;

public class CommandFactory {
    public static Command get(String action, Scanner scanner) {
        Command command = null;

        switch (action) {
            case "buy":
                command = new BuyCommand(scanner);
                break;
            case "take":
                command = new TakeCommand();
                break;
            case "fill":
                command = new FillCommand(scanner);
                break;
            default: command = (MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeTypeConfig> configs) -> Message.EMPTY;
        }
        return command;
    }
}