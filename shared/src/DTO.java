import java.io.Serializable;
import java.rmi.RemoteException;

public class DTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private int securityLevel;

	public DTO(int id, String username, String password, int securityLevel) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.securityLevel = securityLevel;
	}

	public DTO(User user) throws RemoteException {
		this(user.getId(), user.getUserName(), user.getPassword(), user.getSecurityLevel());
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
}
