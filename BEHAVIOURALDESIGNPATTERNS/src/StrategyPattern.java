/*
Strategy Pattern is a behavioural design pattern that defines a family of algorithms, encapsulates each algorithm
in a separate class, and makes them interchangable at runtime
 */

interface MatchingStrategy{
    void match(String riderLocation);
}

//concrete strategy 1
class NearestDriverStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching with the nearest available driver" + " to" + riderLocation);
    }
}

//concrete strategy 2
class AirportQueueStratrgy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using FIFO AirportQueue for" + riderLocation);
    }
}

//concrete strategy 3
class SurgePriorityStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using surge priority near" + riderLocation);
    }
}

//context
class RideMatchingService{
    private MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }
    public void matchRider(String location){
        strategy.match(location);
    }
}

//client
public class StrategyPattern {
    static void main(String[] args) {
        RideMatchingService rideMatchingService = new RideMatchingService(new NearestDriverStrategy());
        rideMatchingService.matchRider("Hi tech city");
        rideMatchingService.setStrategy(new AirportQueueStratrgy());
        rideMatchingService.matchRider("Airport");
        rideMatchingService.setStrategy(new SurgePriorityStrategy());
        rideMatchingService.matchRider("Banjara hills");
    }
}
