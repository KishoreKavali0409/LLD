interface Logistics{
    void send();
}
//concrete classes
class Road implements Logistics{
    @Override
    public void send() {
        System.out.println("Sending out item by road");
    }
}

class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending By flight");
    }
}

class Train implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending By Train");
    }
}

//Factory class
class LogisticsFactory{
    //Exposes a method that takes a mode and returns the interface type
    public static Logistics getLogistics(String mode){
        if(mode.equals("road")){
            return new Road();
        }else if(mode.equals("air")){
            return new Air();
        }else{
            return new Train();
        }
    }
}

public class FactoryPattern {
    static void main(String[] args) {
        String mode = "road";
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}
