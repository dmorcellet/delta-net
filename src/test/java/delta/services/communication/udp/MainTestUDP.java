package delta.services.communication.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MainTestUDP
{
  private static final int PORT=8000;
  private static final int NB_DATAGRAMS=10;
  private static final int DATAGRAM_SIZE=10;
  private boolean _emitter;

  public MainTestUDP(boolean emitter)
  {
    _emitter=emitter;
  }

  public void run()
  {
    if(_emitter)
    {
      runEmitter();
    }
    else
    {
      runReceiver();
    }
  }

  public void runEmitter()
  {
    try
    {
      DatagramSocket ds=new DatagramSocket(PORT+1);
      // ou DatagramSocket ds=new DatagramSocket();
      InetSocketAddress addr=new InetSocketAddress(InetAddress.getLocalHost(), PORT);
      for(int i=0;i<NB_DATAGRAMS;i++)
      {
        byte[] b=new byte[DATAGRAM_SIZE];
        for(int j=0;j<DATAGRAM_SIZE;j++)
        {
          b[j]=(byte)(j+i);
        }
        DatagramPacket dp=new DatagramPacket(b, b.length, addr);
        ds.send(dp);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public void runReceiver()
  {
    try
    {
      DatagramSocket ds=new DatagramSocket(PORT);
      for(int i=0;i<NB_DATAGRAMS;i++)
      {
        byte[] b=new byte[DATAGRAM_SIZE];
        DatagramPacket dp=new DatagramPacket(b, b.length);
        ds.receive(dp);
        System.out.println("Received a datagram packet from "+dp.getSocketAddress());
        byte[] data=dp.getData();
        int length=dp.getLength();
        for(int j=0;j<length;j++)
        {
          System.out.print(data[j]+" ");
        }
        System.out.println("");
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Main method for this test.
   * @param args If argument 1 is "RECEIVER", act as a receiver. Otherwise act as an emitter.
   */
  public static void main(String[] args)
  {
    boolean emitter=true;
    if(args.length>=1)
    {
      if(args[0].equalsIgnoreCase("RECEIVER"))
      {
        emitter=false;
      }
    }
    MainTestUDP test=new MainTestUDP(emitter);
    test.run();
  }
}
