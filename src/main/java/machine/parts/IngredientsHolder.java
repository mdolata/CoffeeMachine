package machine.parts;

import machine.config.CoffeeTypeConfig;
import machine.domain.Ingredients;

public class IngredientsHolder {
    private Ingredients ingredients;

    public IngredientsHolder(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public void fillUp(Ingredients ingredients) {
        this.ingredients = this.ingredients.fillWith(ingredients);
    }

    public Ingredients takeIngredients(CoffeeTypeConfig config) {
        Ingredients ingredients = new Ingredients(config.waterNeeded, config.milkNeeded, config.beansNeeded, 1);
        this.ingredients = this.ingredients.remove(ingredients);

        return ingredients;
    }

    public int getWater() {
        return this.ingredients.water;
    }

    public int getMilk() {
        return this.ingredients.milk;
    }

    public int getBeans() {
        return this.ingredients.beans;
    }

    public int getCups() {
        return this.ingredients.cups;
    }
}