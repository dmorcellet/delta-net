package delta.services.communication.udp;

import delta.common.utils.Bits;

/**
 * Fraction header.
 * @author DAM
 */
public class FractionHeader
{
  /**
   * Fraction header size.
   */
  public static final int SIZE=4;
  private byte[] _header;

  /**
   * Constructor.
   */
  public FractionHeader()
  {
    _header=new byte[SIZE];
  }

  /**
   * Get the fraction identifier.
   * @return an identifier.
   */
  public int getFractionID()
  {
    return Bits.getInt(_header, 0);
  }

  /**
   * Set the fraction identifier.
   * @param id Identifier to set.
   */
  public void setFractionID(int id)
  {
    Bits.putInt(_header, 0, id);
  }

  /**
   * Get the raw bytes for this header.
   * @return A buffer of size <code>SIZE</code>.
   */
  public byte[] getBytes()
  {
    return _header;
  }
}
