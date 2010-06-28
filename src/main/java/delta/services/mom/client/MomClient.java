package delta.services.mom.client;

import java.io.DataOutputStream;
import java.io.IOException;

import delta.common.utils.network.services.ServiceInfo;
import delta.services.mom.common.MomCommands;
import delta.services.mom.common.MomMessage;
import delta.services.mom.common.MomSubscriber;

public class MomClient
{
  public static final int BIG_BUFFER=100*1024;
  public static final int SMALL_BUFFER=4*1024;

  private ServiceInfo _serverLocation;
  private MomSocketInterface _interface;

  public MomClient(ServiceInfo location)
  {
    _serverLocation=location;
  }

  public boolean openConnection(String id)
  {
    boolean ret=connect();
    if (!ret) return false;
    return true;
  }

  public boolean connected()
  {
    if (_interface==null) return false;
    return true;
  }

  public void closeConnection()
  {
    if (_interface!=null)
    {
      _interface.close();
    }
    _interface=null;
  }

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

  public void unsubscribe(String topic)
  {
    // Nothing to do here
  }

  /**
   * Send a message in publish/subscribe mode.
   * @param message
   * @param topic
   * @return true=OK, false=sending failed.
   */
  public boolean sendMessage(MomMessage message, String topic)
  {
    return true;
  }

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
