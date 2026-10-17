interface Pizza{
    String getDescription();
    String getCost();
}

class MargheritaPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public String getCost() {
        return "200.0";
    }
}

abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

class ExtraCheese extends PizzaDecorator{
    public ExtraCheese(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public String getCost() {
        return pizza.getCost() + 40.0;
    }
}

class Olives extends PizzaDecorator{
    public Olives(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public String getCost() {
        return pizza.getCost() + 50.0;
    }
}

class StuffedCrust extends PizzaDecorator{
    public StuffedCrust(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Stuffed Crust";
    }

    @Override
    public String getCost() {
        return pizza.getCost() + 80.0;
    }
}

public class DecoratorPattern {
    static void main(String[] args) {
        Pizza margherita = new MargheritaPizza();
        System.out.println(margherita.getDescription() + " =rs " + margherita.getCost());
        System.out.println();
        Pizza cheesePizza = new ExtraCheese(new MargheritaPizza());
        System.out.println(cheesePizza.getDescription() + " =rs " + cheesePizza.getCost());
        System.out.println();
        Pizza cheeseOlivePizza = new Olives(new ExtraCheese(new MargheritaPizza()));
        System.out.println(cheeseOlivePizza.getDescription() + " =rs " + cheeseOlivePizza.getCost());
        System.out.println();
        Pizza fullyLoadedPizza = new StuffedCrust(new Olives(new ExtraCheese(new MargheritaPizza())));
        System.out.println(fullyLoadedPizza.getDescription() + " =rs " + fullyLoadedPizza.getCost());
    }
}
