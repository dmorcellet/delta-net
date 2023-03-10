package delta.common.utils.network.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import delta.common.utils.configuration.Configuration;
import delta.common.utils.configuration.Configurations;

/**
 * Facade for the network services registry.
 * @author DAM
 */
public final class NetworkServices
{
  private static final Logger LOGGER=Logger.getLogger(NetworkServices.class);

  private static NetworkServices _instance;

  private List<NetworkServicesRegistry> _serviceRegistries;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static NetworkServices getInstance()
  {
    synchronized (NetworkServices.class)
    {
      if (_instance==null)
      {
        _instance=new NetworkServices();
      }
      return _instance;
    }
  }

  private NetworkServices()
  {
    _serviceRegistries=new ArrayList<NetworkServicesRegistry>();
    parserServiceFiles();
  }

  /**
   * Get a service description using its name.
   * @param serviceName Name of the service to use.
   * @return A description or <code>null</code> if not found.
   */
  public ServiceInfo getServiceByName(String serviceName)
  {
    ServiceInfo service=null;
    int nb=_serviceRegistries.size();
    NetworkServicesRegistry register;
    for(int i=0;i<nb;i++)
    {
      register=_serviceRegistries.get(i);
      service=register.getService(serviceName);
      if (service!=null)
      {
        break;
      }
    }
    return service;
  }

  private void parserServiceFiles()
  {
    ServicesFileReader reader=new ServicesFileReader();
    NetworkServicesRegistry register=null;
    Configuration cfg=Configurations.getConfiguration();
    File path=cfg.getFileValue("NETWORK","SERVICES",null);
    if (path!=null)
    {
      register=reader.parseServices(path);
    }
    if (register!=null)
    {
      _serviceRegistries.add(register);
    }
    else
    {
      LOGGER.error("Could not parse service file !");
    }
  }
}
