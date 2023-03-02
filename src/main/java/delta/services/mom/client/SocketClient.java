package delta.services.mom.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.log4j.Logger;

import delta.common.utils.network.services.NetworkServices;
import delta.common.utils.network.services.ServiceInfo;

public class SocketClient
{
  private static final Logger LOGGER=Logger.getLogger(SocketClient.class);

  private ServiceInfo _serviceLocation;
  private Socket _socket;

  /**
   * Constructor using service name.
   * @param serviceName Service name
   */
  public SocketClient(String serviceName)
  {
    _serviceLocation=NetworkServices.getInstance().getServiceByName(serviceName);
    LOGGER.info("New socket client for Service ["+_serviceLocation+"]");
  }

  /**
   * Constructor using hostname and port number.
   * @param hostName Server's hostname.
   * @param port Server's port number.
   */
  public SocketClient(String hostName, short port)
  {
    _serviceLocation=new ServiceInfo("UNKNOWN",hostName,port);
    LOGGER.info("New socket client for Service ["+_serviceLocation+"]");
  }

  /**
   * Constructor using hostname and port number.
   * @param location Location.
   */
  public SocketClient(ServiceInfo location)
  {
    _serviceLocation=location;
    LOGGER.info("New socket client for Service ["+_serviceLocation+"]");
  }

  public boolean connect()
  {
    boolean ret=false;
    if (_socket==null)
    {
      try
      {
        LOGGER.info("Trying to connect to "+_serviceLocation);
        Socket socket=new Socket();
        SocketAddress sockAddr=new InetSocketAddress(_serviceLocation.getHostName(), _serviceLocation.getPort());
        socket.connect(sockAddr,getConnectionTimeout());
        LOGGER.info("OK !");
        doConfigureSocket(socket);
        _socket=socket;
        ret=true;
      }
      catch(IOException ioe)
      {
        LOGGER.error("Cannot connect !",ioe);
        ret=false;
      }
    }
    return ret;
  }

  /**
   * Close socket.
   */
  public void close()
  {
    try
    {
      // Close socket
      if (_socket!=null) _socket.close();
    }
    catch (IOException ioe)
    {
      LOGGER.error("Error during socket closing.",ioe);
    }
    _socket=null;
  }

  public int getConnectionTimeout()
  {
    return 5000;
  }

  protected void doConnect()
  {
    // Nothing to do here.
    // Override to do something usefull.
  }

  protected void doConfigureSocket(Socket socket)
  {
    // Nothing to do here.
    // Override to do some socket configuration.
  }

  protected void doConnectionClosed()
  {
    // Nothing to do here.
    // Override to do something usefull.
  }

  public InputStream getInputStream()
  {
    InputStream ret=null;
    try
    {
      ret=_socket.getInputStream();
    }
    catch(IOException ioe)
    {
      LOGGER.error("Cannot get input stream for socket !",ioe);
    }
    return ret;
  }

  public OutputStream getOutputStream()
  {
    OutputStream ret=null;
    try
    {
      ret=_socket.getOutputStream();
    }
    catch(IOException ioe)
    {
      LOGGER.error("Cannot get output stream for socket !",ioe);
    }
    return ret;
  }
}
