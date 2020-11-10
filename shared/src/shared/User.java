package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User extends Remote {
    int getId() throws RemoteException;
    String getUsername() throws RemoteException;
    String getPassword() throws RemoteException;
    int getSecurityLevel() throws RemoteException;
}
