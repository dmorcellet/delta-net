package delta.common.utils;

/**
 * Facilities for raw IO of base types.
 * @author DAM
 */
public class Bits
{
  /*
    Code taken from java.io.Bits !
   */
  /**
   * Read a boolean value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A boolean value.
   */
  public static boolean getBoolean(byte[] b, int off)
  {
    return b[off]!=0;
  }

  /**
   * Read a char value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A char value.
   */
  public static char getChar(byte[] b, int off)
  {
    return(char)(((b[off+1]&0xFF)<<0)+((b[off+0]&0xFF)<<8));
  }

  /**
   * Read a short value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A short value.
   */
  public static short getShort(byte[] b, int off)
  {
    return(short)(((b[off+1]&0xFF)<<0)+((b[off+0]&0xFF)<<8));
  }

  /**
   * Read an integer value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return An integer value.
   */
  public static int getInt(byte[] b, int off)
  {
    return((b[off+3]&0xFF)<<0)+((b[off+2]&0xFF)<<8)+((b[off+1]&0xFF)<<16)+((b[off+0]&0xFF)<<24);
  }

  /**
   * Read a float value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A float value.
   */
  public static float getFloat(byte[] b, int off)
  {
    int i=((b[off+3]&0xFF)<<0)+((b[off+2]&0xFF)<<8)+((b[off+1]&0xFF)<<16)+((b[off+0]&0xFF)<<24);
    return Float.intBitsToFloat(i);
  }

  /**
   * Read a long value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A long value.
   */
  public static long getLong(byte[] b, int off)
  {
    return((b[off+7]&0xFFL)<<0)+((b[off+6]&0xFFL)<<8)+((b[off+5]&0xFFL)<<16)+((b[off+4]&0xFFL)<<24)+((b[off+3]&0xFFL)<<32)+((b[off+2]&0xFFL)<<40)+((b[off+1]&0xFFL)<<48)+((b[off+0]&0xFFL)<<56);
  }

  /**
   * Read a double value.
   * @param b Buffer to read from.
   * @param off Offset to read from.
   * @return A double value.
   */
  public static double getDouble(byte[] b, int off)
  {
    long j=((b[off+7]&0xFFL)<<0)+((b[off+6]&0xFFL)<<8)+((b[off+5]&0xFFL)<<16)+((b[off+4]&0xFFL)<<24)+((b[off+3]&0xFFL)<<32)+((b[off+2]&0xFFL)<<40)+((b[off+1]&0xFFL)<<48)+((b[off+0]&0xFFL)<<56);
    return Double.longBitsToDouble(j);
  }

  /**
   * Write a boolean value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putBoolean(byte[] b, int off, boolean val)
  {
    b[off]=(byte)(val?1:0);
  }

  /**
   * Write a char value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putChar(byte[] b, int off, char val)
  {
    b[off+1]=(byte)(val>>>0);
    b[off+0]=(byte)(val>>>8);
  }

  /**
   * Write a short value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putShort(byte[] b, int off, short val)
  {
    b[off+1]=(byte)(val>>>0);
    b[off+0]=(byte)(val>>>8);
  }

  /**
   * Write an integer value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putInt(byte[] b, int off, int val)
  {
    b[off+3]=(byte)(val>>>0);
    b[off+2]=(byte)(val>>>8);
    b[off+1]=(byte)(val>>>16);
    b[off+0]=(byte)(val>>>24);
  }

  /**
   * Write a float value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putFloat(byte[] b, int off, float val)
  {
    int i=Float.floatToIntBits(val);
    b[off+3]=(byte)(i>>>0);
    b[off+2]=(byte)(i>>>8);
    b[off+1]=(byte)(i>>>16);
    b[off+0]=(byte)(i>>>24);
  }

  /**
   * Write a long value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putLong(byte[] b, int off, long val)
  {
    b[off+7]=(byte)(val>>>0);
    b[off+6]=(byte)(val>>>8);
    b[off+5]=(byte)(val>>>16);
    b[off+4]=(byte)(val>>>24);
    b[off+3]=(byte)(val>>>32);
    b[off+2]=(byte)(val>>>40);
    b[off+1]=(byte)(val>>>48);
    b[off+0]=(byte)(val>>>56);
  }

  /**
   * Write a double value.
   * @param b Buffer to write to.
   * @param off Offset to write to.
   * @param val Value to write.
   */
  public static void putDouble(byte[] b, int off, double val)
  {
    long j=Double.doubleToLongBits(val);
    b[off+7]=(byte)(j>>>0);
    b[off+6]=(byte)(j>>>8);
    b[off+5]=(byte)(j>>>16);
    b[off+4]=(byte)(j>>>24);
    b[off+3]=(byte)(j>>>32);
    b[off+2]=(byte)(j>>>40);
    b[off+1]=(byte)(j>>>48);
    b[off+0]=(byte)(j>>>56);
  }
}
