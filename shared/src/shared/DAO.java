package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface DAO extends Remote {
	UserDTO create(int id, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] img) throws RemoteException;
	Collection<UserDTO> readAll() throws RemoteException;
	void update(UserDTO user) throws RemoteException;
	void delete(UserDTO user) throws RemoteException;
	UserDTO read(int id) throws RemoteException;
	UserDTO read(String username) throws RemoteException;
}
