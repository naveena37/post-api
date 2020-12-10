package nz.co.post.exception;

/**
 * Exception thrown when the Json object could not be parsed.
 */
public class JsonParseException
    extends RuntimeException
{

  public JsonParseException(String message)
  {
    super(message);
  }

  public JsonParseException()
  {
    super();
  }

  public JsonParseException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public JsonParseException(Throwable cause)
  {
    super(cause);
  }
}
