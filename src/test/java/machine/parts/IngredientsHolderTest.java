package machine.parts;

import machine.config.CoffeeTypeConfig;
import machine.domain.CoffeeType;
import machine.domain.Ingredients;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientsHolderTest {

    private final static Ingredients INGREDIENTS = new Ingredients(10, 20, 30, 40);
    private IngredientsHolder ingredientsHolder = new IngredientsHolder(INGREDIENTS);

    @Test
    public void fillUp() {
        Ingredients ingredients = new Ingredients(10, 20, 30, 40);

        ingredientsHolder.fillUp(ingredients);

        Assert.assertEquals(INGREDIENTS.water + ingredients.water, ingredientsHolder.getWater());
        Assert.assertEquals(INGREDIENTS.milk + ingredients.milk, ingredientsHolder.getMilk());
        Assert.assertEquals(INGREDIENTS.beans + ingredients.beans, ingredientsHolder.getBeans());
        Assert.assertEquals(INGREDIENTS.cups + ingredients.cups, ingredientsHolder.getCups());
    }

    @Test
    public void takeIngredients() {
        CoffeeTypeConfig coffeeTypeConfig = new CoffeeTypeConfig(CoffeeType.espresso, 10, 20, 30, 40);
        ingredientsHolder.takeIngredients(coffeeTypeConfig);

        Assert.assertEquals(INGREDIENTS.water - coffeeTypeConfig.waterNeeded, ingredientsHolder.getWater());
        Assert.assertEquals(INGREDIENTS.milk - coffeeTypeConfig.milkNeeded, ingredientsHolder.getMilk());
        Assert.assertEquals(INGREDIENTS.beans - coffeeTypeConfig.beansNeeded, ingredientsHolder.getBeans());
        Assert.assertEquals(INGREDIENTS.cups - 1, ingredientsHolder.getCups());
    }
}