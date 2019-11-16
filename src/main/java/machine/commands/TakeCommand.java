package machine.commands;

import machine.config.CoffeeTypeConfig;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;

import java.util.List;

public class TakeCommand implements Command {

    @Override
    public Message apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeTypeConfig> configs) {
        int take = moneyHolder.take();

        return Message.create(String.format("I gave you $%s", take));
    }
}