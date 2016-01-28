package co.uk.widepoint.filesearch.validation;

/**
 * 
 * Validator to check the parameters required for a extended file search where
 * the filename has to match AND the file needs to contain the specified string
 * or regex. Note that by the time this class is called we have already
 * validated that we have the correct number of parameters and that they are not
 * null.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class ContentSearchValidator extends FileSearchValidator implements ParameterValidator
{

	private static final String CONTENT_FLAG = "-p";

	public void validateParameters(String[] args) throws IllegalArgumentException
	{
		validateFileNameFlag(args[0]);
		validateFileName(args[1]);
		validateContentFlag(args[2]);
		validateContentPattern(args[3]);
		validateStartDirectory(args[4]);
	}

	protected void validateContentFlag(String contentFlag)
	{
		if (!CONTENT_FLAG.equals(contentFlag))
		{
			throw new IllegalArgumentException("Third parameter must be the content match flag -p");
		}
	}

	/*
	 * The content we are going to be looking for within files cant be an empty
	 * string
	 * 
	 * @param contentPattern
	 */
	protected void validateContentPattern(String contentPattern)
	{
		if (contentPattern.trim().length() == 0)
		{
			throw new IllegalArgumentException("Fourth parameter (content pattern) cannot be an empty string");
		}

	}

	protected String getDirectoryOrder()
	{
		return "Fifth";
	}

}
