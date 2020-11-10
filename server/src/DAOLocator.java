import shared.DAO;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DAOLocator {

	public static DAO getDAO() throws RemoteException {
		Registry registry = LocateRegistry.getRegistry(1099);
		try {
			return (DAO) registry.lookup("userDao");
		} catch (NotBoundException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

}
