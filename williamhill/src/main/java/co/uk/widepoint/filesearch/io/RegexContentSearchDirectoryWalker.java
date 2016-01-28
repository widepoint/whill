package co.uk.widepoint.filesearch.io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * RegexContentSearchDirectoryWalker
 * 
 * This is the most complicated file matching algorithm. It uses a regex to
 * match against file name and then a regex to match content within the file.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */

public class RegexContentSearchDirectoryWalker extends ContentSearchDirectoryWalker implements DirectoryWalker
{

	Pattern filePattern;
	Pattern contentPattern;

	public RegexContentSearchDirectoryWalker(String matchString, String contentMatch)
	{
		super(matchString, contentMatch);

		filePattern = Pattern.compile(matchString);
		contentPattern = Pattern.compile(contentMatch);

	}

	/**
	 * 
	 * We are doing the most complex matching here. The filename needs to match
	 * the supplied reges and then we look to see if teh supplied regex matches
	 * any content within the matched files
	 * 
	 * @param filename
	 * @return
	 */
	@Override
	protected boolean isFilenameMatch(File file)
	{
		Matcher matcher = filePattern.matcher(file.getName());
		if (matcher.matches())
		{
			return isContentMatch(file);
		} else
		{
			return false;
		}
	}

	/**
	 * Check if the regex pattern we havve been supplied matches any of the
	 * lines in the file for is contained in the line from the file
	 * 
	 * @param line
	 * @return
	 */
	protected boolean lineMatch(String line)
	{
		Matcher matcher = contentPattern.matcher(line);
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
		return new RegexContentSearchDirectoryWalker(matchString, contentMatch);
	}

}
