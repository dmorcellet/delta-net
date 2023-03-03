package delta.services.communication;

import java.net.InetSocketAddress;

import delta.common.utils.Bits;

/**
 * Message header.
 * @author dm
 */
public class MessageHeader
{
  private static final int DEFAULT_PORT=1000;
  private static final InetSocketAddress DEFAULT_ADDRESS=new InetSocketAddress("localhost", DEFAULT_PORT);
  /**
   * Size of a message header.
   */
  public static final int SIZE=(4+2)*2+4+4+1;
  // Emitter
  private byte[] _emitterAddress;
  private short _emitterPort;
  // Receiver
  private byte[] _receiverAddress;
  private short _receiverPort;
  // Message attributes
  private int _messageID;
  private int _messageSize;
  private byte _priority;

  private byte[] _header;

  /**
   * Copy constructor.
   * @param header Source header.
   */
  public MessageHeader(MessageHeader header)
  {
    init();
    System.arraycopy(header._header,0,_header,0,_header.length);
    buildFromBytes();
  }

  /**
   * Constructor.
   */
  public MessageHeader()
  {
    init();
  }

  private void init()
  {
    _emitterAddress=DEFAULT_ADDRESS.getAddress().getAddress();
    _emitterPort=DEFAULT_PORT;
    _receiverAddress=DEFAULT_ADDRESS.getAddress().getAddress();
    _receiverPort=DEFAULT_PORT;
    _header=new byte[SIZE];
  }

  /**
   * Set the sender.
   * @param sender IP/port to set for the sender.
   */
  public void setSender(InetSocketAddress sender)
  {
    _emitterAddress=sender.getAddress().getAddress();
    _emitterPort=(short)sender.getPort();
  }

  /**
   * Set the receiver.
   * @param receiver IP/port to set for the receiver.
   */
  public void setReceiver(InetSocketAddress receiver)
  {
    _receiverAddress=receiver.getAddress().getAddress();
    _receiverPort=(short)receiver.getPort();
  }

  /**
   * Get the message identifier.
   * @return a message identifier.
   */
  public int getMessageID()
  {
    return _messageID;
  }

  /**
   * Set the message identifier.
   * @param id Identifier to set.
   */
  public void setMessageID(int id)
  {
    _messageID=id;
  }

  /**
   * Get the message (payload) size.
   * @return A size.
   */
  public int getMessageSize()
  {
    return _messageSize;
  }

  /**
   * Set the message (payload) size.
   * @param size Size in bytes.
   */
  public void setMessageSize(int size)
  {
    _messageSize=size;
  }

  /**
   * Build header buffer from object attributes.
   */
  public void buildBytes()
  {
    int n=0;
    System.arraycopy(_emitterAddress, 0, _header, n, 4);
    n+=4;
    Bits.putShort(_header, n, _emitterPort);
    n+=2;
    System.arraycopy(_receiverAddress, 0, _header, n, 4);
    n+=4;
    Bits.putShort(_header, n, _receiverPort);
    n+=2;
    Bits.putInt(_header, n, _messageID);
    n+=4;
    Bits.putInt(_header, n, _messageSize);
    n+=4;
    _header[n]=_priority;
  }

  /**
   * Setup object attributes from header buffer.
   */
  public void buildFromBytes()
  {
    int n=0;
    System.arraycopy(_header, n, _emitterAddress, 0, 4);
    n+=4;
    _emitterPort=Bits.getShort(_header, n);
    n+=2;
    System.arraycopy(_header, n, _receiverAddress, 0, 4);
    n+=4;
    _receiverPort=Bits.getShort(_header, n);
    n+=2;
    _messageID=Bits.getInt(_header, n);
    n+=4;
    _messageSize=Bits.getInt(_header, n);
    n+=4;
    _priority=_header[n];
  }

  /**
   * Get the header buffer.
   * @return A bytes buffer.
   */
  public byte[] getBytes()
  {
    return _header;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(this==obj) return true;

    if(obj instanceof MessageHeader)
    {
      MessageHeader anotherMessageHeader=(MessageHeader)obj;
      byte[] anotherBuffer=anotherMessageHeader.getBytes();
      if(_header.length!=anotherBuffer.length)
        return false;
      int l=_header.length;
      for(int i=0;i<l;i++)
      {
        if(_header[i]!=anotherBuffer[i])
          return false;
      }
      return true;
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return _messageID;
  }

  @Override
  public String toString()
  {
    StringBuffer sb=new StringBuffer();
    sb.append("Header : MessageID=");
    sb.append(_messageID);
    sb.append(", MessageSize=");
    sb.append(_messageSize);
    return sb.toString();
  }
}
