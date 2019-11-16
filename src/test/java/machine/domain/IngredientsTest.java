package machine.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientsTest {

    Ingredients ingredients;

    @Before
    public void setup() {
        int water = 10;
        int milk = 20;
        int beans = 30;
        int cups = 40;

        ingredients = new Ingredients(water, milk, beans, cups);
    }
    @Test
    public void fillWith() {
        int addedAmount = 10;

        Ingredients filledIngredients = ingredients.fillWith(new Ingredients(addedAmount, addedAmount, addedAmount, addedAmount));

        Assert.assertEquals(ingredients.water + addedAmount, filledIngredients.water);
        Assert.assertEquals(ingredients.milk + addedAmount, filledIngredients.milk);
        Assert.assertEquals(ingredients.beans + addedAmount, filledIngredients.beans);
        Assert.assertEquals(ingredients.cups + addedAmount, filledIngredients.cups);
    }

    @Test
    public void remove() {
        int removeAmount = 10;

        Ingredients filledIngredients = ingredients.remove(new Ingredients(removeAmount, removeAmount, removeAmount, removeAmount));

        Assert.assertEquals(ingredients.water - removeAmount, filledIngredients.water);
        Assert.assertEquals(ingredients.milk - removeAmount, filledIngredients.milk);
        Assert.assertEquals(ingredients.beans - removeAmount, filledIngredients.beans);
        Assert.assertEquals(ingredients.cups - removeAmount, filledIngredients.cups);
    }
}