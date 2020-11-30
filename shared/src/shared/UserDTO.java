package shared;

import java.io.Serializable;
import java.rmi.RemoteException;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String username;
	private String password;
	private int securityLevel;


	public UserDTO(int id, String username, String password, int securityLevel) {
		this.userId = id;
		this.username = username;
		this.password = password;
		this.securityLevel = securityLevel;

	}

	public UserDTO(User user) throws RemoteException {
		this(user.getId(), user.getUserName(), user.getPassword(),
				user.getSecurityLevel());
	}

	public int getId() {
		return userId;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public String getUsername() {
		return username;
	}

}