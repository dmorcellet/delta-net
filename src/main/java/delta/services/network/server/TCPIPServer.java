package delta.services.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * TCP/IP server.
 * @author DAM
 */
public class TCPIPServer
{
  private static final Logger LOGGER=Logger.getLogger(TCPIPServer.class);

  private ServerSocket _serverSocket;
  private short _port;
  private byte _state;

  /**
   * Constructor.
   * @param port Listening port.
   */
  public TCPIPServer(short port)
  {
    _state=TCPIPServerState.NOT_STARTED;
    _port=port;
  }

  /**
   * Start.
   */
  public void start()
  {
    try
    {
      _serverSocket=new ServerSocket(_port);
      _state=TCPIPServerState.STARTED;
    }
    catch(IOException ioException)
    {
      LOGGER.error("",ioException);
    }
  }

  /**
   * Get the server state.
   * @return a state.
   */
  public byte getState()
  {
    return _state;
  }

  /**
   * Get the server port.
   * @return a port.
   */
  public short getPort()
  {
    return _port;
  }

  /**
   * Server loop.
   */
  public void loop()
  {
    while(true)
    {
      Socket s=null;
      try
      {
        s=_serverSocket.accept();
      }
      catch(IOException ioException)
      {
        LOGGER.error("",ioException);
      }
      if(s!=null)
      {
        handleNewClient(s);
      }
    }
  }

  /**
   * Handle a new client.
   * @param s Socket to use with the new client.
   */
  public void handleNewClient(Socket s)
  {
    // Nothing to do !
  }
}
