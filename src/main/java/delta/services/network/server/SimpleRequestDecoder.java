package delta.services.network.server;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Decoder for simple requests.
 * @author DAM
 */
public class SimpleRequestDecoder implements RequestDecoder
{
  @Override
  public Request decodeRequest(Client client)
      throws IOException
  {
    DataInputStream dis=client.getDataInputStream();
    return new SimpleRequest(dis.readShort(), dis.readShort());
  }
}
