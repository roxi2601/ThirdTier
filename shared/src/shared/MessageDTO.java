package shared;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String messageBody;
    private LocalDateTime dateTime;
    private ArrayList<String> messages;

    public MessageDTO() {
    }

    public MessageDTO(String username, String messageBody) {
        this.username = username;
        this.messageBody = messageBody;
        this.dateTime = LocalDateTime.now();
        messages = new ArrayList<>();
    }

    public MessageDTO(Message message) {
        this(message.getUsername(), message.getBody());
    }

    public String getUsername() {
        return username;
    }

    public String getBody() {
        return messageBody;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public ArrayList<String> getChats() {
        for (String message : messages){
            System.out.println(message + "/n");
        }
        return messages;
    }
}
