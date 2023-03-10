package delta.services.mom.client;

import java.io.DataOutputStream;
import java.io.IOException;

import delta.common.utils.network.services.ServiceInfo;
import delta.services.mom.common.MomCommands;
import delta.services.mom.common.MomMessage;
import delta.services.mom.common.MomSubscriber;

/**
 * MOM client.
 * @author DAM
 */
public class MomClient
{
  /**
   * Big buffer size.
   */
  public static final int BIG_BUFFER=100*1024;
  /**
   * Small buffer size.
   */
  public static final int SMALL_BUFFER=4*1024;

  private ServiceInfo _serverLocation;
  private MomSocketInterface _interface;

  /**
   * Constructor.
   * @param location Server location.
   */
  public MomClient(ServiceInfo location)
  {
    _serverLocation=location;
  }

  /**
   * Open a connection.
   * @param id Connection identifier.
   * @return <code>true</code> if connection is OK, <code>false</code> otherwise.
   */
  public boolean openConnection(String id)
  {
    boolean ret=connect();
    if (!ret) return false;
    return true;
  }

  /**
   * Indicates if the client is connected or not.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public boolean connected()
  {
    if (_interface==null) return false;
    return true;
  }

  /**
   * Close connection.
   */
  public void closeConnection()
  {
    if (_interface!=null)
    {
      _interface.close();
    }
    _interface=null;
  }

  /**
   * Subscribe to a topic.
   * @param topic Topic name.
   */
  public void subscribe(String topic)
  {
    DataOutputStream dos=_interface.getOutputStream();
    try
    {
      dos.writeInt(MomCommands.SUBSCRIBE);
      dos.writeUTF(topic);
      dos.flush();
    }
    catch(IOException ioe)
    {
      closeConnection();
    }
  }

  /**
   * Unsubscribe.
   * @param topic Topic name.
   */
  public void unsubscribe(String topic)
  {
    // Nothing to do here
  }

  /**
   * Send a message in publish/subscribe mode.
   * @param message Message to send.
   * @param topic Topic to send to.
   * @return true=OK, false=sending failed.
   */
  public boolean sendMessage(MomMessage message, String topic)
  {
    return true;
  }

  /**
   * Send a message to a subscriber.
   * @param message Message 
   * @param target Targeted subscriber.
   * @return 1 if OK.
   */
  public int sendMessage(MomMessage message, MomSubscriber target)
  {
    return 1;
  }

  private boolean connect()
  {
    SocketClient socket=new SocketClient(_serverLocation);
    boolean ret=socket.connect();
    if (ret)
    {
      _interface=new MomSocketInterface(socket,SMALL_BUFFER,BIG_BUFFER);
    }
    else
    {
      _interface=null;
    }
    return ret;
  }
}
