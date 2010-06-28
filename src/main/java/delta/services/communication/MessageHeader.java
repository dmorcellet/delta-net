package delta.services.communication;

import java.net.InetSocketAddress;

import delta.common.utils.Bits;

public class MessageHeader extends Object
{
  private static final int DEFAULT_PORT=1000;
  private static final InetSocketAddress DEFAULT_ADDRESS=new InetSocketAddress("localhost", DEFAULT_PORT);
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

  public MessageHeader(MessageHeader header)
  {
    init();
    System.arraycopy(header._header,0,_header,0,_header.length);
    buildFromBytes();
  }

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

  public void setSender(InetSocketAddress sender)
  {
    _emitterAddress=sender.getAddress().getAddress();
    _emitterPort=(short)sender.getPort();
  }

  public void setReceiver(InetSocketAddress receiver)
  {
    _receiverAddress=receiver.getAddress().getAddress();
    _receiverPort=(short)receiver.getPort();
  }

  public int getMessageID()
  {
    return _messageID;
  }

  public void setMessageID(int id)
  {
    _messageID=id;
  }

  public int getMessageSize()
  {
    return _messageSize;
  }

  public void setMessageSize(int size)
  {
    _messageSize=size;
  }

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
