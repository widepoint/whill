package co.uk.widepoint.filesearch.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import co.uk.widepoint.filesearch.exception.FileSearcherException;

/**
 * 
 * ErrorWriter
 * 
 * Format and write error messages
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class ErrorWriter
{

	Writer outputStreamWriter;

	/**
	 * Use System err as the default error stream if nothing has been directly
	 * provided in the constructor
	 */
	public ErrorWriter()
	{
		this(System.err);
	}

	public ErrorWriter(OutputStream outputStream)
	{
		outputStreamWriter = new OutputStreamWriter(outputStream);
	}

	public void writeError(String message, Exception e)
	{
		StringBuilder messageBuilder = new StringBuilder(message);
		messageBuilder.append(" : ERROR - ");
		messageBuilder.append(e.getMessage());
		messageBuilder.append("\n");
		try
		{
			outputStreamWriter.write(messageBuilder.toString());
			outputStreamWriter.flush();
		} catch (IOException e1)
		{
			throw new FileSearcherException("Error writing to error output stream", e);
		}
	}

}
