package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User extends Remote {
    int getId() throws RemoteException;
    String getUserName() throws RemoteException;
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;
    String getDescription() throws RemoteException;
    String getPassword() throws RemoteException;
    byte[] getImg() throws RemoteException;
    int getSecurityLevel() throws RemoteException;
}
