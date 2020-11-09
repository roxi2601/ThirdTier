import java.rmi.RemoteException;
import java.util.*;

public class BaseImplementation {
	private Map<String, Remote> users = new HashMap<>();
	private DAO dao;

	public BaseImplementation(DAO dao) {
		this.dao = dao;
	}
	
	public User registerUser(int id, String username, String password, int securityLevel) throws RemoteException {
		DTO dto = dao.create(id, username, password, securityLevel);
		Remote user = new Remote(dto);
		users.put(username, user);
		return user;
	}
	

	public User getUser(String username) throws RemoteException {
		if (!users.containsKey(username)) {
			users.put(username, new Remote(dao.read(username)));
		}
		return users.get(username);
	}

	public List<User> getAllUsers() throws RemoteException {
		Collection<DTO> allUsers = dao.readAll();
		LinkedList<User> list = new LinkedList<User>();
		for(DTO user: allUsers) {
			if (!users.containsKey(user.getUserName())) {
				users.put(user.getUserName(), new Remote(user));
			}
			list.add(users.get(user.getUserName()));
		}
		return list;
	}

	public void removeUser(User user) throws RemoteException {
		dao.delete(new DTO(user));
		users.remove(user.getUserName());
	}
}
