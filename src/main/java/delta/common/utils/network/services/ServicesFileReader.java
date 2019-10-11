package delta.common.utils.network.services;

import java.io.File;
import java.util.StringTokenizer;

import delta.common.utils.files.TextFileReader;

/**
 * Utility class to read network services information from a file.<br>
 * It reads files constituted of lines which contain white separated fields :
 * service_name, ip_port and optional hostname.<br>
 * Comments begin with "#".<br>
 * For instance :<br>
 * <br>
 * <i>#service_name     ip_port     hostname<br>
 * my_service         1000       tequila<br>
 * another_service    1001<br></i>
 * <br>
 */
public class ServicesFileReader
{
  private static final String COMMENT_PREFIX = "#";
  private static final String SEPARATORS = " \t";

  /**
   * Builds a service register from a file.
   * @param filename file to be read.
   * @return Newly built ServiceRegister instance or exception.
   */
  public NetworkServicesRegistry parseServices(File filename)
  {
    TextFileReader parser=new TextFileReader(filename);
    if (!parser.start()) return null;

    NetworkServicesRegistry ret=new NetworkServicesRegistry();
    ServiceInfo location=null;
    String line;
    String serviceName;
    String hostName;
    String portStr;
    short portNumber;
    while(true)
    {
      line=parser.getNextLine();
      if (line==null) break;
      if (line.startsWith(COMMENT_PREFIX)) continue;
      serviceName="";
      hostName=null;
      portNumber=-1;
      StringTokenizer st=new StringTokenizer(line,SEPARATORS);
      if (st.hasMoreTokens()) serviceName=st.nextToken(); else continue;
      if (st.hasMoreTokens()) portStr=st.nextToken(); else continue;
      try { portNumber=Short.parseShort(portStr); }
      catch(NumberFormatException nfe) { continue; }
      if (st.hasMoreTokens()) hostName=st.nextToken();
      location=new ServiceInfo(serviceName,hostName,portNumber);
      ret.addServiceInfo(location);
    }
    parser.terminate();
    return ret;
  }
}
