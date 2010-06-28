package delta.services.network.server;

public class SimpleRequest extends Request
{
  private short _function;
  private short _subFunction;

  public SimpleRequest(short function, short subFunction)
  {
    _function=function;
    _subFunction=subFunction;
  }

  public short getFunction()
  {
    return _function;
  }

  public short getSubFunction()
  {
    return _subFunction;
  }
}
