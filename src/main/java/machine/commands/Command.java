package machine.commands;

import machine.config.CoffeeMachineConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;

public interface Command {
    void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs);

}
