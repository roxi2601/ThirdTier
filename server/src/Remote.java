import shared.DTO;
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

	public Remote(int id, String username, String password, int securityLevel) throws RemoteException {
		this.id = id;
		this.username = username;
		this.password = password;
		this.securityLevel = securityLevel;
	}
	
	public Remote(DTO user) throws RemoteException {
		this(user.getId(), user.getUserName(), user.getPassword(), user.getSecurityLevel());	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}

}
