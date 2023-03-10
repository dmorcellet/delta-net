package delta.services.network.server;

import java.io.IOException;

/**
 * Request decoder.
 * <p>
 * Builds request descriptions from incoming bytes.
 * @author DAM
 */
public interface RequestDecoder
{
  /**
   * Decode a request.
   * @param client Client to read from.
   * @return the decoded requests.
   * @throws IOException If an I/O error occurs.
   */
  public Request decodeRequest(Client client) throws IOException;
}
