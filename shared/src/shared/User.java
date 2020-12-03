package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User {
    int getUserId() ;
    String getUsername() ;
    String getPassword() ;
    int getSecurityLevel() ;
}
