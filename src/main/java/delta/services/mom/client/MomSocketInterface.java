package delta.services.mom.client;

import java.io.InputStream;
import java.io.OutputStream;

import delta.services.mom.common.MomInputStream;
import delta.services.mom.common.MomOutputStream;

/**
 * Socket level interface 
 * @author DAM
 */
public class MomSocketInterface
{
  private SocketClient _socket;
  private MomInputStream _inputStream;
  private MomOutputStream _outputStream;

  /**
   * Constructor.
   * @param socket Socket to use.
   * @param readBuffer Size of the reception buffer.
   * @param writeBuffer Size of the emissio buffer.
   */
  public MomSocketInterface(SocketClient socket,int readBuffer, int writeBuffer)
  {
    _socket=socket;
    InputStream is=_socket.getInputStream();
    _inputStream=new MomInputStream(is,readBuffer);
    OutputStream os=_socket.getOutputStream();
    _outputStream=new MomOutputStream(os,writeBuffer);
  }

  /**
   * Get the input stream.
   * @return the input stream.
   */
  public MomInputStream getInputStream()
  {
    return _inputStream;
  }

  /**
   * Get the output stream.
   * @return the output stream.
   */
  public MomOutputStream getOutputStream()
  {
    return _outputStream;
  }

  /**
   * Close.
   */
  public void close()
  {
    _socket.close();
  }
}
