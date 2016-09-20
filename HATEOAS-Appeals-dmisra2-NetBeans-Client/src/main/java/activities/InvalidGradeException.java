package activities;

public class InvalidGradeException extends RuntimeException
{
    public InvalidGradeException(Exception ex)
    {
        super(ex);
    }
}
