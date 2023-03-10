package delta.services.network.server;

/**
 * Simple request, described by:
 * <ul>
 * <li>a function code,
 * <li>a sub-function code.
 * </ul>
 * @author DAM
 */
public class SimpleRequest extends Request
{
  private short _function;
  private short _subFunction;

  /**
   * Constructor.
   * @param function Function code.
   * @param subFunction Sub-function code.
   */
  public SimpleRequest(short function, short subFunction)
  {
    _function=function;
    _subFunction=subFunction;
  }

  /**
   * Get the function code.
   * @return the function code.
   */
  public short getFunction()
  {
    return _function;
  }

  /**
   * Get the sub-function code.
   * @return the sub-function code.
   */
  public short getSubFunction()
  {
    return _subFunction;
  }
}
