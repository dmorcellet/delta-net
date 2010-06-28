package delta.services.network.server;

import java.io.IOException;

public interface RequestDecoder
{
  public Request decodeRequest(Client client)
      throws IOException;
}
