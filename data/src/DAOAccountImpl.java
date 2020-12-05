import shared.AccountDAO;
import shared.AccountDTO;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class DAOAccountImpl implements AccountDAO
{
  private DatabaseHelper<AccountDTO> helperAccount;

  public DAOAccountImpl(DatabaseHelper<AccountDTO> helperAccount) {
    this.helperAccount = helperAccount;
  }

  public AccountDTO createAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName,
      String description, byte[] pictureBytes)  throws RemoteException
  {
    helperAccount.executeUpdate("INSERT INTO sep3db.\"UserAccount\" VALUES (?, ?, ?, ?,?,?,?,?)",userId, username, password,firstName,
        lastName, description, pictureBytes,securityLevel);

    return new AccountDTO(userId, username, password, firstName,
        lastName, description, pictureBytes,securityLevel);
  }

  private AccountDTO createAccount(ResultSet rs) throws SQLException
  {
    int userId = rs.getInt("userid");
    String username = rs.getString("username");
    String password = rs.getString("password");
    String firstName = rs.getString("firstname");
    String lastName = rs.getString("lastname");
    String description = rs.getString("description");
    byte[] pictureBytes =rs.getBytes("img");
    int securityLevel = rs.getInt("securityLevel");
    return new AccountDTO(userId, username, password, firstName,lastName,description,pictureBytes,securityLevel);
  }

  public AccountDTO readAccount(int userId) throws RemoteException {
    return helperAccount.mapSingle(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\" where userid = ?", userId);
  }

  public AccountDTO readAccount(String username) throws RemoteException
  {
    return helperAccount.mapSingle(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\" where username = ?", username);
  }

  public Collection<AccountDTO> readAllAccounts() throws RemoteException {
    return helperAccount.map(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\"");
  }

  @Override
  public AccountDTO updateAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName, String description, byte[] pictureBytes) throws RemoteException {
    helperAccount.executeUpdate("UPDATE sep3db.\"UserAccount\" SET  username=?, password=?, firstname=?, lastname=?, description=?, img=?, securitylevel=? WHERE userid=?",username, password, firstName, lastName, description,pictureBytes,securityLevel,userId);

    return new AccountDTO(userId, username, password, firstName,
            lastName, description, pictureBytes,securityLevel);
  }

@Override
  public void deleteAccount(int userId) throws RemoteException {
    helperAccount.executeUpdate("DELETE FROM sep3db.\"UserAccount\" WHERE userid = ?", userId);
  }
}
