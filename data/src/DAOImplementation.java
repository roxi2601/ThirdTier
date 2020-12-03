import shared.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.Collection;

public class DAOImplementation implements DAO
{
	private DatabaseHelper<UserDTO> helperUser;
	private DatabaseHelper<AccountDTO> helperAccount;
	private DatabaseHelper<ArtworkDTO> helperArtwork;

	public DAOImplementation(DatabaseHelper<UserDTO> helperUser, DatabaseHelper<AccountDTO> helperAccount,DatabaseHelper<ArtworkDTO> helperArtwork) {
		this.helperUser = helperUser;
		this.helperAccount = helperAccount;
		this.helperArtwork = helperArtwork;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Roksanka2601");
	}

	public AccountDTO createAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] pictureBytes)  throws RemoteException {
		helperAccount.executeUpdate("INSERT INTO sep3db.\"UserAccount\" VALUES (?, ?, ?, ?,?,?,?,?)",userId, username, password,firstName,
				lastName, description, pictureBytes,securityLevel);

		return new AccountDTO(userId, username, password, firstName,
				lastName, description, pictureBytes,securityLevel);
	}
	public UserDTO createUser(int userId, String username, String password, int securityLevel)  throws RemoteException {
		helperUser.executeUpdate("INSERT INTO sep3db.\"User\" VALUES (?, ?, ?, ?)",userId,username, password, securityLevel);
		return new UserDTO(userId, username, password,securityLevel);
	}
	
	private AccountDTO createAccount(ResultSet rs) throws SQLException {
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
	private UserDTO createUser(ResultSet rs) throws SQLException {
		int userId = rs.getInt("userid");
		String username = rs.getString("username");
		String password = rs.getString("password");
		int securityLevel = rs.getInt("securityLevel");
		return new UserDTO(userId, username, password, securityLevel);
	}
	private ArtworkDTO createArtwork(ResultSet rs) throws SQLException
	{
		byte[] pictureBytes = rs.getBytes("img");
		String title = rs.getString("title");
		String description = rs.getString("description");
		String author = rs.getString("author");
		int price = rs.getInt("price");
		int userId = rs.getInt("userid");
		int id = rs.getInt("id");
		String category = rs.getString("category");

		return  new ArtworkDTO(pictureBytes,title,description,author,price,userId,id,category);
	}
	public UserDTO readUser(int userId) throws RemoteException {
		return helperUser.mapSingle(this::createUser, "SELECT * FROM sep3db.\"User\" where userid = ?", userId);
	}
	public AccountDTO readAccount(int accountId) throws RemoteException {
		return helperAccount.mapSingle(this::createAccount, "SELECT * FROM sep3db.\"UserAccount\" where userid = ?", accountId);
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
						"description=?, img=? ,securityLevel=? WHERE userid = ?",
				account.getUsername(), account.getPassword(),  account.getFirstName(),account.getLastName(),
				account.getDescription(),account.getPictureBytes(),account.getSecurityLevel(),account.getUserId());
	}
	/*public void update(UserDTO user) throws RemoteException {
		helper.executeUpdate("UPDATE User SET username=?, password=?, securityLevel=? WHERE id = ?",
				user.getUserName(), user.getPassword(), user.getSecurityLevel(),user.getId());
	}*/

	/*public void delete(UserDTO user) throws RemoteException {
		helper.executeUpdate("DELETE FROM User WHERE id = ?", user.getId());
	}*/
	public void delete(AccountDTO account) throws RemoteException {
		helperAccount.executeUpdate("DELETE FROM sep3db.\"UserAccount\" WHERE userid = ?", account.getUserId());
	}
	//artwork methods
	public ArtworkDTO saveArtwork(byte[] pictureBytes, String title,
			String description, String author, int price, int userId, int id,
			String category) throws RemoteException
	{
		helperArtwork.executeUpdate("INSERT INTO sep3db.\"Artwork\" VALUES (?, ?, ?, ?, ?, ?, ?, ?)",pictureBytes, title, description, author, price, userId, id, category);
		return new ArtworkDTO(pictureBytes, title, description, author, price, userId, id, category);
	}

	@Override
	public ArtworkDTO readArtwork(int id) throws RemoteException
	{
		return helperArtwork.mapSingle(this::createArtwork, "SELECT * FROM sep3db.\"Artwork\" where id = ?",id);

	}

	public Collection<ArtworkDTO> readAllArtworks() throws  RemoteException
	{
		return helperArtwork.map(this::createArtwork,"SELECT * FROM sep3db.\"Artwork\"");
	}

	@Override public Collection<ArtworkDTO> readArtworksFrom(int userId)
			throws RemoteException
	{
		return helperArtwork.map(this::createArtwork,"SELECT * FROM sep3db.\"Artwork\" WHERE userid=?",userId);
	}
}
