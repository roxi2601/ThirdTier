package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface UserDAO extends Remote {
	UserDTO createUser(int userId, String username, String password, int securityLevel) throws RemoteException;
	Collection<UserDTO> readAllUsers() throws RemoteException;
	UserDTO readUser(int id) throws RemoteException;
	UserDTO readUser(String username) throws RemoteException;
	UserDTO updateUser(int userId, String username, String password, int securityLevel) throws RemoteException;
	void deleteUser(int userId) throws RemoteException;

}
