
public class MatrixException extends Exception
{
    public String message;

    MatrixException(String message)
    {
      this.message = message;
    }

    public String getMessage()
    {
      return message;
    }
}
