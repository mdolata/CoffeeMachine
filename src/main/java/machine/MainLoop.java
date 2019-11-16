package machine;

import machine.commands.CommandFactory;
import machine.config.CoffeeTypeConfig;
import machine.domain.CoffeeType;
import machine.domain.Ingredients;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainLoop {
    public static void main(String[] args) {
        List<CoffeeTypeConfig> configs = createCoffeeConfig();
        CoffeeMachine coffeeMachine = createCoffeeMachine(configs);

        loop(coffeeMachine);
    }

    private static CoffeeMachine createCoffeeMachine(List<CoffeeTypeConfig> configs) {
        Ingredients ingredients = new Ingredients(400, 540, 120, 9);
        IngredientsHolder ingredientsHolder = new IngredientsHolder(ingredients);
        MoneyHolder moneyHolder = new MoneyHolder(550);

        return new CoffeeMachine(ingredientsHolder, moneyHolder, configs);
    }

    private static void loop(CoffeeMachine coffeeMachine) {
        Scanner scanner = new Scanner(System.in);

        mainLoop:
        while (true) {
            System.out.println("action!");
            String action = scanner.nextLine();

            switch (action) {
                case "buy":
                case "fill":
                case "take":
                    coffeeMachine
                            .doAction(CommandFactory.get(action, scanner))
                            .getContent()
                            .ifPresent(System.out::println);
                    break;
                case "remaining":
                    System.out.println(coffeeMachine.status());
                    break;
                case "exit":
                    break mainLoop;
            }
        }
    }

    private static List<CoffeeTypeConfig> createCoffeeConfig() {
        CoffeeTypeConfig espresso = new CoffeeTypeConfig(CoffeeType.espresso, 250, 0, 16, 4);
        CoffeeTypeConfig latte = new CoffeeTypeConfig(CoffeeType.latte, 350, 75, 20, 7);
        CoffeeTypeConfig cappuccino = new CoffeeTypeConfig(CoffeeType.cappuccino, 200, 100, 12, 6);

        List<CoffeeTypeConfig> configs = new ArrayList<>();
        configs.add(espresso);
        configs.add(latte);
        configs.add(cappuccino);
        return configs;
    }
}
