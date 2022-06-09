package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public enum CofeeMachineState {
        NONE, EXIT,WAIT, REMAINING, BUY, WAITBUY, FILL, FILLWATER, FILLMILK, FILLCOFFEE, FILLCUPS, TAKE
    }
    int water,milk,coffee,cups,money;
    CofeeMachineState state;
    public CoffeeMachine (int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        this.state = CofeeMachineState.NONE;
    }

    public void remaining() {
        System.out.println(this.water + " ml of water");
        System.out.println(this.milk + " ml of milk");
        System.out.println(this.coffee + " g of coffee beans");
        System.out.println(this.cups + " disposable cups");
        System.out.println("$" + this.money + " of money");
    }

    public void buyCoffee (int water, int milk, int coffee, int money) {
        if (water > this.water) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (milk > this.milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (coffee > this.coffee) {
            System.out.println("Sorry, not enough coffee!");
            return;
        }
        if (this.cups < 1) {
            System.out.println("Sorry, not enough cups!");
            return;
        }
        this.water -= water;
        this.milk -= milk;
        this.coffee -= coffee;
        this.cups -= 1;
        this.money += money;
        System.out.println("I have enough resources, making you a coffee!");
    }

    public void action(String action) {
        if (this.state == CofeeMachineState.WAIT) {
            this.state = CofeeMachineState.valueOf(action.toUpperCase());
        }
        switch (this.state) {
            case REMAINING:
                this.remaining();
                this.state = CofeeMachineState.NONE;
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                this.state = CofeeMachineState.WAITBUY;
                break;
            case WAITBUY:
                switch (action) {
                    case "1":
                        this.buyCoffee(250,0,16,4);
                        break;
                    case "2":
                        this.buyCoffee(350,75,20,7);
                        break;
                    case "3":
                        this.buyCoffee(200,100,12,6);
                        break;
                }
                this.state = CofeeMachineState.NONE;
                break;
            case FILL:
                System.out.println("Write how many ml of water you want to add:");
                this.state = CofeeMachineState.FILLWATER;
                break;
            case FILLWATER:
                this.water += Integer.parseInt(action);
                System.out.println("Write how many ml of milk you want to add:");
                this.state = CofeeMachineState.FILLMILK;
                break;
            case FILLMILK:
                this.milk += Integer.parseInt(action);
                System.out.println("Write how many grams of coffee beans you want to add:");
                this.state = CofeeMachineState.FILLCOFFEE;
                break;
            case FILLCOFFEE:
                this.coffee += Integer.parseInt(action);
                System.out.println("Write how many disposable cups of coffee you want to add:");
                this.state = CofeeMachineState.FILLCUPS;
                break;
            case FILLCUPS:
                this.cups += Integer.parseInt(action);
                this.state = CofeeMachineState.NONE;
                break;
            case TAKE:
                System.out.println("I gave you $" + this.money);
                this.money = 0;
                this.state = CofeeMachineState.NONE;
                break;
        }
    }
    public void setWaiting () {
        if (this.state == CofeeMachineState.NONE) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            this.state = CofeeMachineState.WAIT;
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);
        while (coffeeMachine.state != CofeeMachineState.EXIT) {
            coffeeMachine.setWaiting();
            coffeeMachine.action(scanner.next());
        }
    }
}
