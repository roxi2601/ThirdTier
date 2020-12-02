package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account  {
    int getUserId();
    String getUsername() ;
    String getPassword() ;
    String getFirstName() ;
    String getLastName() ;
    String getDescription() ;
    byte[] getPictureBytes() ;
    int getSecurityLevel();
}