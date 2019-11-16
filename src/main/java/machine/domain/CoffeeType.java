package machine.domain;

public enum CoffeeType {
    espresso(1), latte(2), cappuccino(3);

    public final int number;

    CoffeeType(int number) {
        this.number = number;
    }
}