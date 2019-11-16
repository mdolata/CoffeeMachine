package machine.commands;

import machine.CoffeeMachine;

import java.util.List;

public interface Command {
    void apply(CoffeeMachine.MoneyHolder moneyHolder, CoffeeMachine.IngredientsHolder ingredientsHolder, List<CoffeeMachine.CoffeeMachineConfig> configs);

}
