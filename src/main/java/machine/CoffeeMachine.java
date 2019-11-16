package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private final int waterAmount;
    private final int milkAmount;
    private final int beanAmount;
    private final CoffeeMachineConfig config;

    public CoffeeMachine(int waterAmount, int milkAmount, int beanAmount, CoffeeMachineConfig config) {
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.beanAmount = beanAmount;
        this.config = config;
    }

    public static void main(String[] args) {
        CoffeeMachineConfig config = new CoffeeMachineConfig(200, 50, 15);
        Scanner scanner = new Scanner(System.in);

//        System.out.println("podaj dane");
        int waterAmount = scanner.nextInt();
        int milkAmount = scanner.nextInt();
        int beanAmount = scanner.nextInt();
        int coffeesCount = scanner.nextInt();

        Ingredients ingredients = calculateNeededIngredients(coffeesCount, config);
        CoffeeMachine coffeeMachine = new CoffeeMachine(waterAmount, milkAmount, beanAmount, config);

        int calculatedCoffeesAmount = coffeeMachine.howManyCoffeesCanBeMake(ingredients);

        String message = "";

        if (calculatedCoffeesAmount == coffeesCount) {
            message = "Yes, I can make that amount of coffee";
        } else if (calculatedCoffeesAmount > coffeesCount) {
            message = String.format("Yes, I can make that amount of coffee (and even %s more than that)", (calculatedCoffeesAmount - coffeesCount));
        } else {
            message = String.format("No, I can make only %s cup(s) of coffee", calculatedCoffeesAmount);
        }

        System.out.println(message);
    }

    private int howManyCoffeesCanBeMake(Ingredients ingredients) {
        int water = maximumCoffeesOf(waterAmount, config.waterNeeded);
        int milk = maximumCoffeesOf(milkAmount, config.milkNeeded);
        int beans = maximumCoffeesOf(beanAmount, config.beansNeeded);

        return min(water,milk, beans);
    }

    private int min(int a, int b, int c) {
        return Integer.min(a, Integer.min(b, c));
    }

    private int maximumCoffeesOf(int amountInCoffeeMachine, int coffeeNeeds) {
        return amountInCoffeeMachine / coffeeNeeds;
    }

    private static void printStatus(int coffeesCount, Ingredients ingredients) {
        System.out.println(String.format("For %s cups of coffee you will need:", coffeesCount));
        System.out.println(String.format("%s ml of water", ingredients.water));
        System.out.println(String.format("%s ml of milk", ingredients.milk));
        System.out.println(String.format("%s g of coffee beans", ingredients.beans));
    }

    private static Ingredients calculateNeededIngredients(int coffeesCount, CoffeeMachineConfig config) {
        return new Ingredients(coffeesCount, config);
    }

    private String makeCoffee() {
        return "Starting to make a coffee" + "\n" +
        "Grinding coffee beans" + "\n" +
        "Boiling water" +  "\n" +
        "Mixing boiled water with crushed coffee beans" +  "\n" +
        "Pouring coffee into the cup" +  "\n" +
        "Pouring some milk into the cup" + "\n" +
        "Coffee is ready!";
    }

    private static class Ingredients {
        private final int water;
        private final int milk;
        private final int beans;

        public Ingredients(int coffeesCount, CoffeeMachineConfig config) {
            water = config.waterNeeded * coffeesCount;
            milk = config.milkNeeded * coffeesCount;
            beans = config.beansNeeded * coffeesCount;
        }
    }

    private static class CoffeeMachineConfig {

        private final int waterNeeded;
        private final int milkNeeded;
        private final int beansNeeded;

        public CoffeeMachineConfig(int waterNeeded, int milkNeeded, int beansNeeded) {
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.beansNeeded = beansNeeded;
        }
    }
}
