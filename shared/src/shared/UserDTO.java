package shared;

import java.io.Serializable;
import java.rmi.RemoteException;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private int securityLevel;
	private String firstName;
	private String lastName;
	private String description;
	private byte[] img;

	public UserDTO(int id, String username, String password, int securityLevel, String firstName,
				   String lastName, String description, byte[] img) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.securityLevel = securityLevel;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.img = img;
	}

	public UserDTO(User user) throws RemoteException {
		this(user.getId(), user.getUserName(), user.getPassword(), user.getSecurityLevel(),
				user.getFirstName(),user.getLastName(),user.getDescription(),
				user.getImg());
	}

	public int getId() {
		return id;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public String getDescription() {
		return description;
	}

	public byte[] getImg() {
		return img;
	}
}