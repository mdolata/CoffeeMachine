package machine.commands;

import machine.config.CoffeeTypeConfig;
import machine.domain.CoffeeType;
import machine.domain.Ingredients;
import machine.parts.IngredientsHolder;
import machine.parts.MoneyHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BuyCommandTest {
    private final static Ingredients INGREDIENTS = new Ingredients(10, 20, 30, 1);
    private static final int MONEY = 100;

    private CustomSystemIn source = new CustomSystemIn();
    private BuyCommand buyCommand = (BuyCommand) CommandFactory.get("buy", new Scanner(source));

    @Before
    public void setup() {
        source.setResponses(new int[] {49});
    }


    @Test
    public void shouldSubtractsIngredientsAndAddMoneyWhenUserBuyCoffeeSuccessfully() {
        IngredientsHolder ingredientsHolder = new IngredientsHolder(INGREDIENTS);
        MoneyHolder moneyHolder = new MoneyHolder(MONEY);
        List<CoffeeTypeConfig> configs = new ArrayList<>();
        configs.add(new CoffeeTypeConfig(CoffeeType.espresso, 10, 20, 30,40));

        Message message = buyCommand.apply(moneyHolder, ingredientsHolder, configs);

        Assert.assertEquals(140, moneyHolder.getMoney());
        Assert.assertEquals(0, ingredientsHolder.getWater());
        Assert.assertEquals(0, ingredientsHolder.getMilk());
        Assert.assertEquals(0, ingredientsHolder.getBeans());
        Assert.assertEquals(0, ingredientsHolder.getCups());
        Assert.assertEquals("I have enough resources, making you a coffee!", message.getContent().orElse(""));
    }

    @Test
    public void shouldNotSubtractsIngredientsAndAddMoneyWhenUserChangeHisMind() {
        IngredientsHolder ingredientsHolder = new IngredientsHolder(INGREDIENTS);
        MoneyHolder moneyHolder = new MoneyHolder(MONEY);
        List<CoffeeTypeConfig> configs = new ArrayList<>();
        configs.add(new CoffeeTypeConfig(CoffeeType.espresso, 10, 20, 30,40));

        source.setResponses(new int[] {98, 97, 99, 107});
        Message message = buyCommand.apply(moneyHolder, ingredientsHolder, configs);

        Assert.assertEquals(100, moneyHolder.getMoney());
        Assert.assertEquals(10, ingredientsHolder.getWater());
        Assert.assertEquals(20, ingredientsHolder.getMilk());
        Assert.assertEquals(30, ingredientsHolder.getBeans());
        Assert.assertEquals(1, ingredientsHolder.getCups());
        Assert.assertEquals(Message.EMPTY, message);
    }

    @Test
    public void shouldNotSubtractsIngredientsAndAddMoneyWhenIsNotEnoughResources() {
        IngredientsHolder ingredientsHolder = new IngredientsHolder(INGREDIENTS.remove(new Ingredients(0,0,0,1)));
        MoneyHolder moneyHolder = new MoneyHolder(MONEY);
        List<CoffeeTypeConfig> configs = new ArrayList<>();
        configs.add(new CoffeeTypeConfig(CoffeeType.espresso, 20, 40, 60,10));

        Message message = buyCommand.apply(moneyHolder, ingredientsHolder, configs);

        Assert.assertEquals(100, moneyHolder.getMoney());
        Assert.assertEquals(10, ingredientsHolder.getWater());
        Assert.assertEquals(20, ingredientsHolder.getMilk());
        Assert.assertEquals(30, ingredientsHolder.getBeans());
        Assert.assertEquals(0, ingredientsHolder.getCups());
        Assert.assertEquals("Sorry, not enough water, milk, beans, cups!", message.getContent().orElse(""));
    }
}