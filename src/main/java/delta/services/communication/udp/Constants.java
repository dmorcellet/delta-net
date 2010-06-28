package delta.services.communication.udp;

import delta.services.communication.MessageHeader;

public class Constants
{
  public static final int MAX_PACKET_SIZE=60000;
  public static final int MAX_DATA_SIZE_IN_FRACTION=Constants.MAX_PACKET_SIZE-MessageHeader.SIZE-FractionHeader.SIZE;
  public static final int MESSAGE_HEADER_OFFSET=0;
  public static final int FRACTION_HEADER_OFFSET=MessageHeader.SIZE;
  public static final int DATA_OFFSET=MessageHeader.SIZE+FractionHeader.SIZE;
  public static final int MIN_DATA_SIZE_IN_FRACTION=DATA_OFFSET;

  public static int getNumberOfFractions(int messageSize)
  {
    return(messageSize/Constants.MAX_DATA_SIZE_IN_FRACTION)+((messageSize%Constants.MAX_DATA_SIZE_IN_FRACTION==0)?0:1);
  }
}
