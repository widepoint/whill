package co.uk.widepoint.filesearch.io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * RegexFileSearchDirectoryWalker
 * 
 * This uses regex matching against the filename.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */

public class RegexFileSearchDirectoryWalker extends FileSearchDirectoryWalker implements DirectoryWalker
{

	Pattern pattern;

	public RegexFileSearchDirectoryWalker(String matchString)
	{
		super(matchString);
		pattern = Pattern.compile(matchString);
	}

	/**
	 * 
	 * Check to see if the filename matches the regex we have been provided
	 * 
	 * @param filename
	 * @return
	 */
	@Override
	protected boolean isFilenameMatch(File file)
	{
		Matcher matcher = pattern.matcher(file.getName());
		return matcher.matches();
	}

	/**
	 * 
	 * Return a new FileSearchDirectoryWalker to do basic filename matching
	 * 
	 * @return
	 */
	@Override
	protected DirectoryWalker getDirectoryWalker()
	{
		return new RegexFileSearchDirectoryWalker(matchString);
	}

}
