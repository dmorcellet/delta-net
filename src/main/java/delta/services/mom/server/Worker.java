package delta.services.mom.server;

import java.net.Socket;

public class Worker implements Runnable
{
  private Socket _socket;

  public Worker(Socket s)
  {
    _socket=s;
  }

  public void run()
  {
    // Nothing to do here
  }

  public Socket getSocket()
  {
    return _socket;
  }
}
