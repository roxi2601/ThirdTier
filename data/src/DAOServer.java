import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class DAOServer extends UnicastRemoteObject implements DAO {
	private static final long serialVersionUID = 1;
	private final DAOImplementation implementation;

	public DAOServer(DatabaseHelper<DTO> helper) throws RemoteException {
		implementation = new DAOImplementation(helper);
	}

	@Override
	public DTO create(int id, String username, String password, int securityLevel) throws RemoteException {
		return implementation.create(id, username, password, securityLevel);
	}

	@Override
	public DTO read(int id) throws RemoteException {
		return implementation.read(id);
	}

	@Override
	public Collection<DTO> readAll() throws RemoteException {
		return implementation.readAll();
	}

	@Override
	public void update(DTO car) throws RemoteException {
		implementation.update(car);
	}

	@Override
	public void delete(DTO car) throws RemoteException {
		implementation.delete(car);
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseHelper<DTO> helper = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=car_base", "postgres", "password");
		DAOServer daoServer = new DAOServer(helper);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("userDao", daoServer);
	}
}
