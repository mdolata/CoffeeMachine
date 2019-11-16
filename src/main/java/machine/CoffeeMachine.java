package machine;

import machine.commands.Command;
import machine.config.CoffeeMachineConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.*;

public class CoffeeMachine {
    private final List<CoffeeMachineConfig> configs;
    private final IngredientsHolder ingredientsHolder;
    private final MoneyHolder moneyHolder;

    public CoffeeMachine(IngredientsHolder ingredientsHolder, MoneyHolder moneyHolder, List<CoffeeMachineConfig> config) {
        this.ingredientsHolder = ingredientsHolder;
        this.moneyHolder = moneyHolder;
        this.configs = config;
    }

    public void doAction(Command command) {

        command.apply(moneyHolder, ingredientsHolder, configs);
    }

    public String status() {
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
