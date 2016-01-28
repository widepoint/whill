package co.uk.widepoint.filesearch;

import java.io.File;

import co.uk.widepoint.filesearch.io.DirectoryWalker;
import co.uk.widepoint.filesearch.io.DirectoryWalkerFactory;
import co.uk.widepoint.filesearch.output.ErrorWriter;
import co.uk.widepoint.filesearch.validation.ContentSearchValidator;
import co.uk.widepoint.filesearch.validation.FileSearchValidator;
import co.uk.widepoint.filesearch.validation.ParameterValidator;

/**
 * 
 * Main entry class into the directory search utility
 * 
 * Usage: java FileSearcher.class -f "filename" <directory>
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class FileSearcher
{

	private static final String USAGE_TEMPLATE = "Usage: java FileSearcher.class -f filenamePattern [ -p contentPattern ] <Directory>";

	protected ParameterValidator parameterValidator;

	protected File startDirectory;

	protected String fileMatchExpression;

	protected String contentMatchExpression;

	ErrorWriter errorWriter;

	public FileSearcher()
	{
		errorWriter = new ErrorWriter();

	}

	/**
	 * 
	 * This is the main entry point to the directory search utility.
	 * 
	 * @param args
	 */
	public void runSearch(String[] args)
	{
		try
		{
			validateArguments(args);

			DirectoryWalker directoryWalker = getDirectoryWalker();
			directoryWalker.traverseDirectory(startDirectory);

		} catch (IllegalArgumentException e)
		{
			errorWriter.writeError(USAGE_TEMPLATE, e);
			throw e;
		}

	}

	protected void validateArguments(String[] args)
	{

		if (args == null || (args.length != 3 && args.length != 5))
		{
			throw new IllegalArgumentException("Invalid number of parameters");
		}

		/*
		 * Use a different parameter validator depending on the number of
		 * arguments supplied
		 */
		if (args.length == 3)
		{
			parameterValidator = new FileSearchValidator();
		} else
		{
			parameterValidator = new ContentSearchValidator();
		}

		parameterValidator.validateParameters(args);

		setInstanceFields(args);

	}

	protected void setInstanceFields(String[] args)
	{
		/*
		 * At this point we know that we have valid parameters
		 */
		fileMatchExpression = args[1];
		startDirectory = new File(args[2]);
		if (args.length == 5)
		{
			contentMatchExpression = args[3];
			startDirectory = new File(args[4]);
		}

	}

	/**
	 * 
	 * Get appropriate DirectoryWalker depending on whether we are just looking
	 * at filename or whether we are also dealing with file content. Also the
	 * implementation varies depending of whether regex patterns need to be
	 * supported or not.
	 * 
	 * @return
	 */
	protected DirectoryWalker getDirectoryWalker()
	{
		return DirectoryWalkerFactory.getInstance().getDirectoryWalker(fileMatchExpression, contentMatchExpression);
	}

	public static void main(String[] args)
	{

		try
		{
			FileSearcher fileSearcher = new FileSearcher();
			fileSearcher.runSearch(args);
		} catch (Throwable t)
		{
			t.printStackTrace();
			System.exit(1);
		}

	}

}
