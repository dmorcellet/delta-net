package delta.services.communication;

public class Message
{
  private MessageHeader _header;
  private byte[] _data;
  private int _offset;
  private int _lenght;

  public Message(byte[] data, int dataOffset, int dataLength)
  {
    _header=new MessageHeader();
    _data=data;
    _offset=dataOffset;
    _lenght=dataLength;
  }

  public Message(int messageSize, MessageHeader header)
  {
    _header=header;
    _data=new byte[messageSize];
    _offset=0;
    _lenght=messageSize;
  }

  public MessageHeader getHeader()
  {
    return _header;
  }

  public byte[] getData()
  {
    return _data;
  }

  public int getOffset()
  {
    return _offset;
  }

  public int getSize()
  {
    return _lenght;
  }
}
