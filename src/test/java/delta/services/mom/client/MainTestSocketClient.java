package delta.services.mom.client;

import delta.common.utils.Tools;
import delta.services.mom.common.TestConstants;

public class MainTestSocketClient
{
  /**
   * Main method for this test.
   * @param args None usefull.
   */
  public static void main(String[] args)
  {
    SocketClient client=new SocketClient("localhost",TestConstants.TEST_PORT);
    if (client.connect())
    {
      System.out.println("Connection successfull !");
      Tools.sleep(3000);
      client.close();
      System.out.println("Connection closed !");
    }
    else
    {
      System.out.println("Connection failed !");
    }
  }
}
