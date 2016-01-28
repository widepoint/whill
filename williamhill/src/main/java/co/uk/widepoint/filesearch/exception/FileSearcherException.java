package co.uk.widepoint.filesearch.exception;

/**
 * 
 * General purpose application runtime exception to wrap checked exceptions eg IOException that we can't really manage  
 * 
 * 
 * @author David Harvey
 * 
 * Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class FileSearcherException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public FileSearcherException()
	{
		
	}

	public FileSearcherException(String message)
	{
		super(message);
		
	}

	public FileSearcherException(Throwable cause)
	{
		super(cause);
		
	}

	public FileSearcherException(String message, Throwable cause)
	{
		super(message, cause);
		
	}

	public FileSearcherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
