package delta.services.communication.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

import delta.common.utils.traces.UtilsLoggers;
import delta.services.communication.Message;

public class Receiver
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();

  private DatagramSocket _socket;
  private DatagramPacket _packet;
  private DatagramPacket _ackPacket;
  private int _port;
  private boolean _isStarted;
  private MessageReceiverManager _receiversManager;

  public Receiver(int port)
  {
    this(port, Constants.MAX_PACKET_SIZE);
  }

  public Receiver(int port, int maxPacketSize)
  {
    _port=port;
    byte[] buffer=new byte[maxPacketSize];
    _packet=new DatagramPacket(buffer, maxPacketSize);
    _ackPacket=new DatagramPacket(new byte[0], 0, 0);
    _isStarted=false;
    _receiversManager=new MessageReceiverManager(1000);
  }

  public synchronized boolean start()
  {
    boolean ret=false;
    try
    {
      _socket=new DatagramSocket(_port);
      ret=true;
      _isStarted=true;
      receivePackets();
    }
    catch(SocketException soe)
    {
      _logger.error("",soe);
    }
    return ret;
  }

  public synchronized void stop()
  {
    if(_socket!=null)
    {
      _socket.close();
      _socket=null;
      _isStarted=false;
    }
  }

  public void receivePackets()
  {
    while(true)
    {
      receivePacket();
    }
  }

  public synchronized boolean receivePacket()
  {
    boolean ret=false;
    if(_isStarted)
    {
      try
      {
        _socket.receive(_packet);
        Message m=_receiversManager.handleFraction(_packet);
        if(m!=null)
        {
          //System.out.println("Received message "+m.getHeader().getMessageID()+". Sending acknowledge.");
          sendAcknowledge();
        }
        ret=true;
      }
      catch(IOException ioe)
      {
        _logger.error("",ioe);
        stop();
      }
    }
    return ret;
  }

  private void sendAcknowledge()
  {
    _ackPacket.setAddress(_packet.getAddress());
    _ackPacket.setPort(_packet.getPort());
    try
    {
      _socket.send(_ackPacket);
    }
    catch(IOException ioe)
    {
      _logger.error("",ioe);
    }
  }
}
