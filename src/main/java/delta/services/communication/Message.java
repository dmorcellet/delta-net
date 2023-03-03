package delta.services.communication;

/**
 * Message.
 * @author DAM
 */
public class Message
{
  private MessageHeader _header;
  private byte[] _data;
  private int _offset;
  private int _length;

  /**
   * Constructor.
   * @param data Data buffer.
   * @param dataOffset Offset of data.
   * @param dataLength Data length.
   */
  public Message(byte[] data, int dataOffset, int dataLength)
  {
    _header=new MessageHeader();
    _data=data;
    _offset=dataOffset;
    _length=dataLength;
  }

  /**
   * Constructor.
   * @param messageSize Message size.
   * @param header
   */
  public Message(int messageSize, MessageHeader header)
  {
    _header=header;
    _data=new byte[messageSize];
    _offset=0;
    _length=messageSize;
  }

  /**
   * Get the message header.
   * @return the message header.
   */
  public MessageHeader getHeader()
  {
    return _header;
  }

  /**
   * Get the message data.
   * @return a data buffer.
   */
  public byte[] getData()
  {
    return _data;
  }

  /**
   * Get the offset of data in the managed buffer.
   * @return An offset.
   */
  public int getOffset()
  {
    return _offset;
  }

  /**
   * Get the size of data in the managed buffer.
   * @return A buffer size.
   */
  public int getSize()
  {
    return _length;
  }
}
