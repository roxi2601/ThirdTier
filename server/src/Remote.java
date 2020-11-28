import shared.UserDTO;
import shared.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Remote extends UnicastRemoteObject implements User
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private int securityLevel;
	private String firstName;
	private String lastName;
	private String description;
	private byte[] img;

	public Remote(int id, String username, String password, int securityLevel, String firstName,
			   String lastName, String description, byte[] img) throws RemoteException{
		this.id = id;
		this.username = username;
		this.password = password;
		this.securityLevel = securityLevel;
		this.firstName = firstName;
		this.lastName = lastName;

		this.description = description;
		this.img = img;
	}
	
	public Remote(UserDTO user) throws RemoteException {
		this(user.getId(), user.getUserName(), user.getPassword(), user.getSecurityLevel(),
				user.getFirstName(),user.getLastName(),user.getDescription(),
				user.getImg());	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getFirstName()  {
		return firstName;
	}

	@Override
	public String getLastName()  {
		return lastName;
	}

	@Override
	public String getDescription()  {
		return description;
	}

	@Override
	public String getPassword() {
		return password;
	}



	@Override
	public byte[] getImg() {
		return img;
	}

	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}

}
