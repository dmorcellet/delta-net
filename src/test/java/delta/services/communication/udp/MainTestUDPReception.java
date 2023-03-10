package delta.services.communication.udp;

/**
 * Test UDP reception.
 * @author DAM
 */
public class MainTestUDPReception
{
  private static void run()
  {
    Receiver receiver=new Receiver(8000);
    receiver.start();
  }

  /**
   * Main method for this test.
   * @param args Useless.
   */
  public static void main(String[] args)
  {
    run();
  }
}
