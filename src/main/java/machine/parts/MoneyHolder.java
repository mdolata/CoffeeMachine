package machine.parts;

public class MoneyHolder {
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

    public int getMoney() {
        return money;
    }
}