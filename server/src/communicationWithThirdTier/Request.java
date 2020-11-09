package communicationWithThirdTier;

import java.io.Serializable;

public class Request implements Serializable
{
  private  Object obj;
  private String requestType;

  public Request(String requestType,Object obj)
  {
    this.requestType = requestType;
    this.obj = obj;
  }

  public Object getObject()
  {
    return obj;
  }
  public String getRequest()
  {
    return requestType;
  }
}
