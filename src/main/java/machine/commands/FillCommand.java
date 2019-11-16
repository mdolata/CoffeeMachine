package machine.commands;

import machine.config.CoffeeTypeConfig;
import machine.domain.Ingredients;
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
    public Message apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeTypeConfig> configs) {
        int waterAdd = Integer.parseInt(scanner.nextLine());
        int milkAdd = Integer.parseInt(scanner.nextLine());
        int beansAdd = Integer.parseInt(scanner.nextLine());
        int cupsAdd = Integer.parseInt(scanner.nextLine());

        Ingredients ingredients = new Ingredients(waterAdd, milkAdd, beansAdd, cupsAdd);
        ingredientsHolder.fillUp(ingredients);

        return Message.EMPTY;
    }
}