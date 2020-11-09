import java.rmi.RemoteException;
import java.util.List;

public class RemoteBase implements Base {
	private final BaseImplementation implementation;

	public RemoteBase(DAO dao) {
		implementation = new BaseImplementation(dao);
	}
	
	@Override
	public User registerUser(int id, String username, String password, int securityLevel) throws RemoteException {
		return implementation.registerUser(id, username, password, securityLevel);
	}
	

	@Override
	public User getUser(String username) throws RemoteException {
		return implementation.getUser(username);
	}

	@Override
	public List<User> getAllUsers() throws RemoteException {
		return implementation.getAllUsers();
	}

	@Override
	public void removeUser(User user) throws RemoteException {
		implementation.removeUser(user);
	}
}
