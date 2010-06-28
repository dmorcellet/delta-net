package delta.services.communication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Emitter
{
  private DatagramSocket _socket;
  private DatagramPacket _packet;
  private DatagramPacket _ackPacket;

  public Emitter()
  {
    try
    {
      _socket=new DatagramSocket();
      _socket.setSoTimeout(5000);
      _packet=new DatagramPacket(new byte[0], 0, 0);
      _ackPacket=new DatagramPacket(new byte[0], 0, 0);
    }
    catch(SocketException soe)
    {
      soe.printStackTrace();
    }
  }

  public InetSocketAddress getLocalAddress()
  {
    return(InetSocketAddress)_socket.getLocalSocketAddress();
  }

  public synchronized void send(InetSocketAddress target, byte[] packet, int packetSize)
  {
    _packet.setSocketAddress(target);
    _packet.setData(packet, 0, packetSize);
    try
    {
      //Traces.logNormal("Sending packet of size "+packetSize+" to "+target);
      _socket.send(_packet);
      //SleepManager.sleep(10);
    }
    catch(IOException ioe)
    {
      ioe.printStackTrace();
    }
  }

  public void receiveAcknowledge()
  {
    try
    {
      _socket.receive(_ackPacket);
      //System.out.println("Received acknowledge");
    }
    catch(IOException ioe)
    {
      ioe.printStackTrace();
    }
  }
}
