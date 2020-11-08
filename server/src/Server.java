import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
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

			String connection = (String) inFromClient.readObject();
			if (!connection.equals("Connect")) {
				System.out.println("Disconnected");
				serverSocket.close();
				break;
			}
			outToClient.writeObject("Username: ");
			String username = (String) inFromClient.readObject();
			outToClient.writeObject("Password: ");
			String password = (String) inFromClient.readObject();
			outToClient.writeObject("Approved");
		}
	}
}

	/*public static void main(String[] args) throws RemoteException {
		RemoteBase base = new RemoteBase(DAOLocator.getDAO());
		Remote skeleton = UnicastRemoteObject.exportObject(base, 8080);
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind("Base", skeleton);
		System.out.println("Server running");
	}*/
