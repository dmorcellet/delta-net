package delta.services.communication.udp;

import delta.services.communication.MessageHeader;

/**
 * Constants for the messaging system.
 * @author DAM
 */
public class Constants
{
  /**
   * Maximum packet (fragment) size.
   */
  public static final int MAX_PACKET_SIZE=60000;
  /**
   * Maximum data size in a fraction.
   */
  public static final int MAX_DATA_SIZE_IN_FRACTION=Constants.MAX_PACKET_SIZE-MessageHeader.SIZE-FractionHeader.SIZE;
  /**
   * Offset of message header.
   */
  public static final int MESSAGE_HEADER_OFFSET=0;
  /**
   * Offset of fraction header.
   */
  public static final int FRACTION_HEADER_OFFSET=MessageHeader.SIZE;
  /**
   * Offset of data.
   */
  public static final int DATA_OFFSET=MessageHeader.SIZE+FractionHeader.SIZE;
  /**
   * Minimum size of a legal (fraction) packet.
   */
  public static final int MIN_DATA_SIZE_IN_FRACTION=DATA_OFFSET;

  /**
   * Get the number of fractions to use for a given pay-load size.
   * @param messageSize Pay-load size.
   * @return A number of fractions.
   */
  public static int getNumberOfFractions(int messageSize)
  {
    return(messageSize/Constants.MAX_DATA_SIZE_IN_FRACTION)+((messageSize%Constants.MAX_DATA_SIZE_IN_FRACTION==0)?0:1);
  }
}
