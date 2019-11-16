package machine;

import machine.commands.CommandFactory;
import machine.config.CoffeeMachineConfig;
import machine.domain.CoffeeType;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainLoop {
    public static void main(String[] args) {
        List<CoffeeMachineConfig> configs = createCoffeeConfig();
        CoffeeMachine coffeeMachine = createCoffeeMachine(configs);

        loop(coffeeMachine);
    }

    private static CoffeeMachine createCoffeeMachine(List<CoffeeMachineConfig> configs) {
        IngredientsHolder ingredientsHolder = new IngredientsHolder(400, 540, 120, 9);
        MoneyHolder moneyHolder = new MoneyHolder(550);

        return new CoffeeMachine(ingredientsHolder, moneyHolder, configs);
    }

    private static void loop(CoffeeMachine coffeeMachine) {
        Scanner scanner = new Scanner(System.in);

        mainLoop: while (true) {
            System.out.println("action!");
            String action = scanner.nextLine();

            switch (action) {
                case "buy":
                case "fill":
                case "take":
                    coffeeMachine.doAction(CommandFactory.get(action, scanner));
                    break;
                case "remaining":
                    System.out.println(coffeeMachine.status());
                    break;
                case "exit":
                    break mainLoop;
            }
        }
    }

    private static List<CoffeeMachineConfig> createCoffeeConfig() {
        CoffeeMachineConfig espresso = new CoffeeMachineConfig(CoffeeType.espresso, 250, 0, 16, 4);
        CoffeeMachineConfig latte = new CoffeeMachineConfig(CoffeeType.latte, 350, 75, 20, 7);
        CoffeeMachineConfig cappuccino = new CoffeeMachineConfig(CoffeeType.cappuccino, 200, 100, 12, 6);

        List<CoffeeMachineConfig> configs = new ArrayList<>();
        configs.add(espresso);
        configs.add(latte);
        configs.add(cappuccino);
        return configs;
    }
}
