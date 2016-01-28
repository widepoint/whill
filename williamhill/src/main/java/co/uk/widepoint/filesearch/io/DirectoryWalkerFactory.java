package co.uk.widepoint.filesearch.io;

/**
 * 
 * Static singleton factory to return the correct DirectoryWalker implementation
 * 
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class DirectoryWalkerFactory
{

	private static final DirectoryWalkerFactory theInstance = new DirectoryWalkerFactory();

	/*
	 * Flag to indicat that we are NOT using regex matching. By default this
	 * will be false ie we will do regex matching. To switch off the java system
	 * parameter regex.supported must be supplied at runtime with a value of
	 * false eg -Dregex.supported=false
	 */
	private boolean noregex;

	private DirectoryWalkerFactory()
	{
		/*
		 * Default to regex being supported
		 */
		String noregexS = System.getProperty("regex.supported", "true");
		noregex = (noregexS.equals("false"));
	}

	public static DirectoryWalkerFactory getInstance()
	{
		return theInstance;
	}

	/**
	 * 
	 * Return appropriate DirectoryWalker depending on whether we are just
	 * looking at filename or whether we are also dealing with file content.
	 * Also the implementation varies depending of whether regex patterns need
	 * to be supported or not.
	 * 
	 * @return
	 */
	public DirectoryWalker getDirectoryWalker(String fileMatchString, String contentPattern)
	{

		/*
		 * If contentPattern is not null then we need to look at file contents
		 */
		if (contentPattern == null)
		{
			if (noregex)
			{
				return new FileSearchDirectoryWalker(fileMatchString);
			} else
			{
				return new RegexFileSearchDirectoryWalker(fileMatchString);
			}
		} else
		{
			if (noregex)
			{
				return new ContentSearchDirectoryWalker(fileMatchString, contentPattern);
			} else
			{
				return new RegexContentSearchDirectoryWalker(fileMatchString, contentPattern);
			}
		}

	}

}
