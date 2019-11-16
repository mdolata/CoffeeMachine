package machine.config;

import machine.domain.CoffeeType;

public class CoffeeTypeConfig {
    public final CoffeeType coffeeType;
    public final int waterNeeded;
    public final int milkNeeded;
    public final int beansNeeded;
    public final int cost;

    public CoffeeTypeConfig(CoffeeType coffeeType, int waterNeeded, int milkNeeded, int beansNeeded, int cost) {
        this.coffeeType = coffeeType;
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.beansNeeded = beansNeeded;
        this.cost = cost;
    }
}