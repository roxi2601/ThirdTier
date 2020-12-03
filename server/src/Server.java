import communicationWithThirdTier.Request;
import shared.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.*;

public class Server {

	/*private ServerSocket welcomeSocket;

	public Server(int port) throws IOException {
		System.out.println("Starting Server...");

		//Create welcoming socket at the port
		ServerSocket welcomeSocket = new ServerSocket(port);
	}*/

	public static void main(String[] args) throws RemoteException
	{

		DatabaseHelper<UserDTO> helperUser = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DatabaseHelper<AccountDTO> helperAccount = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
		DatabaseHelper<ArtworkDTO> helperArtwork = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");

		UserDAO userDAO = new DAOUserImpl(helperUser);
		AccountDAO accountDAO = new DAOAccountImpl(helperAccount);
		ArtworkDAO artworkDAO = new DAOArtworkImpl(helperArtwork);

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
							UserDTO userDto = userDAO.readUser(request.getObject().toString());

							outToClient.writeObject(userDto);
						}
						if (request.getRequest().equals("getAccount")) {
							int userId = (int)request.getObject();
							AccountDTO dto = accountDAO.readAccount(userId);
							outToClient.writeObject(dto);
						}

						if(request.getRequest().equals("saveArtwork"))
						{
							ArtworkDTO dto = (ArtworkDTO)request.getObject();
							int max = artworkDAO.readAllArtworks().size();
							dto.setId(++max);
							ArtworkDTO saved = artworkDAO.saveArtwork(dto.getPictureBytes(),dto.getTitle(),dto.getDescription(),dto.getAuthor(),dto.getPrice(),dto.getUserId(),dto.getId(),dto.getCategory());
							outToClient.writeObject(saved);
						}
						if(request.getRequest().equals("getArtworks"))
						{
							List<ArtworkDTO> artworks = new ArrayList<>(artworkDAO.readAllArtworks());
							outToClient.writeObject(artworks);
						}
						if(request.getRequest().equals("getArtwork"))
						{
							int id = (int)request.getObject();
							ArtworkDTO dto = artworkDAO.readArtwork(id);
							outToClient.writeObject(dto);
						}
						if(request.getRequest().equals("deleteArtwork"))
						{
							int id = (int)request.getObject();
							artworkDAO.deleteArtwork(id);
						}
						if(request.getRequest().equals("editArtwork"))
						{
							ArtworkDTO dto = (ArtworkDTO)request.getObject();
							ArtworkDTO saved = artworkDAO.updateArtwork(dto.getPictureBytes(),dto.getTitle(),dto.getDescription(),dto.getAuthor(),dto.getPrice(),dto.getUserId(), dto.getId(),dto.getCategory());
							outToClient.writeObject(saved);
						}
						if(request.getRequest().equals("saveUser"))
						{
							AccountDTO accountDtoFromRequest = (AccountDTO) request.getObject();
							int max = accountDAO.readAllAccounts().size();
							accountDtoFromRequest.setUserId(++max);
							AccountDTO accountDto =accountDAO.createAccount(accountDtoFromRequest.getUserId(),
									accountDtoFromRequest.getUsername(), accountDtoFromRequest.getPassword(),accountDtoFromRequest.getSecurityLevel(),
									accountDtoFromRequest.getFirstName(),accountDtoFromRequest.getLastName(),accountDtoFromRequest.getDescription(),
									accountDtoFromRequest.getPictureBytes());
							userDAO.createUser(accountDto.getUserId(),accountDtoFromRequest.getUsername(), accountDtoFromRequest.getPassword(),accountDtoFromRequest.getSecurityLevel());
							outToClient.writeObject(accountDto);

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
