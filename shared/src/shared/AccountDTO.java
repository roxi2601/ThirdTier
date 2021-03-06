package shared;


import java.io.Serializable;

public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private String password;
    private int securityLevel;
    private String firstName;
    private String lastName;
    private String description;
    private byte[] pictureBytes;

    public AccountDTO() {
    }

    public AccountDTO(int userId, String username, String password, String firstName,
                      String lastName, String description, byte[] pictureBytes, int securityLevel) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.pictureBytes = pictureBytes;
    }

    public AccountDTO(Account account) {
        this(account.getUserId(), account.getUsername(), account.getPassword(),
                account.getFirstName(), account.getLastName(), account.getDescription(),
                account.getPictureBytes(), account.getSecurityLevel());
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPictureBytes() {
        return pictureBytes;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "AccountDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", securityLevel=" + securityLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
