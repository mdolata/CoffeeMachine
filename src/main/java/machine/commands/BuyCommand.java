package machine.commands;

import machine.CoffeeMachine;

import java.util.*;

public class BuyCommand implements Command{
        private final Scanner scanner;

        public BuyCommand(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void apply(CoffeeMachine.MoneyHolder moneyHolder, CoffeeMachine.IngredientsHolder ingredientsHolder, List<CoffeeMachine.CoffeeMachineConfig> configs) {
            String input = scanner.nextLine();

            if ("back".equals(input)) {
                return;
            }
            int inputCoffeeType = Integer.parseInt(input);

            CoffeeMachine.CoffeeType coffeeType = Arrays.stream(CoffeeMachine.CoffeeType.values())
                    .filter(coffeeType2 -> inputCoffeeType == coffeeType2.number)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);


            CoffeeMachine.CoffeeMachineConfig theConfig = configs.stream()
                    .filter(config -> config.coffeeType.equals(coffeeType))
                    .findFirst()
                    .get();

            Set<String> isValid = validate(ingredientsHolder, theConfig);

            if (isValid.isEmpty()) {
                System.out.println("I have enough resources, making you a coffee!");
                moneyHolder.put(theConfig.cost);
                ingredientsHolder.getIngredients(theConfig);
            } else {
                String notValid = String.join(", ", isValid);
                System.out.println(String.format("Sorry, not enough %s!", notValid));
            }
        }

        private Set<String> validate(CoffeeMachine.IngredientsHolder ingredientsHolder, CoffeeMachine.CoffeeMachineConfig config) {
            Set<String> notValidHolders = new LinkedHashSet<>();

            if (ingredientsHolder.getWater() < config.waterNeeded) {
                notValidHolders.add("water");
            } else if (ingredientsHolder.getMilk() < config.milkNeeded) {
                notValidHolders.add("milk");
            } else if (ingredientsHolder.getBeans() < config.beansNeeded) {
                notValidHolders.add("beans");
            } else if (ingredientsHolder.getCups() < 1) {
                notValidHolders.add("cups");
            }

            return notValidHolders;
        }
    }