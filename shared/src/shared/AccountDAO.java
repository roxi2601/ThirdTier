package shared;

import java.rmi.RemoteException;
import java.util.Collection;

public interface AccountDAO
{
  AccountDTO createAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName,
      String description, byte[] pictureBytes) throws RemoteException;
  Collection<AccountDTO> readAllAccounts() throws RemoteException;
  void update(AccountDTO user) throws RemoteException;
  void delete(AccountDTO user) throws RemoteException;
  AccountDTO readAccount(int id) throws RemoteException;
  AccountDTO readAccount(String username) throws RemoteException;
}
