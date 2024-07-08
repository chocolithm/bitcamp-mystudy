package book.designpattern.ch06;

public class RemoteLoader {
  public static void main(String[] args) {
    RemoteControl remoteControl = new RemoteControl();

    Light livingRoomLight = new Light("Living Room");
    Light kitchenLight = new Light("Kitchen");
    GarageDoor garageDoor = new GarageDoor("Garage");
    Stereo stereo = new Stereo("Living Room");

    LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
    LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
    LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
    LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);

    GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
    GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

    StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
    StereoOffCommand stereoOff = new StereoOffCommand(stereo);

    remoteControl.setCommand(0, livingRoomLightOnCommand, livingRoomLightOffCommand);
    remoteControl.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);
    remoteControl.setCommand(2, garageDoorUp, garageDoorDown);
    remoteControl.setCommand(3, stereoOnWithCD, stereoOff);

    System.out.println(remoteControl);

    remoteControl.onButtonWasPushed(0);
    remoteControl.offButtonWasPushed(0);
    System.out.println(remoteControl);
    remoteControl.undoButtonWasPushed();
    remoteControl.offButtonWasPushed(0);
    remoteControl.onButtonWasPushed(0);
    System.out.println(remoteControl);
    remoteControl.undoButtonWasPushed();
  }
}
