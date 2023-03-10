package delta.services.network.server;

/**
 * Request handlers.
 * @author DAM
 */
public interface RequestHandler
{
  /**
   * Handle a request.
   * @param r Request to handle.
   * @return <code>true</code> if handling succeeded, <code>false</code> otherwise.
   */
  boolean handleRequest(Request r);
}
