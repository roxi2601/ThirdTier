import communicationWithThirdTier.Request;
import shared.DTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/*private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}*/

	public static void main(String[] args)
			throws IOException, ClassNotFoundException {

		ServerSocket serverSocket = new ServerSocket(1098);
		System.out.println("Starting server...");

		while (true) {

			Socket socket = serverSocket.accept();
			System.out.println("Connection established");

			ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());

			ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

			while(true){
				Request request = (Request)inFromClient.readObject();
				if (request.getRequest().equals("getUser")) {
					DTO dto = DAOLocator.getDAO().read(request.getObject().toString());
					outToClient.writeObject(dto);
				}
			}
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
