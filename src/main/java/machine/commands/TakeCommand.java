package machine.commands;

import machine.config.CoffeeMachineConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;

public class TakeCommand implements Command {

        @Override
        public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
            int take = moneyHolder.take();

            //todo print there is not fine
            System.out.println(String.format("I gave you $%s", take));
        }
    }