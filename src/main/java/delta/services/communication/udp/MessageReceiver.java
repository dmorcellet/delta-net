package delta.services.communication.udp;

import java.net.DatagramPacket;
import java.util.BitSet;

import org.apache.log4j.Logger;

import delta.common.utils.traces.UtilsLoggers;
import delta.services.communication.Message;
import delta.services.communication.MessageHeader;

public class MessageReceiver
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();
  private Message _message;
  private MessageHeader _messageHeader;
  private FractionHeader _fractionHeader;
  private BitSet _receivedFractions;

  public MessageReceiver()
  {
    _messageHeader=new MessageHeader();
    _fractionHeader=new FractionHeader();
  }

  private void reset()
  {
    _message=null;
    _receivedFractions.clear();
  }

  public Message decodeFraction(DatagramPacket packet)
  {
    Message ret=null;
    byte[] data=packet.getData();
    System.arraycopy(data,Constants.MESSAGE_HEADER_OFFSET,_messageHeader
        .getBytes(),0,MessageHeader.SIZE);
    System.arraycopy(data,Constants.FRACTION_HEADER_OFFSET,_fractionHeader
        .getBytes(),0,FractionHeader.SIZE);
    _messageHeader.buildFromBytes();
    int messageSize=_messageHeader.getMessageSize();
    int nbFractions=Constants.getNumberOfFractions(messageSize);
    if (_message==null)
    {
      _message=new Message(messageSize,_messageHeader);
      _receivedFractions=new BitSet(nbFractions);
    }
    int fractionID=_fractionHeader.getFractionID();
    if (_receivedFractions.get(fractionID))
    {
      _logger.error("Already received fraction "+fractionID+" for message "+_messageHeader.getMessageID());
    }
    else
    {
      int fractionSize=packet.getLength()-Constants.MIN_DATA_SIZE_IN_FRACTION;
      int offset=fractionID*Constants.MAX_DATA_SIZE_IN_FRACTION;
      System.arraycopy(data,Constants.MIN_DATA_SIZE_IN_FRACTION,_message
          .getData(),offset,fractionSize);
      _receivedFractions.set(fractionID);
      //System.out.println("Fraction fID="+fractionID);
    }
    if (_receivedFractions.cardinality()==nbFractions)
    {
      ret=_message;
      reset();
    }
    return ret;
  }
}
