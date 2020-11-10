import org.junit.Before;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BaseTest {
	private Base base;
	
	@Before
	public void setUp() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(1099);
		base = (Base) registry.lookup("Base");
	}

	@Test
	public void test() throws RemoteException {
		User user = base.getUser("postgres");
		user.getSecurityLevel();
		List<User> allUsers = base.getAllUsers();
		assertEquals(1, allUsers.size());
	}
}
