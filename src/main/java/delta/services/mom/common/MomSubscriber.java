package delta.services.mom.common;

public class MomSubscriber
{
  private String _id;
  private String _hostName;

  public MomSubscriber(String id, String hostName)
  {
    _id=id;
    _hostName=hostName;
  }

  public String getId() { return _id; }
  public String getHostname() { return _hostName; }
}
