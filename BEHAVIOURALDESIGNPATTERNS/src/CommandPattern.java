/*
The command pattern is a behavioral design pattern that encapsulates a request as an object, allowing you to parameterize
clients with different a request as an object, allowing you to parameterize clients with different requests queue
or log requests, and support undoable operations.
 */
import java.util.Arrays;
class Light{
    public void on(){
        System.out.println("Light is on");
    }
    public void off(){
        System.out.println("light is off");
    }
}
class AirCondition{
    public void on(){
        System.out.println("AC is on");
    }
    public void off(){
        System.out.println("AC is off");
    }
}
interface Command{
    void execute();
    void undo();
}
class LightOnCommand implements Command{
    private Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
class ACOnCommand implements Command{
    private AirCondition ac;
    public ACOnCommand(AirCondition ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}
class ACOffCommand implements Command{
    private AirCondition ac;
    public ACOffCommand(AirCondition ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}
class RemoteControl{
    private Command[] buttons;
    private Command lastCommand;
    public RemoteControl(int numberOfSlots){
        buttons = new Command[numberOfSlots];
    }
    public void setCommand(int slot, Command command){
        buttons[slot] = command;
    }
    public void pressButton(int slot){
        if(buttons[slot] != null){
            lastCommand = buttons[slot];
            lastCommand.execute();
        }else{
            System.out.println("No command to undo");
        }
    }
    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
            lastCommand = null;
        } else {
            System.out.println("Nothing to undo");
        }
    }
    }
public class CommandPattern {
    public static void main(String[] args) {
        Light light = new Light();
        AirCondition ac = new AirCondition();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command acOn = new ACOnCommand(ac);
        Command acOff = new ACOffCommand(ac);
        RemoteControl remote = new RemoteControl(4);
        remote.setCommand(0, lightOn);
        remote.setCommand(1, lightOff);
        remote.setCommand(2, acOn);
        remote.setCommand(3, acOff);
        remote.pressButton(0);
        remote.pressButton(2);
        remote.pressUndo();
        remote.pressButton(1);
        remote.pressUndo();
    }
}
