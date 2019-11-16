package machine;

import machine.commands.Command;
import machine.config.CoffeeTypeConfig;
import machine.domain.Message;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.*;

class CoffeeMachine {
    private final List<CoffeeTypeConfig> configs;
    private final IngredientsHolder ingredientsHolder;
    private final MoneyHolder moneyHolder;

    CoffeeMachine(IngredientsHolder ingredientsHolder, MoneyHolder moneyHolder, List<CoffeeTypeConfig> config) {
        this.ingredientsHolder = ingredientsHolder;
        this.moneyHolder = moneyHolder;
        this.configs = config;
    }

    Message doAction(Command command) {
        return command.apply(moneyHolder, ingredientsHolder, configs);
    }

    String status() {
        return String.format("The coffee machine has:\n" +
                        "%s of water\n" +
                        "%s of milk\n" +
                        "%s of coffee beans\n" +
                        "%s of disposable cups\n" +
                        "%s of money",
                ingredientsHolder.getWater(), ingredientsHolder.getMilk(), ingredientsHolder.getBeans(),
                ingredientsHolder.getCups(), moneyHolder.getMoney());
    }
}
