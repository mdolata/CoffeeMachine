package machine.domain;

public class Ingredients {
    public final int water;
    public final int milk;
    public final int beans;
    public final int cups;

    public Ingredients(int water, int milk, int beans, int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
    }

    public Ingredients fillWith(Ingredients ingredients) {
        return new Ingredients(this.water + ingredients.water,
                this.milk + ingredients.milk,
                this.beans + ingredients.beans,
                this.cups + ingredients.cups);
    }

    public Ingredients remove(Ingredients ingredients) {
        return new Ingredients(this.water - ingredients.water,
                this.milk - ingredients.milk,
                this.beans - ingredients.beans,
                this.cups - ingredients.cups);
    }
}