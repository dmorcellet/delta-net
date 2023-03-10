package delta.services.mom.server;

import java.net.Socket;

/**
 * Worker.
 * <p>
 * Handles incoming requests.
 * @author DAM
 */
public class Worker implements Runnable
{
  private Socket _socket;

  /**
   * Constructor.
   * @param s Underlying socket.
   */
  public Worker(Socket s)
  {
    _socket=s;
  }

  public void run()
  {
    // Nothing to do here
  }

  /**
   * Get the managed socket.
   * @return the managed socket.
   */
  public Socket getSocket()
  {
    return _socket;
  }
}
