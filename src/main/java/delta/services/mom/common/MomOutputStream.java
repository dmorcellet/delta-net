package delta.services.mom.common;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class MomOutputStream extends DataOutputStream
{
  public MomOutputStream(OutputStream os, int bufferSize)
  {
    super(new BufferedOutputStream(os,bufferSize));
  }
}
