package delta.services.network.server;

import java.io.DataInputStream;
import java.io.IOException;

public class SimpleRequestDecoder implements RequestDecoder
{
  public Request decodeRequest(Client client)
      throws IOException
  {
    DataInputStream dis=client.getDataInputStream();
    return new SimpleRequest(dis.readShort(), dis.readShort());
  }
}
