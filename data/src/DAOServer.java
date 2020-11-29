import shared.DAO;
import shared.UserDTO;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class DAOServer extends UnicastRemoteObject implements DAO
{
	private static final long serialVersionUID = 1;
	private final DAOImplementation implementation;

	public DAOServer(DatabaseHelper<UserDTO> helper) throws RemoteException {
		implementation = new DAOImplementation(helper);
	}

	@Override
	public UserDTO create(int id, String username, String password, int securityLevel, String firstName, String lastName,
						  String description, byte[] img) throws RemoteException {
		return implementation.create(id, username, password, securityLevel,firstName,lastName,description,img);
	}

	@Override
	public UserDTO read(int id) throws RemoteException {
		return implementation.read(id);
	}
	@Override
	public UserDTO read(String username) throws RemoteException {
		return implementation.read(username);
	}



	@Override
	public Collection<UserDTO> readAll() throws RemoteException {
		return implementation.readAll();
	}

	@Override
	public void update(UserDTO user) throws RemoteException {
		implementation.update(user);
	}

	@Override
	public void delete(UserDTO user) throws RemoteException {
		implementation.delete(user);
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseHelper<UserDTO> helper = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DAOServer daoServer = new DAOServer(helper);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("userDao", daoServer);
	}
}
