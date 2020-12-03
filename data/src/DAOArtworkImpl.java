import shared.ArtworkDAO;
import shared.ArtworkDTO;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class DAOArtworkImpl implements ArtworkDAO
{
  private DatabaseHelper<ArtworkDTO> helperArtwork;


  public DAOArtworkImpl(DatabaseHelper<ArtworkDTO> helperArtwork) {
    this.helperArtwork = helperArtwork;
  }

  private ArtworkDTO createArtwork(ResultSet rs) throws SQLException
  {
    byte[] pictureBytes = rs.getBytes("img");
    String title = rs.getString("title");
    String description = rs.getString("description");
    String author = rs.getString("author");
    int price = rs.getInt("price");
    int userId = rs.getInt("userid");
    int id = rs.getInt("id");
    String category = rs.getString("category");

    return  new ArtworkDTO(pictureBytes,title,description,author,price,userId,id,category);
  }
  //artwork methods
  public ArtworkDTO saveArtwork(byte[] pictureBytes, String title,
      String description, String author, int price, int userId, int id,
      String category) throws RemoteException
  {
    helperArtwork.executeUpdate("INSERT INTO sep3db.\"Artwork\" VALUES (?, ?, ?, ?, ?, ?, ?, ?)",pictureBytes, title, description, author, price, userId, id, category);
    return new ArtworkDTO(pictureBytes, title, description, author, price, userId, id, category);
  }

  @Override
  public ArtworkDTO readArtwork(int id) throws RemoteException
  {
    return helperArtwork.mapSingle(this::createArtwork, "SELECT * FROM sep3db.\"Artwork\" where id = ?",id);

  }

  public Collection<ArtworkDTO> readAllArtworks() throws  RemoteException
  {
    return helperArtwork.map(this::createArtwork,"SELECT * FROM sep3db.\"Artwork\"");
  }

  @Override public Collection<ArtworkDTO> readArtworksFrom(int userId)
      throws RemoteException
  {
    return helperArtwork.map(this::createArtwork,"SELECT * FROM sep3db.\"Artwork\" WHERE userid=?",userId);
  }

  @Override public void deleteArtwork(int id) throws RemoteException
  {
    helperArtwork.executeUpdate("DELETE FROM sep3db.\"Artwork\" WHERE id = ?", id);
  }

  @Override public ArtworkDTO updateArtwork(byte[] pictureBytes, String title,
      String description, String author, int price,int userId, int id, String category)
      throws RemoteException
  {
    helperArtwork.executeUpdate("UPDATE sep3db.\"Artwork\" SET img=?, title=?,  description=?, author=?," +
        "price=?, category=?  WHERE id = ?",pictureBytes,title,description,author,price,category,id);
    return new ArtworkDTO(pictureBytes, title, description, author, price, userId, id, category);
  }
}
