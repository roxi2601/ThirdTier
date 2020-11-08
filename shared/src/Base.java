import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Base extends Remote {
	User registerUser(int id, String username, String password, int securityLevel) throws RemoteException;
	static User getUser(int id) throws RemoteException;
	static List<User> getAllUsers() throws RemoteException;
	void removeUser(User user) throws RemoteException;
}
