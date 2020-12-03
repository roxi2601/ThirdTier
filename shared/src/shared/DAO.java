package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface DAO extends Remote {
	AccountDTO createAccount(int userId, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] pictureBytes) throws RemoteException;
	UserDTO createUser(int userId, String username, String password, int securityLevel) throws RemoteException;
	Collection<UserDTO> readAllUsers() throws RemoteException;
	Collection<AccountDTO> readAllAccounts() throws RemoteException;
	void update(AccountDTO user) throws RemoteException;
	void delete(AccountDTO user) throws RemoteException;
	AccountDTO readAccount(int id) throws RemoteException;
	UserDTO readUser(int id) throws RemoteException;
	UserDTO readUser(String username) throws RemoteException;
	AccountDTO readAccount(String username) throws RemoteException;
	//artworks methods
	ArtworkDTO saveArtwork(byte[] pictureBytes, String title, String description,
			String author, int price, int userId, int id, String category) throws RemoteException;
	ArtworkDTO readArtwork(int id) throws RemoteException;
	Collection<ArtworkDTO> readAllArtworks() throws RemoteException;
	Collection<ArtworkDTO> readArtworksFrom(int userId) throws RemoteException;
}
