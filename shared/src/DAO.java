import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface DAO extends Remote {
	DTO create(int id, String username, String password, int securityLevel) throws RemoteException;
	Collection<DTO> readAll() throws RemoteException;
	void update(DTO user) throws RemoteException;
	void delete(DTO user) throws RemoteException;
	DTO read(int id) throws RemoteException;
}
