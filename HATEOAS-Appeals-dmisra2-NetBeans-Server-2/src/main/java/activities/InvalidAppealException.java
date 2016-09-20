package activities;

public class InvalidAppealException extends RuntimeException 
{
    public InvalidAppealException(Exception ex)
    {
        super(ex);
    }
}
