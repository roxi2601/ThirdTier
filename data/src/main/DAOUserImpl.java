package main;

import shared.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.Collection;

public class DAOUserImpl implements UserDAO
{
	private DatabaseHelper<UserDTO> helperUser;

	public DAOUserImpl(DatabaseHelper<UserDTO> helperUser) {
		this.helperUser = helperUser;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Roksanka2601");
	}


	public UserDTO createUser(int userId, String username, String password, int securityLevel)  throws RemoteException {
		helperUser.executeUpdate("INSERT INTO sep3db.\"User\" VALUES (?, ?, ?, ?)",userId,username, password, securityLevel);
		return new UserDTO(userId, username, password,securityLevel);
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

	public UserDTO readUser(String username) throws RemoteException
	{
		return helperUser.mapSingle(this::createUser, "SELECT * FROM sep3db.\"User\" where username = ?", username);
	}

	@Override
	public UserDTO updateUser(int userId, String username, String password, int securityLevel) throws RemoteException {
		helperUser.executeUpdate("UPDATE sep3db.\"User\" SET username=?, password=?, securityLevel=? WHERE userid=?",username,password,securityLevel,
				userId);
		return new UserDTO(userId, username,password,securityLevel);
	}

	@Override
	public void deleteUser(int userId) throws RemoteException {
		helperUser.executeUpdate("DELETE FROM sep3db.\"User\" WHERE userid = ?", userId);
	}

	public Collection<UserDTO> readAllUsers() throws RemoteException {
		return helperUser.map(this::createUser, "SELECT * FROM sep3db.\"User\"");
	}



	/*public void update(UserDTO user) throws RemoteException {
		helper.executeUpdate("UPDATE User SET username=?, password=?, securityLevel=? WHERE id = ?",
				user.getUserName(), user.getPassword(), user.getSecurityLevel(),user.getId());
	}*/

	/*public void delete(UserDTO user) throws RemoteException {
		helper.executeUpdate("DELETE FROM User WHERE id = ?", user.getId());
	}*/



}
