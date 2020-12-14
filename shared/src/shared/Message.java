package shared;

import java.util.ArrayList;

public interface Message {
    String getUsername();
    ArrayList<String> getChats();
    String getBody();
}
