package machine.commands;

import machine.domain.Ingredients;
import machine.parts.IngredientsHolder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class FillCommandTest {

    private final static Ingredients INGREDIENTS = new Ingredients(10, 20, 30, 40);
    private FillCommand fillCommand = (FillCommand) CommandFactory.get("fill", new Scanner(new CustomSystemIn()));

    @Test
    public void shouldFillIngredientsHolderWithAmountProvidedByUser() {
        IngredientsHolder ingredientsHolder = new IngredientsHolder(INGREDIENTS);
        Message message = fillCommand.apply(null, ingredientsHolder, null);

        Assert.assertEquals(INGREDIENTS.water + 10, ingredientsHolder.getWater());
        Assert.assertEquals(INGREDIENTS.milk + 10, ingredientsHolder.getMilk());
        Assert.assertEquals(INGREDIENTS.beans + 10, ingredientsHolder.getBeans());
        Assert.assertEquals(INGREDIENTS.cups + 10, ingredientsHolder.getCups());
        Assert.assertEquals(Message.EMPTY, message);

    }
}