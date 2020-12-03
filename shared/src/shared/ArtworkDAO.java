package shared;

import java.rmi.RemoteException;
import java.util.Collection;

public interface ArtworkDAO
{
  //artworks methods
  ArtworkDTO saveArtwork(byte[] pictureBytes, String title, String description,
      String author, int price, int userId, int id, String category) throws
      RemoteException;
  ArtworkDTO readArtwork(int id) throws RemoteException;
  Collection<ArtworkDTO> readAllArtworks() throws RemoteException;
  Collection<ArtworkDTO> readArtworksFrom(int userId) throws RemoteException;
  void deleteArtwork(int id) throws RemoteException;
}
