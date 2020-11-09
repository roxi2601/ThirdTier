import java.rmi.RemoteException;
import java.sql.*;
import java.util.Collection;

public class DAOImplementation {
	private DatabaseHelper<DTO> helper;

	public DAOImplementation(DatabaseHelper<DTO> helper) {
		this.helper = helper;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "JJuu11@@");
	}

	public DTO create(int id, String username, String password, int securityLevel)  throws RemoteException {
		helper.executeUpdate("INSERT INTO user VALUES (?, ?, ?, ?)", id, username, password, securityLevel);
		return new DTO(id, username, password, securityLevel);
	}
	
	private DTO createUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		int securityLevel = rs.getInt("securityLevel");
		return new DTO(id, username, password, securityLevel);
	}

	public DTO read(int id) throws RemoteException {
		return helper.mapSingle(this::createUser, "SELECT * FROM sep3db.\"user\" where id = ?", id);
	}
	public DTO read(String username) throws RemoteException
	{
		return helper.mapSingle(this::createUser, "SELECT * FROM sep3db.\"user\" where username = ?", username);
	}

	public Collection<DTO> readAll() throws RemoteException {
		return helper.map(this::createUser, "SELECT * FROM sep3db.\"user\"");
	}

	public void update(DTO user) throws RemoteException {
		helper.executeUpdate("UPDATE sep3db.\"user\" SET username=?, password=?, securityLevel=? WHERE id = ?",
				user.getUserName(), user.getPassword(), user.getSecurityLevel(), user.getId());
	}

	public void delete(DTO user) throws RemoteException {
		helper.executeUpdate("DELETE FROM sep3db.\"user\" WHERE id = ?", user.getId());
	}
	
	private void createTestDB() throws SQLException {
		try (Connection connection = getConnection()) {
			Statement stat = connection.createStatement();
			stat.executeUpdate("DELETE FROM user");
		}
	}
}
