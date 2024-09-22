package delta.common.utils.network;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.common.utils.network.services.NetworkServices;
import delta.common.utils.network.services.ServiceInfo;

/**
 * Services test.
 * @author DAM
 */
@DisplayName("Services test")
class TestServices
{
  /**
   * Test services parsing.
   */
  @Test
  void testServicesParsing()
  {
    ServiceInfo sl=NetworkServices.getInstance().getServiceByName("testService");
    System.out.println("Service : "+sl);
  }
}
