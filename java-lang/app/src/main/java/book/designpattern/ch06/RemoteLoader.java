package book.designpattern.ch06;

public class RemoteLoader {
  public static void main(String[] args) {
    RemoteControl remoteControl = new RemoteControl();

    Light livingRoomLight = new Light("Living Room");
    Light kitchenLight = new Light("Kitchen");
    GarageDoor garageDoor = new GarageDoor("Garage");
    Stereo stereo = new Stereo("Living Room");
    CeilingFan ceilingFan = new CeilingFan("Living Room");

    LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
    LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
    LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
    LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);

    GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
    GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

    StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
    StereoOffCommand stereoOff = new StereoOffCommand(stereo);

    CeilingFanHighCommand ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
    CeilingFanMediumCommand ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
    CeilingFanLowCommand ceilingFanLowCommand = new CeilingFanLowCommand(ceilingFan);
    CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);

    remoteControl.setCommand(0, ceilingFanHighCommand, ceilingFanOffCommand);
    remoteControl.setCommand(1, ceilingFanMediumCommand, ceilingFanOffCommand);
    remoteControl.setCommand(2, ceilingFanLowCommand, ceilingFanOffCommand);

    remoteControl.onButtonWasPushed(0);
    remoteControl.onButtonWasPushed(1);
    remoteControl.undoButtonWasPushed();
    remoteControl.onButtonWasPushed(0);
  }
}
