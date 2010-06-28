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

import delta.common.utils.traces.UtilsLoggers;

public class Client
{
  private static final Logger _logger=UtilsLoggers.getServicesLogger();

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
        _logger.error("",ioException);
      }
    }
    return _inputStream;
  }

  public BufferedInputStream getBufferedInputStream()
  {
    if(_bufferedInputStream==null)
    {
      _bufferedInputStream=new BufferedInputStream(getInputStream());
    }
    return _bufferedInputStream;
  }

  public DataInputStream getDataInputStream()
  {
    if(_dataInputStream==null)
    {
      _dataInputStream=new DataInputStream(getBufferedInputStream());
    }
    return _dataInputStream;
  }

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
        _logger.error("",ioException);
      }
    }
    return _outputStream;
  }

  public BufferedOutputStream getBufferedOutputStream()
  {
    if(_bufferedOutputStream==null)
    {
      _bufferedOutputStream=new BufferedOutputStream(getOutputStream());
    }
    return _bufferedOutputStream;
  }

  public DataOutputStream getDataOutputStream()
  {
    if(_dataOutputStream==null)
    {
      _dataOutputStream=new DataOutputStream(getBufferedOutputStream());
    }
    return _dataOutputStream;
  }

  public BufferedWriter getBufferedWriter()
  {
    if(_bufferedWriter==null)
    {
      _bufferedWriter=new BufferedWriter(new OutputStreamWriter(getOutputStream()));
    }
    return _bufferedWriter;
  }
}
