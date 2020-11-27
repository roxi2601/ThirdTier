package shared;

import shared.DTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface DAO extends Remote {
	public DTO create(int id, String username, String password, int securityLevel, String firstName, String lastName,
					  String description, byte[] img) throws RemoteException;
	Collection<DTO> readAll() throws RemoteException;
	void update(DTO user) throws RemoteException;
	void delete(DTO user) throws RemoteException;
	DTO read(int id) throws RemoteException;
	DTO read(String username) throws RemoteException;
}
