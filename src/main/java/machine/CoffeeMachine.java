package machine;

public class CoffeeMachine {
    public static void main(String[] args) {
        String response = new CoffeeMachine().makeCoffee();
        System.out.println(response);
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
}
