/*
Decorator is a structural design pattern that allows you to add new behavior or responsibilities to an object dynamically
at runtime without modifying its original class.
 */

//Component
interface Pizza{
    String getDescription();
    String getCost();
}

//Concrete Component
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

//Decorator Abstract Class
abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

//Concrete Decorator - Extra cheese
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

//Concrete Decorator - Olives
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

//Concrete Decorator - StuffedCrust
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

//Main
public class DecoratorPattern {
    static void main(String[] args) {
        //Basic pizza
        Pizza margherita = new MargheritaPizza();
        System.out.println(margherita.getDescription() + " =rs " + margherita.getCost());
        System.out.println();
        //Margherita + Extracheese
        Pizza cheesePizza = new ExtraCheese(new MargheritaPizza());
        System.out.println(cheesePizza.getDescription() + " =rs " + cheesePizza.getCost());
        System.out.println();
        //Margherita + Extracheese + Olive
        Pizza cheeseOlivePizza = new Olives(new ExtraCheese(new MargheritaPizza()));
        System.out.println(cheeseOlivePizza.getDescription() + " =rs " + cheeseOlivePizza.getCost());
        System.out.println();
        //Margherita + Extracheese + Olive + Stuffed Crust
        Pizza fullyLoadedPizza = new StuffedCrust(new Olives(new ExtraCheese(new MargheritaPizza())));
        System.out.println(fullyLoadedPizza.getDescription() + " =rs " + fullyLoadedPizza.getCost());
    }
}

/*
Pros:
1. Follows open/closed principle.
2. Avoids subclass explosion.
3. Runtime flexibility.
4. Single responsibility principle.
5. Composable behavior ( Structure is flexible )

Cons:
1. Can result in small class.
2. Debugging can become difficult.
3. Ordering can matter.
4. Can make object creation harder to read.

When to use:
1. You need to add responsibilities dynamically.
2. You want to avoid subclass explosion.
   Base object
   + Feature A
   + Feature B
   + Feature C
3. Features can be combined
   Coffee
   |- Milk
   |- Sugar
   |- Whipped down
   |- Caramel
4. You want layered behavior

When to avoid:
1. Behavior is fixed.
2. You only have a few simple variations.
3. Decorator chain becomes extremely complex.
4. Behavior has to be tightly coupled.
 */