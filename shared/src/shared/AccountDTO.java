package shared;

import java.io.Serializable;
import java.rmi.RemoteException;

public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private String password;
    private int securityLevel;
    private String firstName;
    private String lastName;
    private String description;
    private byte[] img;

    public AccountDTO(int userId, String username, String password, String firstName,
                   String lastName, String description, byte[] img, int securityLevel) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.img = img;
    }

    public AccountDTO(Account account) throws RemoteException {
        this(account.getUserId(), account.getUsername(), account.getPassword(),
                account.getFirstName(), account.getLastName(), account.getDescription(),
                account.getImg(), account.getSecurityLevel());
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getSecurityLevel() {
        return securityLevel;
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

    public String getPassword() {
        return password;
    }
}
