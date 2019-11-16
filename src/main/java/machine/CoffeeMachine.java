package machine;

import java.util.*;

public class CoffeeMachine {
    private final int waterAmount;
    private final int milkAmount;
    private final int beanAmount;
    private final List<CoffeeMachineConfig> configs;
    private final IngredientsHolder ingredientsHolder;
    private final MoneyHolder moneyHolder;

    public CoffeeMachine(IngredientsHolder ingredientsHolder, MoneyHolder moneyHolder, List<CoffeeMachineConfig> config) {
        this.ingredientsHolder = ingredientsHolder;
        this.moneyHolder = moneyHolder;
        this.waterAmount = ingredientsHolder.water;
        this.milkAmount = ingredientsHolder.milk;
        this.beanAmount = ingredientsHolder.beans;
        this.configs = config;
    }

    public static void main(String[] args) {
        CoffeeMachineConfig espresso = new CoffeeMachineConfig(CoffeeType.espresso, 250, 0, 16, 4);
        CoffeeMachineConfig latte = new CoffeeMachineConfig(CoffeeType.latte, 350, 75, 20, 7);
        CoffeeMachineConfig cappuccino = new CoffeeMachineConfig(CoffeeType.cappuccino, 200, 100, 12, 6);

        List<CoffeeMachineConfig> configs = new ArrayList<>();
        configs.add(espresso);
        configs.add(latte);
        configs.add(cappuccino);

        IngredientsHolder ingredientsHolder = new IngredientsHolder(1200, 540, 120, 9);
        MoneyHolder moneyHolder = new MoneyHolder(550);

        CoffeeMachine coffeeMachine = new CoffeeMachine(ingredientsHolder, moneyHolder, configs);

        String status = coffeeMachine.status();
        System.out.println(status);

        Scanner scanner = new Scanner(System.in);

        System.out.println("akcja!");
        String action = scanner.nextLine();

        Command command = CommandFactory.get(action, scanner);
        coffeeMachine.doAction(command);

        status = coffeeMachine.status();
        System.out.println(status);
    }

    private void doAction(Command command) {
        command.apply(moneyHolder, ingredientsHolder, configs);
    }

    private String status() {
        return String.format("The coffee machine has:\n" +
                "%s of water\n" +
                "%s of milk\n" +
                "%s of coffee beans\n" +
                "%s of disposable cups\n" +
                "%s of money", ingredientsHolder.water,ingredientsHolder.milk, ingredientsHolder.beans,
                ingredientsHolder.cups, moneyHolder.money);
    }

    private int howManyCoffeesCanBeMake() {
        CoffeeMachineConfig config = configs.get(0);
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
        private final int cups;

        public Ingredients(int water, int milk, int beans, int cups) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
        }
    }

    private static class CoffeeMachineConfig {
        private final CoffeeType coffeeType;
        private final int waterNeeded;
        private final int milkNeeded;
        private final int beansNeeded;
        private final int cost;

        public CoffeeMachineConfig(CoffeeType coffeeType, int waterNeeded, int milkNeeded, int beansNeeded, int cost) {
            this.coffeeType = coffeeType;
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.beansNeeded = beansNeeded;
            this.cost = cost;
        }
    }

    private static class IngredientsHolder {
        private int water;
        private int milk;
        private int beans;
        private int cups;

        private IngredientsHolder(int water, int milk, int beans, int cups) {
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
    }

    private static enum CoffeeType {
        espresso(1), latte(2), cappuccino(3);

        private final int number;

        CoffeeType(int number) {
            this.number = number;
        }
    }

    private static class MoneyHolder {
        private int money;

        private MoneyHolder(int money) {
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

    private static interface Command {
        public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs);
    }

    private static class CommandFactory {
        public static Command get(String action, Scanner scanner) {
            Command command = null;

            switch (action) {
                case "buy" : command = new BuyCommand(scanner); break;
                case "take" : command = new TakeCommand(scanner); break;
                case "fill" : command = new FillCommand(scanner); break;
            }
            return command;
        }


    }
    private static class BuyCommand implements Command{
        private final Scanner scanner;

        public BuyCommand(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
            int inputCoffeeType = scanner.nextInt();
            CoffeeType coffeeType = Arrays.stream(CoffeeType.values())
                    .filter(coffeeType2 -> inputCoffeeType == coffeeType2.number)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            configs.stream().filter(config -> config.coffeeType.equals(coffeeType))
                    .peek(config -> moneyHolder.put(config.cost))
                    .peek(ingredientsHolder::getIngredients)
                    .count();
        }
    }

    private static class TakeCommand implements Command {
        private final Scanner scanner;

        public TakeCommand(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
            int take = moneyHolder.take();

            //todo print there is not fine
            System.out.println(String.format("I gave you $%s", take));
        }
    }

    private static class FillCommand implements Command {
        private final Scanner scanner;

        public FillCommand(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void apply(MoneyHolder moneyHolder, IngredientsHolder ingredientsHolder, List<CoffeeMachineConfig> configs) {
            int waterAdd = scanner.nextInt();
            int milkAdd = scanner.nextInt();
            int beansAdd = scanner.nextInt();
            int cupsAdd = scanner.nextInt();

            ingredientsHolder.fillUp(waterAdd,milkAdd, beansAdd,cupsAdd);
        }
    }
}
