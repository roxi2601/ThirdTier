package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote {
    int getUserId() throws RemoteException;
    String getUsername() throws RemoteException;
    String getPassword() throws RemoteException;
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;
    String getDescription() throws RemoteException;
    byte[] getImg() throws RemoteException;
    int getSecurityLevel() throws RemoteException;
}