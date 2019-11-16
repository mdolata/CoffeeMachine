package machine.commands;

import machine.config.CoffeeMachineConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;
import java.util.Scanner;

public class FillCommand implements Command {
    private final Scanner scanner;

    FillCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
        int waterAdd = Integer.parseInt(scanner.nextLine());
        int milkAdd = Integer.parseInt(scanner.nextLine());
        int beansAdd = Integer.parseInt(scanner.nextLine());
        int cupsAdd = Integer.parseInt(scanner.nextLine());

        ingredientsHolder.fillUp(waterAdd, milkAdd, beansAdd, cupsAdd);
    }
}