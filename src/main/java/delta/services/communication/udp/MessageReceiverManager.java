package delta.services.communication.udp;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import delta.services.communication.Message;
import delta.services.communication.MessageHeader;

public class MessageReceiverManager
{
  private HashMap<MessageHeader,MessageReceiver> _messageReceivers;
  private Stack<MessageReceiver> _availableMessageReceivers;
  private MessageHeader _header;

  public MessageReceiverManager(int poolSize)
  {
    _messageReceivers=new HashMap<MessageHeader,MessageReceiver>(poolSize);
    _availableMessageReceivers=new Stack<MessageReceiver>();
    for (int i=0; i<poolSize; i++)
    {
      _availableMessageReceivers.push(new MessageReceiver());
    }
    _header=new MessageHeader();
  }

  private MessageReceiver getMessageReceiver()
  {
    MessageReceiver ret=null;
    if (_availableMessageReceivers.isEmpty())
    {
      ret=new MessageReceiver();
    }
    else
    {
      ret=_availableMessageReceivers.pop();
    }
    return ret;
  }

  public int getNumberOfActiveReceivers()
  {
    return _messageReceivers.size();
  }

  private MessageReceiver getReceiver(DatagramPacket fraction)
  {
    MessageReceiver ret=null;
    byte[] data=fraction.getData();
    System.arraycopy(data,Constants.MESSAGE_HEADER_OFFSET,_header.getBytes(),0,
        MessageHeader.SIZE);
    _header.buildFromBytes();
    //System.out.println("Fraction mID="+_header.getMessageID());
    ret=_messageReceivers.get(_header);
    if (ret==null)
    {
      ret=getMessageReceiver();
      MessageHeader newKey=new MessageHeader(_header);
      _messageReceivers.put(newKey,ret);
    }
    return ret;
  }

  /**
   * Indicates whether the given fraction has a good format.
   *
   * @param fraction
   *          Fraction to check.
   * @return true if format ok, false otherwise.
   */
  public boolean checkFraction(DatagramPacket fraction)
  {
    return (fraction.getData().length>=Constants.MIN_DATA_SIZE_IN_FRACTION);
  }

  public Message handleFraction(DatagramPacket fraction)
  {
    Message ret=null;
    //System.out.println("Received packet.");
    if (checkFraction(fraction))
    {
      MessageReceiver receiver=getReceiver(fraction);
      ret=receiver.decodeFraction(fraction);
      if (ret!=null)
      {
        _messageReceivers.remove(_header);
        _availableMessageReceivers.push(receiver);
      }
    }
    //dumpState();
    return ret;
  }

  public void dumpState()
  {
    System.out.println("Active receivers : "+getNumberOfActiveReceivers());
    Set<Map.Entry<MessageHeader,MessageReceiver>> entries=_messageReceivers.entrySet();
    Map.Entry<MessageHeader,MessageReceiver> entry;
    MessageHeader header;
    MessageReceiver receiver;
    for(Iterator<Map.Entry<MessageHeader,MessageReceiver>> it=entries.iterator();it.hasNext();)
    {
      entry=it.next();
      header=entry.getKey();
      receiver=entry.getValue();
      System.out.println("Header : "+header);
      System.out.println("Receiver : "+receiver);
    }
  }
}
