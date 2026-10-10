class BurgerMeal {

    private final String bunType;
    private final String patty;
    private final Boolean hasCheese;
    private final String side;
    private final String toppings;

    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.side = builder.side;
        this.toppings = builder.toppings;
    }

    public static class BurgerBuilder {
        private final String bunType;
        private final String patty;
        private Boolean hasCheese;
        private String side;
        private String toppings;

        public BurgerBuilder(String bunType, String patty) {
            this.bunType = bunType;
            this.patty = patty;
        }

        public BurgerBuilder WithCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerBuilder withToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
    @Override
    public String toString(){
        return "BurgerMeal{" +
                "bunType='" + bunType + '\'' +
                ", patty='" + patty + '\'' +
                ", hasCheese=" + hasCheese +
                ", side='" + side + '\'' +
                ", toppings='" + toppings + '\'' +
                '}';
    }
}
public class BuilderPattern {
    public static void main(String[] args) {
        BurgerMeal meal1 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .build();

        BurgerMeal meal2 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .WithCheese(true)
                .build();

        BurgerMeal meal3 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .withSide("Fries")
                .build();

        BurgerMeal meal4 = new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                .WithCheese(true)
                .withSide("Fries")
                .withToppings("Lettuce")
                .build();

        System.out.println(meal1);
        System.out.println(meal2);
        System.out.println(meal3);
        System.out.println(meal4);
    }
}
