package delta.services.mom.common;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * MOM output stream.
 * @author DAM
 */
public class MomOutputStream extends DataOutputStream
{
  /**
   * Constructor.
   * @param os Output stream.
   * @param bufferSize Buffer size.
   */
  public MomOutputStream(OutputStream os, int bufferSize)
  {
    super(new BufferedOutputStream(os,bufferSize));
  }
}
