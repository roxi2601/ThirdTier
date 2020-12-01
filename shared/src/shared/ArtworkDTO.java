package shared;

import java.io.Serializable;

public class ArtworkDTO implements Serializable
{
  private static final long serialVersionUID = 1L;

  private byte[] pictureBytes;
  private String title;
  private String description;
  private String author;
  private int price;
  private int userId;
  private int id;
  private String category;

  public ArtworkDTO(byte[] pictureBytes, String title, String description,
      String author, int price, int userId, int id, String category)
  {
    this.pictureBytes = pictureBytes;
    this.title = title;
    this.description = description;
    this.author = author;
    this.price = price;
    this.userId = userId;
    this.id = id;
    this.category = category;
  }
  public ArtworkDTO(Artwork artwork)
  {
    this(artwork.getPictureBytes(),artwork.getTitle(),artwork.getDescription(),artwork.getAuthor(),artwork.getPrice(),artwork.getUserId(),artwork.getId(),artwork.getCategory());
  }

  public byte[] getPictureBytes()
  {
    return pictureBytes;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public String getAuthor()
  {
    return author;
  }

  public int getPrice()
  {
    return price;
  }

  public int getUserId()
  {
    return userId;
  }

  public int getId()
  {
    return id;
  }

  public String getCategory()
  {
    return category;
  }

  public void setId(int id)
  {
    this.id = id;
  }
}
