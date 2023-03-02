package delta.services.mom.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ConnectionListener implements Runnable
{
  private static final Logger LOGGER=Logger.getLogger(ConnectionListener.class);

  private ServerSocket _listeningSocket;
  private boolean _isRunning;
  private int _port;

  /**
   * Standard constructor.
   * @param port Port to listen to.
   */
  public ConnectionListener(int port)
  {
    _isRunning=false;
    _port=port;
  }

  private void startListening()
  {
    if (_isRunning) throw new IllegalStateException("start() call is invalid : server is already listening incoming connections !");
    try
    {
      _listeningSocket=new ServerSocket(_port);
      LOGGER.info("Service at port : " + _port + " has started ...");
      _isRunning=true;
    }
    catch(BindException bindException)
    {
      LOGGER.error("Cannot create server socket. Bind error !", bindException);
    }
    catch (IOException ioe)
    {
      LOGGER.error("Cannot create server socket. I/O error", ioe);
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
      LOGGER.error("",exception);
    }
  }

  private void waitForClientConnection()
  {
    // Waiting for a connection
    try
    {
      Socket clientSocket=_listeningSocket.accept();
      LOGGER.info("Server on port "+_port+" handles a new connection...");
      doConnectionClient(clientSocket);
    }
    catch(Throwable t)
    {
      LOGGER.error("Cannot handle new client !",t);
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
    LOGGER.info("End of connection listener !");
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
