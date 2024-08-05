package book.designpattern.ch10;

import java.rmi.Remote;
import java.rmi.RemoteException;
import book.designpattern.ch10.state.State;

public interface GumballMachineRemote extends Remote {
  public int getCount() throws RemoteException;
  public String getLocation() throws RemoteException;
  public State getState() throws RemoteException;
}
