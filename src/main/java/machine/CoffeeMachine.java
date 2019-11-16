package machine;

import machine.commands.Command;

import java.util.*;

public class CoffeeMachine {
    private final List<CoffeeMachineConfig> configs;
    private final IngredientsHolder ingredientsHolder;
    private final MoneyHolder moneyHolder;

    public CoffeeMachine(IngredientsHolder ingredientsHolder, MoneyHolder moneyHolder, List<CoffeeMachineConfig> config) {
        this.ingredientsHolder = ingredientsHolder;
        this.moneyHolder = moneyHolder;
        this.configs = config;
    }

    public void doAction(Command command) {
        command.apply(moneyHolder, ingredientsHolder, configs);
    }

    public String status() {
        return String.format("The coffee machine has:\n" +
                "%s of water\n" +
                "%s of milk\n" +
                "%s of coffee beans\n" +
                "%s of disposable cups\n" +
                "%s of money", ingredientsHolder.water,ingredientsHolder.milk, ingredientsHolder.beans,
                ingredientsHolder.cups, moneyHolder.money);
    }

    private static class Ingredients {
        private final int water;
        private final int milk;
        private final int beans;
        private final int cups;

        public Ingredients(int water, int milk, int beans, int cups) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
        }
    }

    public static class CoffeeMachineConfig {
        public final CoffeeType coffeeType;
        public final int waterNeeded;
        public final int milkNeeded;
        public final int beansNeeded;
        public final int cost;

        public CoffeeMachineConfig(CoffeeType coffeeType, int waterNeeded, int milkNeeded, int beansNeeded, int cost) {
            this.coffeeType = coffeeType;
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.beansNeeded = beansNeeded;
            this.cost = cost;
        }
    }

    public static class IngredientsHolder {
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

        public void fillUp(int water, int milk, int beans, int cups){
            this.water += water;
            this.milk += milk;
            this.beans += beans;
            this.cups += cups;
        }

        public Ingredients getIngredients(CoffeeMachineConfig config){
            this.water -= config.waterNeeded;
            this.milk -= config.milkNeeded;
            this.beans -= config.beansNeeded;
            this.cups--;
            return new Ingredients(config.waterNeeded,config.milkNeeded,config.beansNeeded,1);
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

    public enum CoffeeType {
        espresso(1), latte(2), cappuccino(3);

        public final int number;

        CoffeeType(int number) {
            this.number = number;
        }
    }

    public static class MoneyHolder {
        private int money;

        public MoneyHolder(int money) {
            this.money = money;
        }

        public int take(){
            int result = this.money;
            this.money = 0;
            return result;
        }

        public void put(int amount) {
            money += amount;
        }
    }
}
