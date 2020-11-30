import shared.AccountDTO;
import shared.UserDTO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.Collection;

public class DAOImplementation {
	private DatabaseHelper<UserDTO> helperUser;
	private DatabaseHelper<AccountDTO> helperAccount;

	public DAOImplementation(DatabaseHelper<UserDTO> helperUser, DatabaseHelper<AccountDTO> helperAccount) {
		this.helperUser = helperUser;
		this.helperAccount = helperAccount;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Roksanka2601");
	}

	public AccountDTO createAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] img)  throws RemoteException {
		helperAccount.executeUpdate("INSERT INTO sep3db.\"UserAccount\" VALUES (?, ?, ?, ?,?,?,?,?)", userId, username, password,firstName,
				lastName, description, img,securityLevel);

		return new AccountDTO(userId, username, password, firstName,
				lastName, description, img,securityLevel);
	}
	public UserDTO createUser(int userId, String username, String password, int securityLevel)  throws RemoteException {
		helperUser.executeUpdate("INSERT INTO sep3db.\"User\" VALUES (?, ?, ?, ?)", userId, username, password, securityLevel);
		return new UserDTO(userId, username, password,securityLevel);
	}
	
	private AccountDTO createAccount(ResultSet rs) throws SQLException {
		int accountId = rs.getInt("accountId");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String firstName = rs.getString("firstname");
		String lastName = rs.getString("lastname");
		String description = rs.getString("description");
		byte[] img =rs.getBytes("img");
		int securityLevel = rs.getInt("securityLevel");
		return new AccountDTO(accountId, username, password, firstName,lastName,description,img,securityLevel);
	}
	private UserDTO createUser(ResultSet rs) throws SQLException {
		int userId = rs.getInt("userId");
		String username = rs.getString("username");
		String password = rs.getString("password");
		int securityLevel = rs.getInt("securityLevel");
		return new UserDTO(userId, username, password, securityLevel);
	}

	public UserDTO readUser(int userId) throws RemoteException {
		return helperUser.mapSingle(this::createUser, "SELECT * FROM sep3db.\"User\" where userId = ?", userId);
	}
	public AccountDTO readAccount(int accountId) throws RemoteException {
		return helperAccount.mapSingle(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\" where accountId = ?", accountId);
	}
	public UserDTO readUser(String username) throws RemoteException
	{
		return helperUser.mapSingle(this::createUser, "SELECT * FROM sep3db.\"User\" where username = ?", username);
	}
	public AccountDTO readAccount(String username) throws RemoteException
	{
		return helperAccount.mapSingle(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\" where username = ?", username);
	}

	public Collection<UserDTO> readAllUsers() throws RemoteException {
		return helperUser.map(this::createUser, "SELECT * FROM sep3db.\"User\"");
	}

	public Collection<AccountDTO> readAllAccounts() throws RemoteException {
		return helperAccount.map(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\"");
	}

	public void update(AccountDTO account) throws RemoteException {
		helperAccount.executeUpdate("UPDATE sep3db.\"UserAccount\" SET username=?, password=?,  firstname=?, lastname=?," +
						"description=?, img=? ,securityLevel=? WHERE accountId = ?",
				account.getUsername(), account.getPassword(),  account.getFirstName(),account.getLastName(),
				account.getDescription(),account.getImg(),account.getSecurityLevel(),account.getAccountId());
	}
	/*public void update(UserDTO user) throws RemoteException {
		helper.executeUpdate("UPDATE User SET username=?, password=?, securityLevel=? WHERE id = ?",
				user.getUserName(), user.getPassword(), user.getSecurityLevel(),user.getId());
	}*/

	/*public void delete(UserDTO user) throws RemoteException {
		helper.executeUpdate("DELETE FROM User WHERE id = ?", user.getId());
	}*/
	public void delete(AccountDTO account) throws RemoteException {
		helperAccount.executeUpdate("DELETE FROM sep3db.\"UserAccount\" WHERE accountId = ?", account.getAccountId());
	}
	
	/*private void createTestDB() throws SQLException {
		try (Connection connection = getConnection()) {
			Statement stat = connection.createStatement();
			stat.executeUpdate("DELETE FROM user");
		}
	}*/
}
