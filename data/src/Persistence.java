import java.util.ArrayList;

public interface Persistence {
    //Adding, removing users
    void addUserToDB(User user);
    void removeUserFromDB(User user);
    void getUserById(User user);
    void getUserByUserName(User user);
    void getUserByPassword(User user);
    void getUserBySecurityLevel(User user);

    //Login checks
    User loginUser(int id, String username, String password, int securityLevel) throws Exception;
    ArrayList<User> getAllUserFromDB() throws Exception;
}
