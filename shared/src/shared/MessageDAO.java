package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface MessageDAO extends Remote {
    MessageDTO createMessage(String username, String message) throws RemoteException;
    Collection<MessageDTO> readAllMessages() throws RemoteException;
    MessageDTO readMessage(String message) throws RemoteException;
    MessageDTO updateChat(String username, String message) throws RemoteException;
    void deleteMessage(String message) throws RemoteException;
}
