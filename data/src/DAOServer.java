import shared.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class DAOServer extends UnicastRemoteObject implements DAO
{
	private static final long serialVersionUID = 1;
	private final DAOImplementation implementation;



	public DAOServer(DatabaseHelper<UserDTO> helperUser, DatabaseHelper<AccountDTO> helperAccount, DatabaseHelper<ArtworkDTO> helperArtwork) throws RemoteException {
		implementation = new DAOImplementation(helperUser, helperAccount,helperArtwork);
	}

	@Override
	public AccountDTO createAccount(int id, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] img) throws RemoteException {
		return implementation.createAccount(id, username, password, securityLevel,firstName,lastName,description,img);
	}

	@Override
	public UserDTO createUser(int id, String username, String password, int securityLevel) throws RemoteException {
		return implementation.createUser(id,username,password,securityLevel);
	}

	@Override
	public AccountDTO readAccount(int id) throws RemoteException {
		return implementation.readAccount(id);
	}
	@Override
	public UserDTO readUser(int id) throws RemoteException {
		return implementation.readUser(id);
	}

	@Override
	public UserDTO readUser(String username) throws RemoteException {
		return implementation.readUser(username);
	}

	@Override
	public AccountDTO readAccount(String username) throws RemoteException {
		return implementation.readAccount(username);
	}
//artwork methods
	@Override public ArtworkDTO saveArtwork(byte[] pictureBytes, String title,
			String description, String author, int price, int userId, int id,
			String category) throws RemoteException
	{
		return implementation.saveArtwork(pictureBytes, title, description, author, price, userId, id, category);
	}

	@Override public ArtworkDTO readArtwork(int id) throws RemoteException
	{
		return implementation.readArtwork(id);
	}

	@Override public Collection<ArtworkDTO> readAllArtworks()
			throws RemoteException
	{
		return implementation.readAllArtworks();
	}

	@Override public Collection<ArtworkDTO> readArtworksFrom(int userId)
			throws RemoteException
	{
		return implementation.readArtworksFrom(userId);
	}

	@Override
	public Collection<AccountDTO> readAllAccounts() throws RemoteException {
		return implementation.readAllAccounts();
	}
	@Override
	public Collection<UserDTO> readAllUsers() throws RemoteException {
		return implementation.readAllUsers();
	}

	@Override
	public void update(AccountDTO account) throws RemoteException {
		implementation.update(account);
	}

	@Override
	public void delete(AccountDTO account) throws RemoteException {
		implementation.delete(account);
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseHelper<UserDTO> helperUser = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DatabaseHelper<AccountDTO> helperAccount = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DatabaseHelper<ArtworkDTO> helperArtwork = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DAOServer daoServer = new DAOServer(helperUser,helperAccount,helperArtwork);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("userDao", daoServer);
	}
}
