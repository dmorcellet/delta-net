package delta.services.communication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Packet emitter.
 * @author DAM
 */
public class Emitter
{
  private DatagramSocket _socket;
  private DatagramPacket _packet;
  private DatagramPacket _ackPacket;

  /**
   * Constructor.
   */
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

  /**
   * Get the local address for this emitter.
   * @return An IP address.
   */
  public InetSocketAddress getLocalAddress()
  {
    return(InetSocketAddress)_socket.getLocalSocketAddress();
  }

  /**
   * Send a packet.
   * @param target IP/port to send to.
   * @param packet Packet to send.
   * @param packetSize Packet size.
   */
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

  /**
   * Receive an acknowledge for a packet.
   */
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
