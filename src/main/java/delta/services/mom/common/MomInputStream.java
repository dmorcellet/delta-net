package delta.services.mom.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class MomInputStream extends DataInputStream
{
  public MomInputStream(InputStream is, int bufferSize)
  {
    super(new BufferedInputStream(is,bufferSize));
  }
}
