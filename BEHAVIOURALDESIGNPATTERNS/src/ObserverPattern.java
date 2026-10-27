/*
Observer pattern is a behavioural design pattern that establishes a one to many dependency between objects
(When the subject changes state, all it's registered observers are automatically notified and updated)
 */

import java.util.*;

// Observer Pattern
interface Subscriber{
    void update(String videoTitle);
}

//Concrete Obserser
class EmailSubscriber implements Subscriber{

    private String email;

    public EmailSubscriber(String email){
        this.email = email;
        }

    @Override
    public void update(String videoTitle){
        System.out.println("Email sent to " + email + ":New video uploaded" + videoTitle);
    }
}

//Concrete Obserser
class MobileAppSubscriber implements Subscriber{

    private String username;

    public MobileAppSubscriber(String username){
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("In app notification for " + username + ": New video " + videoTitle);
    }
}

//Subject
interface Channel{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

//Concrete Subject
class YoutubeChannel implements Channel{
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YoutubeChannel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        for(Subscriber subscriber : subscribers){
            subscriber.update(videoTitle);
        }
    }

    public void uploadVideo(String videoTitle){
        System.out.println(channelName + "uploaded:" + videoTitle);
        notifySubscribers(videoTitle);
    }
}

//Client
public class ObserverPattern {
   public static void main(String[] args) {
        YoutubeChannel tuf = new YoutubeChannel("ABC");
        tuf.subscribe(new MobileAppSubscriber("kishore"));
        tuf.subscribe(new MobileAppSubscriber("kishore"));
        tuf.uploadVideo("Testing video");
    }
}

/*
                                    Youtube Channel
                                        |
Email                                  Mobile                           Other
Sub                                    Sub                              Sub

Channel uploads a video -> notify all subs -> Email and mobile notification
(Youtube Channel does not need to know the concrete implementation of each subscriber
It only knows subscriber interface.

An observer pattern may trigger another operation
subject -> obserer A -> Another subject -> Observer B
(This creates different event chains)
 */