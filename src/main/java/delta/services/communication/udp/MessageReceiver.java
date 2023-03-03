package delta.services.communication.udp;

import java.net.DatagramPacket;
import java.util.BitSet;

import org.apache.log4j.Logger;

import delta.services.communication.Message;
import delta.services.communication.MessageHeader;

/**
 * Message receiver.
 * @author DAM
 */
public class MessageReceiver
{
  private static final Logger LOGGER=Logger.getLogger(MessageReceiver.class);
  private Message _message;
  private MessageHeader _messageHeader;
  private FractionHeader _fractionHeader;
  private BitSet _receivedFractions;

  /**
   * Constructor.
   */
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

  /**
   * Decode a fraction.
   * @param packet Input packet.
   * @return A <code>Message</code> if a message was fully received, <code>null</code> otherwise.
   */
  public Message decodeFraction(DatagramPacket packet)
  {
    Message ret=null;
    byte[] data=packet.getData();
    System.arraycopy(data,Constants.MESSAGE_HEADER_OFFSET,_messageHeader.getBytes(),0,MessageHeader.SIZE);
    System.arraycopy(data,Constants.FRACTION_HEADER_OFFSET,_fractionHeader.getBytes(),0,FractionHeader.SIZE);
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
      LOGGER.error("Already received fraction "+fractionID+" for message "+_messageHeader.getMessageID());
    }
    else
    {
      int fractionSize=packet.getLength()-Constants.MIN_DATA_SIZE_IN_FRACTION;
      int offset=fractionID*Constants.MAX_DATA_SIZE_IN_FRACTION;
      System.arraycopy(data,Constants.DATA_OFFSET,_message.getData(),offset,fractionSize);
      _receivedFractions.set(fractionID);
    }
    if (_receivedFractions.cardinality()==nbFractions)
    {
      ret=_message;
      reset();
    }
    return ret;
  }
}
