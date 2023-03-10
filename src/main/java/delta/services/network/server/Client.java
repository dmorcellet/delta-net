package delta.services.network.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Client.
 * @author DAM
 */
public class Client
{
  private static final Logger LOGGER=Logger.getLogger(Client.class);

  private Socket _socket;
  private InputStream _inputStream;
  private BufferedInputStream _bufferedInputStream;
  private DataInputStream _dataInputStream;
  private BufferedReader _bufferedReader;
  private OutputStream _outputStream;
  private BufferedOutputStream _bufferedOutputStream;
  private DataOutputStream _dataOutputStream;
  private BufferedWriter _bufferedWriter;

  Client(Socket clientSocket)
  {
    _socket=clientSocket;
  }

  private InputStream getInputStream()
  {
    if(_inputStream==null)
    {
      try
      {
        _inputStream=_socket.getInputStream();
      }
      catch(IOException ioException)
      {
        LOGGER.error("",ioException);
      }
    }
    return _inputStream;
  }

  /**
   * Get the input stream.
   * @return the input stream.
   */
  public BufferedInputStream getBufferedInputStream()
  {
    if(_bufferedInputStream==null)
    {
      _bufferedInputStream=new BufferedInputStream(getInputStream());
    }
    return _bufferedInputStream;
  }

  /**
   * Get the data input stream.
   * @return the data input stream.
   */
  public DataInputStream getDataInputStream()
  {
    if(_dataInputStream==null)
    {
      _dataInputStream=new DataInputStream(getBufferedInputStream());
    }
    return _dataInputStream;
  }

  /**
   * Get the buffered reader.
   * @return the buffered reader.
   */
  public BufferedReader getBufferedReader()
  {
    if(_bufferedReader==null)
    {
      _bufferedReader=new BufferedReader(new InputStreamReader(getInputStream()));
    }
    return _bufferedReader;
  }

  private OutputStream getOutputStream()
  {
    if(_outputStream==null)
    {
      try
      {
        _outputStream=_socket.getOutputStream();
      }
      catch(IOException ioException)
      {
        LOGGER.error("",ioException);
      }
    }
    return _outputStream;
  }

  /**
   * Get the buffered output stream.
   * @return the buffered output stream.
   */
  public BufferedOutputStream getBufferedOutputStream()
  {
    if(_bufferedOutputStream==null)
    {
      _bufferedOutputStream=new BufferedOutputStream(getOutputStream());
    }
    return _bufferedOutputStream;
  }

  /**
   * Get the data output stream.
   * @return the data output stream.
   */
  public DataOutputStream getDataOutputStream()
  {
    if(_dataOutputStream==null)
    {
      _dataOutputStream=new DataOutputStream(getBufferedOutputStream());
    }
    return _dataOutputStream;
  }

  /**
   * Get the buffered writer.
   * @return the buffered writer.
   */
  public BufferedWriter getBufferedWriter()
  {
    if(_bufferedWriter==null)
    {
      _bufferedWriter=new BufferedWriter(new OutputStreamWriter(getOutputStream()));
    }
    return _bufferedWriter;
  }
}
