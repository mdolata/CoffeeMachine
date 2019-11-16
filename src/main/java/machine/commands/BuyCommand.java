package machine.commands;

import machine.config.CoffeeMachineConfig;
import machine.domain.CoffeeType;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class BuyCommand implements Command {
    private final Scanner scanner;

    BuyCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
        String input = scanner.nextLine();

        if ("back".equals(input)) {
            return;
        }
        int inputCoffeeType = Integer.parseInt(input);

        CoffeeType coffeeType = Arrays.stream(CoffeeType.values())
                .filter(coffeeType2 -> inputCoffeeType == coffeeType2.number)
                .findFirst()
                .orElseThrow(RuntimeException::new);


        CoffeeMachineConfig theConfig = configs.stream()
                .filter(config -> config.coffeeType.equals(coffeeType))
                .findFirst()
                .get();

        Set<String> isValid = validate(ingredientsHolder, theConfig);

        if (isValid.isEmpty()) {
            System.out.println("I have enough resources, making you a coffee!");
            moneyHolder.put(theConfig.cost);
            ingredientsHolder.getIngredients(theConfig);
        } else {
            String notValid = String.join(", ", isValid);
            System.out.println(String.format("Sorry, not enough %s!", notValid));
        }
    }

    private Set<String> validate(IngredientsHolder ingredientsHolder, CoffeeMachineConfig config) {
        Set<String> notValidHolders = new LinkedHashSet<>();

        if (ingredientsHolder.getWater() < config.waterNeeded) {
            notValidHolders.add("water");
        } else if (ingredientsHolder.getMilk() < config.milkNeeded) {
            notValidHolders.add("milk");
        } else if (ingredientsHolder.getBeans() < config.beansNeeded) {
            notValidHolders.add("beans");
        } else if (ingredientsHolder.getCups() < 1) {
            notValidHolders.add("cups");
        }

        return notValidHolders;
    }
}