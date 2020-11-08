import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Base extends Remote {
	User registerUser(int id, String username, String password, int securityLevel) throws RemoteException;
	User getUser(int id) throws RemoteException;
	List<User> getAllUsers() throws RemoteException;
	void removeUser(User user) throws RemoteException;
}
