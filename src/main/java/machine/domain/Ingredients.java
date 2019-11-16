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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredients that = (Ingredients) o;

        if (water != that.water) return false;
        if (milk != that.milk) return false;
        if (beans != that.beans) return false;
        return cups == that.cups;
    }

    @Override
    public int hashCode() {
        int result = water;
        result = 31 * result + milk;
        result = 31 * result + beans;
        result = 31 * result + cups;
        return result;
    }
}