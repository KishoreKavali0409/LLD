//Client should not be forced tp depend on interfaces they don't use
/*
- Don't create large, bloated interfaces
- Break them into smaller and more specific ones
Ex: UBER
 */
interface RiderInterface{
    void bookRide();
    void payRide();
}

interface DriverInterface{
    void acceptRide();
    void drive();
    void endRide();
}

class Rider implements RiderInterface {

    public void bookRide() {
        System.out.println("Rider booked a ride");
    }

    public void payRide() {
        System.out.println("Rider paid for the ride");
    }
}

class Driver implements DriverInterface{
    public void acceptRide() {
        System.out.println("Driver accepted the ride");
    }
    public void drive() {
        System.out.println("Driving the car");
    }
    public void endRide() {
        System.out.println("Driver ended the ride");
    }
}

public class InterfaceSegregationPrinciple {
    static void main(String[] args) {
        Rider rider = new Rider();
        rider.bookRide();
        rider.payRide();

        Driver driver = new Driver();
        driver.acceptRide();
        driver.drive();
        driver.endRide();
    }
}
/*
Benefits:
- Modularity & Flexibility
- Improves testability(easy to make smaller interfaces)
- Prevents accidental implementation of irrelevant methods
- Makes code easier to understand

When to apply:
- When your interface is doing too many things
- When some classes implementing an interface throw errors
 */
