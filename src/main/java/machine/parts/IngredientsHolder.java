package machine.parts;

import machine.config.CoffeeMachineConfig;
import machine.domain.Ingredients;

public class IngredientsHolder {
    private int water;
    private int milk;
    private int beans;
    private int cups;

    public IngredientsHolder(int water, int milk, int beans, int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
    }

    public void fillUp(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    public Ingredients getIngredients(CoffeeMachineConfig config) {
        this.water -= config.waterNeeded;
        this.milk -= config.milkNeeded;
        this.beans -= config.beansNeeded;
        this.cups--;
        return new Ingredients(config.waterNeeded, config.milkNeeded, config.beansNeeded, 1);
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }
}