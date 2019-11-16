package machine;

import machine.commands.CommandFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainLoop {
    public static void main(String[] args) {
        List<CoffeeMachine.CoffeeMachineConfig> configs = createCoffeeConfig();
        CoffeeMachine coffeeMachine = createCoffeeMachine(configs);

        loop(coffeeMachine);
    }

    private static CoffeeMachine createCoffeeMachine(List<CoffeeMachine.CoffeeMachineConfig> configs) {
        CoffeeMachine.IngredientsHolder ingredientsHolder = new CoffeeMachine.IngredientsHolder(400, 540, 120, 9);
        CoffeeMachine.MoneyHolder moneyHolder = new CoffeeMachine.MoneyHolder(550);

        return new CoffeeMachine(ingredientsHolder, moneyHolder, configs);
    }

    private static void loop(CoffeeMachine coffeeMachine) {
        Scanner scanner = new Scanner(System.in);

        mainLoop: while (true) {
            System.out.println("akcja!");
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

    private static List<CoffeeMachine.CoffeeMachineConfig> createCoffeeConfig() {
        CoffeeMachine.CoffeeMachineConfig espresso = new CoffeeMachine.CoffeeMachineConfig(CoffeeMachine.CoffeeType.espresso, 250, 0, 16, 4);
        CoffeeMachine.CoffeeMachineConfig latte = new CoffeeMachine.CoffeeMachineConfig(CoffeeMachine.CoffeeType.latte, 350, 75, 20, 7);
        CoffeeMachine.CoffeeMachineConfig cappuccino = new CoffeeMachine.CoffeeMachineConfig(CoffeeMachine.CoffeeType.cappuccino, 200, 100, 12, 6);

        List<CoffeeMachine.CoffeeMachineConfig> configs = new ArrayList<>();
        configs.add(espresso);
        configs.add(latte);
        configs.add(cappuccino);
        return configs;
    }
}
