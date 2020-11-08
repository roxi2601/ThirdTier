import java.rmi.RemoteException;
import java.util.*;

public class BaseImplementation {
	private Map<Integer, Remote> users = new HashMap<>();
	private DAO dao;

	public BaseImplementation(DAO dao) {
		this.dao = dao;
	}
	
	public User registerUser(int id, String username, String password, int securityLevel) throws RemoteException {
		DTO dto = dao.create(id, username, password, securityLevel);
		Remote user = new Remote(dto);
		users.put(id, user);
		return user;
	}
	

	public User getUser(int id) throws RemoteException {
		if (!users.containsKey(id)) {
			users.put(id, new Remote(dao.read(id)));
		}
		return users.get(id);
	}

	public List<User> getAllUsers() throws RemoteException {
		Collection<DTO> allUsers = dao.readAll();
		LinkedList<User> list = new LinkedList<User>();
		for(DTO user: allUsers) {
			if (!users.containsKey(user.getId())) {
				users.put(user.getId(), new Remote(user));
			}
			list.add(users.get(user.getId()));
		}
		return list;
	}

	public void removeUser(User user) throws RemoteException {
		dao.delete(new DTO(user));
		users.remove(user.getId());
	}
}
