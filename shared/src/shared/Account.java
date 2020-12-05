package shared;



public interface Account  {
    int getUserId();
    String getUsername() ;
    String getPassword() ;
    String getFirstName() ;
    String getLastName() ;
    String getDescription() ;
    byte[] getPictureBytes() ;
    int getSecurityLevel();
}