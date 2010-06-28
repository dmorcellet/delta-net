package delta.services.mom.server;

import delta.services.mom.common.TestConstants;

public class MainTestSocketServer
{
  /**
   * @param args None usefull.
   */
  public static void main(String[] args)
  {
    ConnectionListener cl=new ConnectionListener(TestConstants.TEST_PORT);
    cl.start(false);
  }
}
