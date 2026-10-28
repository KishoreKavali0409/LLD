interface MatchingStrategy{
    void match(String riderLocation);
}

class NearestDriverStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching with the nearest available driver" + " to" + riderLocation);
    }
}
class AirportQueueStratrgy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using FIFO AirportQueue for" + riderLocation);
    }
}
class SurgePriorityStrategy implements MatchingStrategy{
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using surge priority near" + riderLocation);
    }
}
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
