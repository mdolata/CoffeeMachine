package machine.commands;

import machine.CoffeeMachine;

import java.util.List;
import java.util.Scanner;

public class TakeCommand implements Command {

        public TakeCommand(Scanner scanner) {}

        @Override
        public void apply(CoffeeMachine.MoneyHolder moneyHolder, CoffeeMachine.IngredientsHolder ingredientsHolder, List<CoffeeMachine.CoffeeMachineConfig> configs) {
            int take = moneyHolder.take();

            //todo print there is not fine
            System.out.println(String.format("I gave you $%s", take));
        }
    }