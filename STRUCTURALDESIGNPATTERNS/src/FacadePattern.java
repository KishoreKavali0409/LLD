/*
This is a structural design pattern that provides a simplified, unified interface to a set of interface to a set
of interfaces in a subsystems

Facade = One simple interface hiding multiple complex subsystem operations
 */

class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of" + amount +
                "Successful for account " + accountId);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat" + seatNumber +
                "Reserved for movie" + movieId);
    }
}

class NotificationService{
    public void sendBooking(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointsService{
    public void addPoints(String accountId, int Points){
        System.out.println(Points + "Loyalty points added to account " + accountId);
    }
}

class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Ticket generated for movie" + movieId +
                ", Seat:" + seatNumber);
    }
}

//CODE Looks without facade
/*
public class FacadePattern {
    public static void main(String[] args) {

        PaymentService payment = new PaymentService();
        SeatReservationService seat = new SeatReservationService();
        TicketService ticket = new TicketService();
        LoyaltyPointsService loyalty = new LoyaltyPointsService();
        NotificationService notification = new NotificationService();

        payment.makePayment("ACC101", 250);

        seat.reserveSeat("MOVIE101", "A10");

        ticket.generateTicket("MOVIE101", "A10");

        loyalty.addPoints("ACC101", 50);

        notification.sendBooking("kishore@gmail.com");

        System.out.println("Movie ticket booking completed successfully");
    }

 */
//FACADE
class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;

    public MovieBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService =  new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(
            String accountId,
            String movieId,
            String seatNumber,
            String userEmail,
            double amount){

        // Make payment
        paymentService.makePayment(accountId, amount);
        // Reserve seat
        seatReservationService.reserveSeat(movieId, seatNumber);
        //Generate Ticket
        ticketService.generateTicket(movieId, seatNumber);
        // Add loyalty points
        loyaltyPointsService.addPoints(accountId, 50);
        // Send Confirmation
        notificationService.sendBooking(userEmail);
        System.out.println("Movie ticket booking completed successfully");

    }
}

//Client code

public class FacadePattern {
    static void main(String[] args) {
        MovieBookingFacade facade = new MovieBookingFacade();
        facade.bookMovieTicket("ACC101",
                "MOVIE101",
                "A10",
                        "kishore@gmail.com",
                250);
    }
}
/*
Pros:
1. Lightweight coupling
2. Flexibility
3. Simplifies client design
4. Promotes layered architecture
5. Better flexibility

Cons:
1. Possible coupling (The facade itself becomes coupled to the sub system class)
2. Hidden complexity (The client doesn't see what is happening internally which can sometimes make behavior less obvious)
3. Runtime errors (If one of the underlying subsystem operations fails, the error may surface only when the facade operation executes)
4. Difficult to trace
5. SRP Violation

When to use:
1. You have complex system
2. You want to reduce client coupling
3. You want clean API
4. You are creating application/service boundaries

When to avoid:
1. The subsystem is already simple.
2. Facade becomes a god class
3. Clients genuinely need fine-grained control
 */