package delta.services.communication.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Test class for UDP messaging.
 * @author DAM
 */
public class MainTestUDP
{
  private static final int PORT=8000;
  private static final int NB_DATAGRAMS=10;
  private static final int DATAGRAM_SIZE=10;
  private boolean _emitter;

  /**
   * Constructor.
   * @param emitter Run as an emitter or as a receiver.
   */
  public MainTestUDP(boolean emitter)
  {
    _emitter=emitter;
  }

  /**
   * Run.
   */
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

  /**
   * Run the emitter.
   */
  public void runEmitter()
  {
    DatagramSocket ds=null;
    try
    {
      ds=new DatagramSocket(PORT+1);
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
    finally
    {
      if (ds!=null)
      {
        ds.close();
      }
    }
  }

  /**
   * Run the receiver.
   */
  public void runReceiver()
  {
    DatagramSocket ds=null;
    try
    {
      ds=new DatagramSocket(PORT);
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
    finally
    {
      if (ds!=null)
      {
        ds.close();
      }
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
