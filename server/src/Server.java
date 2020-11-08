import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}

	public void execute() throws IOException {
		while (true){
			System.out.println("Waiting for a client...");
			//Wait, on welcoming socket for contact by client
			Socket socket = welcomeSocket.accept();

			//Start a thread with the client communication
			/*TODO !!!!
			Thread clientThread = new Thread(new CommunicationThreadHandler(socket));
			clientThread.start();*/
		}
	}

	public static void main(String[] args) throws RemoteException {
		RemoteBase base = new RemoteBase(DAOLocator.getDAO());
		Remote skeleton = UnicastRemoteObject.exportObject(base, 8080);
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind("Base", skeleton);
		System.out.println("Server running");
	}
}
