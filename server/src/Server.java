import communicationWithThirdTier.Request;
import shared.AccountDTO;
import shared.Artwork;
import shared.ArtworkDTO;
import shared.UserDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

	/*private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}*/

	public static void main(String[] args){
			try{
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
							UserDTO userDto = DAOLocator.getDAO().readUser(request.getObject().toString());

							outToClient.writeObject(userDto);
						}
						if (request.getRequest().equals("getAccount")) {
							AccountDTO accountDto = DAOLocator.getDAO().readAccount(request.getObject().toString());
							outToClient.writeObject(accountDto);
						}
						if(request.getRequest().equals("saveArtwork"))
						{
							ArtworkDTO dto = (ArtworkDTO)request.getObject();
							int max = DAOLocator.getDAO().readAllArtworks().size();
							dto.setId(++max);
							ArtworkDTO saved = DAOLocator.getDAO().saveArtwork(dto.getPictureBytes(),dto.getTitle(),dto.getDescription(),dto.getAuthor(),dto.getPrice(),dto.getUserId(),dto.getId(),dto.getCategory());
							outToClient.writeObject(saved);
						}
						if(request.getRequest().equals("getArtworks"))
						{
							List<ArtworkDTO> artworks = new ArrayList<>(DAOLocator.getDAO().readAllArtworks());
							outToClient.writeObject(artworks);
						}
						if(request.getRequest().equals("saveUser"))
						{
							AccountDTO accountDtoFromRequest = (AccountDTO) request.getObject();
							int max = DAOLocator.getDAO().readAllAccounts().size();
							accountDtoFromRequest.setUserId(++max);
							AccountDTO accountDto = DAOLocator.getDAO().createAccount(accountDtoFromRequest.getUserId(),
									accountDtoFromRequest.getUsername(), accountDtoFromRequest.getPassword(),accountDtoFromRequest.getSecurityLevel(),
									accountDtoFromRequest.getFirstName(),accountDtoFromRequest.getLastName(),accountDtoFromRequest.getDescription(),
									accountDtoFromRequest.getPictureBytes());
							System.out.println(accountDto);
							DAOLocator.getDAO().createUser(accountDto.getUserId(),accountDtoFromRequest.getUsername(), accountDtoFromRequest.getPassword(),accountDtoFromRequest.getSecurityLevel());
							outToClient.writeObject(accountDto);
							System.out.println(accountDto);
						}

					}
				}
			}catch (Exception e)
			{
				e.printStackTrace();
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
