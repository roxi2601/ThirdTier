import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server {
	private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}

	public static void main(String[] args)
			throws IOException, ClassNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			Socket socket = new Socket("localhost", 1099);

			ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inFromSever = new ObjectInputStream(socket.getInputStream());

			String connection = keyboard.nextLine();
			outToServer.writeObject(connection);

			System.out.println((String) inFromSever.readObject());

			String username = keyboard.nextLine();
			outToServer.writeObject(username);

			System.out.println((String) inFromSever.readObject());

			String password = keyboard.nextLine();
			outToServer.writeObject(password);

			System.out.println((String) inFromSever.readObject());
		}
	}

	/*public static void main(String[] args) throws RemoteException {
		RemoteBase base = new RemoteBase(DAOLocator.getDAO());
		Remote skeleton = UnicastRemoteObject.exportObject(base, 8080);
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind("Base", skeleton);
		System.out.println("Server running");
	}*/
}
