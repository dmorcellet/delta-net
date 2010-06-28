package delta.services.communication.udp;

import java.net.InetSocketAddress;

import delta.services.communication.Message;
import delta.services.communication.MessageHeader;

public class MessageSender
{
  private byte[] _buffer;
  private FractionHeader _fractionHeader;
  private InetSocketAddress _target;
  private Emitter _emitter;
  private int _counter;

  public MessageSender(InetSocketAddress target, Emitter emitter)
  {
    _buffer=new byte[Constants.MAX_PACKET_SIZE];
    _fractionHeader=new FractionHeader();
    _target=target;
    _emitter=emitter;
    _counter=0;
  }

  public void sendMessage(Message m)
  {
    _counter++;
    int size=m.getSize();
    byte[] data=m.getData();
    int offset=m.getOffset();
    MessageHeader mh=m.getHeader();
    mh.setReceiver(_target);
    mh.setSender(_emitter.getLocalAddress());
    mh.setMessageSize(size);
    mh.setMessageID(_counter);
    mh.buildBytes();
    System.arraycopy(m.getHeader().getBytes(), 0, _buffer, Constants.MESSAGE_HEADER_OFFSET, MessageHeader.SIZE);
    int nbFractions=Constants.getNumberOfFractions(size);
    int bytesToSend=size;
    //System.out.println("Nb of fractions : "+nbFractions);
    for(int i=0;i<nbFractions;i++)
    {
      // Build fraction
      _fractionHeader.setFractionID(i);
      System.arraycopy(_fractionHeader.getBytes(), 0, _buffer, Constants.FRACTION_HEADER_OFFSET, FractionHeader.SIZE);
      int fractionSize=bytesToSend;
      if(bytesToSend>Constants.MAX_DATA_SIZE_IN_FRACTION)
      {
        fractionSize=Constants.MAX_DATA_SIZE_IN_FRACTION;
      }
      System.arraycopy(data, offset, _buffer, Constants.DATA_OFFSET, fractionSize);

      // Send fraction
      _emitter.send(_target, _buffer, fractionSize+Constants.DATA_OFFSET);
      // Aftermath
      bytesToSend-=fractionSize;
    }
    waitAcknowledge();
  }

  private void waitAcknowledge()
  {
    _emitter.receiveAcknowledge();
  }
}
