package delta.services.mom.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * MOM input stream.
 * @author DAM
 */
public class MomInputStream extends DataInputStream
{
  /**
   * Constructor.
   * @param is Input stream.
   * @param bufferSize Buffer size.
   */
  public MomInputStream(InputStream is, int bufferSize)
  {
    super(new BufferedInputStream(is,bufferSize));
  }
}
