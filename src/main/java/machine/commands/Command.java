package machine.commands;

import machine.config.CoffeeTypeConfig;
import machine.domain.Message;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;

@FunctionalInterface
public interface Command {
    Message apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeTypeConfig> configs);

}
