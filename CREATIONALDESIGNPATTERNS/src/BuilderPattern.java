/*
 * Builder Design Pattern - Creational Design Pattern
 *
 * Used to construct complex objects step by step.
 * Separates object construction from its representation.
 */

class BurgerMeal {

    // Immutable fields
    private final String bunType;
    private final String patty;
    private final boolean hasCheese;
    private final String side;
    private final String toppings;

    // Private constructor accepts Builder object
    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.side = builder.side;
        this.toppings = builder.toppings;
    }

    // Getters to access private fields
    public String getBunType() {
        return bunType;
    }

    public String getPatty() {
        return patty;
    }

    public boolean hasCheese() {
        return hasCheese;
    }

    public String getSide() {
        return side;
    }

    public String getToppings() {
        return toppings;
    }

    // Static nested Builder class
    public static class BurgerBuilder {

        // Mandatory parameters
        private final String bunType;
        private final String patty;

        // Optional parameters
        private boolean hasCheese;
        private String side;
        private String toppings;

        // Constructor for mandatory parameters
        public BurgerBuilder(String bunType, String patty) {
            if (bunType == null || bunType.isBlank() ||
                    patty == null || patty.isBlank()) {
                throw new IllegalArgumentException(
                        "Bun type and patty are required"
                );
            }

            this.bunType = bunType;
            this.patty = patty;
        }

        // Optional method to add cheese
        public BurgerBuilder withCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this;
        }

        // Optional method to add side
        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        // Optional method to add toppings
        public BurgerBuilder withToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        // Creates the final immutable BurgerMeal object
        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
}

// Client class
public class BuilderPattern {

    public static void main(String[] args) {

        // Meal with mandatory parameters only
        BurgerMeal meal1 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .build();

        // Meal with cheese
        BurgerMeal meal2 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .withCheese(true)
                .build();

        // Meal with side
        BurgerMeal meal3 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .withSide("Fries")
                .build();

        // Meal with all optional parameters
        BurgerMeal meal4 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .withCheese(true)
                .withSide("Fries")
                .withToppings("Lettuce")
                .build();

        // Display meal details using getters
        System.out.println("Bun Type: " + meal4.getBunType());
        System.out.println("Patty: " + meal4.getPatty());
        System.out.println("Cheese: " + meal4.hasCheese());
        System.out.println("Side: " + meal4.getSide());
        System.out.println("Toppings: " + meal4.getToppings());
    }
}
