package delta.services.mom.common;

/**
 * Subscriber description.
 * @author DAM
 */
public class MomSubscriber
{
  private String _id;
  private String _hostName;

  /**
   * Constructor.
   * @param id Identifier.
   * @param hostName Hostname.
   */
  public MomSubscriber(String id, String hostName)
  {
    _id=id;
    _hostName=hostName;
  }

  /**
   * Get the identifier.
   * @return the identifier.
   */
  public String getId()
  {
    return _id;
  }

  /**
   * Get the hostname.
   * @return the hostname.
   */
  public String getHostname()
  {
    return _hostName;
  }
}
