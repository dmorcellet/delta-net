package delta.services.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import delta.common.utils.traces.UtilsLoggers;

public class TCPIPServer
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();

  private ServerSocket _serverSocket;
  private short _port;
  private byte _state;

  public TCPIPServer(short port)
  {
    _state=TCPIPServerState.NOT_STARTED;
    _port=port;
  }

  public void start()
  {
    try
    {
      _serverSocket=new ServerSocket(_port);
      _state=TCPIPServerState.STARTED;
    }
    catch(IOException ioException)
    {
      _logger.error("",ioException);
    }
  }

  public byte getState()
  {
    return _state;
  }

  public short getPort()
  {
    return _port;
  }

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
        _logger.error("",ioException);
      }
      if(s!=null)
      {
        handleNewClient(s);
      }
    }
  }

  public void handleNewClient(Socket s)
  {
    // Nothing to do !
  }
}
