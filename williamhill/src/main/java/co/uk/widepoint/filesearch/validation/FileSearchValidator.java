package co.uk.widepoint.filesearch.validation;

import java.io.File;

/**
 * 
 * Validator to check the parameters required for a basic file search. Note that
 * by the time this class is called we have already validated that we have the
 * correct number of parameters and that they are not null.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */

public class FileSearchValidator implements ParameterValidator
{

	private static final String FILENAME_FLAG = "-f";

	public void validateParameters(String[] args) throws IllegalArgumentException
	{
		validateFileNameFlag(args[0]);
		validateFileName(args[1]);
		validateStartDirectory(args[2]);
	}

	/*
	 * 
	 * The first parameter must be the flag -f indicating that the filename or
	 * regex to match against is coming next
	 * 
	 * @param fileNameFlag
	 */

	protected void validateFileNameFlag(String fileNameFlag)
	{
		if (!FILENAME_FLAG.equals(fileNameFlag))
		{
			throw new IllegalArgumentException("First parameter must be the filename flag -f");
		}
	}

	/*
	 * The second parameter is a filename that we need to match against. Check
	 * we dont have an empty string
	 * 
	 * @param fileNameFlag
	 */
	protected void validateFileName(String fileName)
	{
		if (fileName.trim().length() == 0)
		{
			throw new IllegalArgumentException("Second parameter (filename) cannot be an empty string");
		}

	}

	/*
	 * The third parameter must be a valid directory
	 */
	protected void validateStartDirectory(String directoryName)
	{
		if (directoryName.trim().length() == 0)
		{
			throw new IllegalArgumentException(getDirectoryOrder() + " parameter (start directory) cannot be an empty string");
		}

		File directory = new File(directoryName);
		if (!directory.isDirectory())
		{
			throw new IllegalArgumentException("The directory you have supplied does not exist or is not a valid directory");
		}
	}

	protected String getDirectoryOrder()
	{
		return "Third";
	}

}
