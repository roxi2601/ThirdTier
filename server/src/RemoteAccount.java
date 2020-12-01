import shared.Account;
import shared.AccountDTO;
import shared.User;
import shared.UserDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteAccount extends UnicastRemoteObject implements Account
{
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private String password;
    private int securityLevel;
    private String firstName;
    private String lastName;
    private String description;
    private byte[] img;

    public RemoteAccount(int userId, String username, String password,int securityLevel, String firstName,
                      String lastName, String description, byte[] img) throws RemoteException {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
        this.firstName = firstName;
        this.lastName = lastName;

        this.description = description;
        this.img = img;
    }

    public RemoteAccount(AccountDTO account) throws RemoteException {
        this(account.getUserId(), account.getUsername(), account.getPassword(), account.getSecurityLevel(),
                account.getFirstName(),account.getLastName(),account.getDescription(),
                account.getImg());	}

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword()  {
        return password;
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
    public byte[] getImg() {
        return img;
    }

    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }

}
