import org.junit.runner.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Persistence{
	private ServerSocket welcomeSocket;
	private ArrayList<User> users;
	private Persistence persistence;

	public Server(int port, DatabaseHelper<DTO> helper) throws IOException {
		users = new ArrayList<User>();
		persistence = (Persistence) new DAOImplementation(helper);

		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}

	public static void main(String[] args)
			throws IOException, ClassNotFoundException {

		ServerSocket serverSocket = new ServerSocket(1099);
		System.out.println("Starting server...");

		while (true) {

			Socket socket = serverSocket.accept();
			System.out.println("Connection established");

			ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());

			ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

			Request request = (Request) inFromClient.readObject();

			//TODO!
			/*if (!request.getRequest().equals("getUserName")) {
				User user = (User) DAOLocator.getDAO().read(users);
				outToClient.writeObject(user);
				serverSocket.close();
				break;
			}*/
			
			/*outToClient.writeObject("Username: ");
			String username = (String) inFromClient.readObject();
			outToClient.writeObject("Password: ");
			String password = (String) inFromClient.readObject();
			outToClient.writeObject("Approved");*/
		}
	}

	@Override
	public void addUserToDB(User user) {
		new Thread(() -> {
			persistence.addUserToDB(user);
			users.add(user);
		}).start();
	}

	@Override
	public void removeUserFromDB(User user) {
		new Thread(() -> {
			persistence.removeUserFromDB(user);
			users.remove(user);
		}).start();
	}

	@Override
	public void getUserById(User user) {
		try {
			persistence.getUserById(user);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void getUserByUserName(User user){
		try {
			persistence.getUserByUserName(user);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void getUserByPassword(User user) {
		try {
			persistence.getUserByPassword(user);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void getUserBySecurityLevel(User user) {
		try {
			persistence.getUserBySecurityLevel(user);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public User loginUser(int id, String username, String password, int securityLevel) throws Exception {
		User user = null;
		try {
			user = persistence.loginUser(id, username, password, securityLevel);
		}
		catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
		return user;
		/*if(user==null)
		{
			throw new UserNotFoundException("username not found");
		}
		else if(user.getPassword().equals(password))
		{
			return user;
		}
		throw new UserNotFoundException("wrong password");*/
	}

	@Override
	public ArrayList<User> getAllUserFromDB() throws Exception {
		return persistence.getAllUserFromDB();
	}
}

	/*public static void main(String[] args) throws RemoteException {
		RemoteBase base = new RemoteBase(DAOLocator.getDAO());
		Remote skeleton = UnicastRemoteObject.exportObject(base, 8080);
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind("Base", skeleton);
		System.out.println("Server running");
	}*/
