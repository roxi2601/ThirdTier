package shared;

public interface Artwork
{
  public byte[] getPictureBytes();

  public String getTitle();

  public String getDescription();

  public String getAuthor();

  public int getPrice();

  public int getUserId();

  public int getId();

  public String getCategory();
}
