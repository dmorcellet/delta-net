package delta.services.communication.udp;

import delta.common.utils.Bits;

public class FractionHeader
{
  public static final int SIZE=4;
  private byte[] _header;

  public FractionHeader()
  {
    _header=new byte[SIZE];
  }

  public int getFractionID()
  {
    return Bits.getInt(_header, 0);
  }

  public void setFractionID(int id)
  {
    Bits.putInt(_header, 0, id);
  }

  public byte[] getBytes()
  {
    return _header;
  }
}
