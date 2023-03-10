package delta.services.mom.server;

import delta.services.mom.common.TestConstants;

/**
 * Socket server for tests.
 * @author DAM
 */
public class MainTestSocketServer
{
  /**
   * Main method.
   * @param args None useful.
   */
  public static void main(String[] args)
  {
    ConnectionListener cl=new ConnectionListener(TestConstants.TEST_PORT);
    cl.start(false);
  }
}
