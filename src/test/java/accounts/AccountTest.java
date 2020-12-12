package accounts;

import main.DAOAccountImpl;
import main.DAOUserImpl;
import main.DatabaseHelper;
import org.junit.Test;
import shared.*;

import java.rmi.RemoteException;
import static org.junit.Assert.*;



public class AccountTest {

    DatabaseHelper<AccountDTO> helperAccount = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");
    DatabaseHelper<UserDTO> helperUser = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep3db", "postgres", "Roksanka2601");

    AccountDAO accountDAO= new DAOAccountImpl(helperAccount);
    UserDAO userDAO= new DAOUserImpl(helperUser);

    public AccountTest() throws RemoteException {
    }

   /* @Test
   public void correctLogin() throws RemoteException {
        String username ="roksi11";
        String password = "roksi1234";
        UserDTO userFromDB =  userDAO.readUser(username);
       assertEquals(password,userFromDB.getPassword());
    }

    @Test
    public void incorrectUsernameLogin() throws RemoteException {
        String username ="incorrectusername";
        UserDTO userFromDB =  userDAO.readUser(username);
        assertNull(userFromDB);

    }

    @Test
    public void incorrectPasswordLogin() throws RemoteException {
        String username = "roksi11";
        String password ="incorrectpassword";
        UserDTO userFromDB =  userDAO.readUser(username);
        assertNotEquals(password,userFromDB.getPassword());

    }*/
   /* @Test
    public void correctSignUp() throws RemoteException {
        String username ="correctsignuptest3";
        String password = "roksi1234";
        int securityLevel =3;
        int userId=113;
        String firstname="Roksana";
        String lastname = "Dziadowicz";
        String description ="aaa";
        byte[] img = null;

        AccountDTO accountFromDB =  accountDAO.createAccount(userId,username,password,securityLevel,firstname,lastname,description,img);
        UserDTO userFromDB =  userDAO.createUser(userId,username,password,securityLevel);
        assertEquals(username,accountFromDB.getUsername());
        assertEquals(username,userFromDB.getUsername());
    }


    @Test
    public void correctUsernameSignUp() throws RemoteException {
        String username ="correctusername";
        AccountDTO accountFromDB =  accountDAO.readAccount(username);
        UserDTO userFromDB = userDAO.readUser(username);
        assertNull(accountFromDB);
        assertNull(userFromDB);
    }*/

    @Test
    public void correctDelete() throws RemoteException {
       int userId =100;
        userDAO.deleteUser(userId);
      accountDAO.deleteAccount(userId);
        System.out.println("User deleted");
    }

   /* @Test
    public void correctEdit() throws RemoteException {
        String username ="correctedit1";
        String password = "roksi12345";
        int securityLevel =3;
        int userId=113;
        String firstname="Roksanaaaa";
        String lastname = "Dziadowicz";
        String description ="aaa";
        byte[] img = null;

        AccountDTO accountFromDB =  accountDAO.updateAccount(userId,username,password,
                securityLevel,firstname,lastname,description,img);
        UserDTO userFromDB =  userDAO.updateUser(userId,username,password,securityLevel);
        assertEquals(username,accountFromDB.getUsername());
        assertEquals(username,userFromDB.getUsername());
    }
    @Test
    public void correctUsernameEdit() throws RemoteException {
        String username ="correctusername";
        AccountDTO accountFromDB =  accountDAO.readAccount(username);
        UserDTO userFromDB = userDAO.readUser(username);
        assertNull(accountFromDB);
        assertNull(userFromDB);
    }*/
            }
