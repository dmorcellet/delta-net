package delta.services.communication.udp;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import delta.common.utils.traces.UtilsLoggers;
import delta.services.communication.Message;

public class MainTestUDPEmission
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();

  private static void run()
  {
    Emitter emitter=new Emitter();
    InetSocketAddress target=new InetSocketAddress("localhost", 8000);
    MessageSender ms=new MessageSender(target, emitter);
    int dataSize=300000;
    byte[] data=new byte[dataSize];
    for(int i=0;i<dataSize;i++)
    {
      data[i]=(byte)(i%0xFF);
    }
    Message m=new Message(data, 0, dataSize);
    long now=System.currentTimeMillis();
    int nb=1000;
    for(int i=0;i<nb;i++)
    {
      //System.out.println("Sending message "+(i+1));
      ms.sendMessage(m);
    }
    long now2=System.currentTimeMillis();
    long delta=now2-now;
    _logger.info(delta+"ms for sending "+nb+" messages.");
    double messagesPerSecond=1000*((double)nb/(double)delta);
    _logger.info(messagesPerSecond+" messages per second !");
    double mbits=messagesPerSecond*dataSize;
    _logger.info(mbits/1000000+" Mo/s !");
  }

  /**
   * Main method for this test.
   * @param args Useless.
   */
  public static void main(String[] args)
  {
    run();
  }
}
