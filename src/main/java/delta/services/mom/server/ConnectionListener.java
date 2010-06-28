package delta.services.mom.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import delta.common.utils.traces.UtilsLoggers;

public class ConnectionListener implements Runnable
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();

  private ServerSocket _listeningSocket;
  private boolean _isRunning;
  private int _port;

  /**
   * Standard constructor.
   * @param port_p Port to listen to.
   */
  public ConnectionListener(int port_p)
  {
    _isRunning=false;
    _port=port_p;
  }

  private void startListening()
  {
    if (_isRunning) throw new IllegalStateException("start() call is invalid : server is already listening incoming connections !");
    try
    {
      _listeningSocket=new ServerSocket(_port);
      _logger.info("Service at port : " + _port + " has started ...");
      _isRunning=true;
    }
    catch(BindException bindException)
    {
      _logger.error("Cannot create server socket. Bind error !", bindException);
    }
    catch (IOException ioe)
    {
      _logger.error("Cannot create server socket. I/O error", ioe);
    }
  }

  public void stop()
  {
    try
    {
      if (_listeningSocket!=null)
      {
        _listeningSocket.close();
        _listeningSocket=null;
      }
      _isRunning=false;
    }
    catch (Exception exception)
    {
      _logger.error("",exception);
    }
  }

  private void waitForClientConnection()
  {
    // Waiting for a connection
    try
    {
      Socket clientSocket_l=_listeningSocket.accept();
      _logger.info("Server on port "+_port+" handles a new connection...");
      doConnectionClient(clientSocket_l);
    }
    catch(Throwable t)
    {
      _logger.error("Cannot handle new client !",t);
    }
  }

  /**
   * Tells if the server is running.
   * @return true if the server is running
   */
  public boolean isRunning()
  { return _isRunning ; }

  public void run()
  {
    startListening();
    while (_isRunning)
    {
      waitForClientConnection();
    }
    _logger.info("End of connection listener !");
    stop();
  }

  /**
   * Starts the server.
   * @param blocking tells if the server is to be launched within a new
   * thread or not (false=new thread server, true=blocking server).
   */
  public void start(boolean blocking)
  {
    if (blocking)
      run();
    else
    {
      new Thread(this).start();
    }
  }

  protected void doConnectionClient(Socket clientSocket) throws Exception
  {
    // This method should be overloaded !
  }
}
