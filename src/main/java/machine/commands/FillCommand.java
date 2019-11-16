package machine.commands;

import machine.CoffeeMachine;

import java.util.List;
import java.util.Scanner;

public class FillCommand implements Command {
        private final Scanner scanner;

        public FillCommand(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void apply(CoffeeMachine.MoneyHolder moneyHolder, CoffeeMachine.IngredientsHolder ingredientsHolder, List<CoffeeMachine.CoffeeMachineConfig> configs) {
            int waterAdd = Integer.parseInt(scanner.nextLine());
            int milkAdd = Integer.parseInt(scanner.nextLine());
            int beansAdd = Integer.parseInt(scanner.nextLine());
            int cupsAdd = Integer.parseInt(scanner.nextLine());

            ingredientsHolder.fillUp(waterAdd,milkAdd, beansAdd,cupsAdd);
        }
    }