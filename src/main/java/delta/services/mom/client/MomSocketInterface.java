package delta.services.mom.client;

import java.io.InputStream;
import java.io.OutputStream;

import delta.services.mom.common.MomInputStream;
import delta.services.mom.common.MomOutputStream;

public class MomSocketInterface
{
  private SocketClient _socket;
  private MomInputStream _inputStream;
  private MomOutputStream _outputStream;

  public MomSocketInterface(SocketClient socket,int readBuffer, int writeBuffer)
  {
    _socket=socket;
    InputStream is=_socket.getInputStream();
    _inputStream=new MomInputStream(is,readBuffer);
    OutputStream os=_socket.getOutputStream();
    _outputStream=new MomOutputStream(os,writeBuffer);
  }

  public MomInputStream getInputStream()
  {
    return _inputStream;
  }

  public MomOutputStream getOutputStream()
  {
    return _outputStream;
  }

  public void close()
  {
    _socket.close();
  }
}
