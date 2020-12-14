package main;

import shared.MessageDAO;
import shared.MessageDTO;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class DAOChatImpl implements MessageDAO {
    private DatabaseHelper<MessageDTO> helperChat;

    public DAOChatImpl(DatabaseHelper<MessageDTO> helperChat) {
        this.helperChat = helperChat;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Roksanka2601");
    }

    public MessageDTO createMessage(String username, String message)  throws RemoteException {
        helperChat.executeUpdate("INSERT INTO sep3db.\"User\" VALUES (?, ?)",username, message);
        return new MessageDTO(username, message);
    }

    private MessageDTO createMessage(ResultSet rs) throws SQLException {
        String username = rs.getString("username");
        String message = rs.getString("message");
        return new MessageDTO(username, message);
    }

    public Collection<MessageDTO> readAllMessages() throws RemoteException {
        return helperChat.map(this::createMessage, "SELECT * FROM sep3db.\"User\"");
    }

    public MessageDTO readMessage(String message) throws RemoteException {
        return helperChat.mapSingle(this::createMessage, "SELECT * FROM sep3db.\"User\" where message = ?", message);
    }

    @Override
    public MessageDTO updateChat(String username, String message) throws RemoteException {
        helperChat.executeUpdate("UPDATE sep3db.\"User\" SET message=? WHERE username=?",message, username);
        return new MessageDTO(message, username);
    }

    @Override
    public void deleteMessage(String message) throws RemoteException {
        helperChat.executeUpdate("DELETE FROM sep3db.\"User\" WHERE message = ?", message);
    }
}
