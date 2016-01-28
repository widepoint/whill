package co.uk.widepoint.filesearch.io;

import java.io.File;

/**
 * 
 * FileSearchDirectoryWalker
 * 
 * This is the most basic implementation of DirectoryWalker. It matches on exact
 * filename and isnt interested in file contents.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */

public class FileSearchDirectoryWalker extends AbstractDirectoryWalker implements DirectoryWalker
{

	protected String matchString;

	public FileSearchDirectoryWalker(String matchString)
	{
		this.matchString = matchString;
	}

	/**
	 * 
	 * We are doing basic matching here. The filename either matches the one we
	 * are looking for or it doesn't.
	 * 
	 * @param filename
	 * @return
	 */
	@Override
	protected boolean isFilenameMatch(File file)
	{
		return file.getName().equals(matchString);
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
		return new FileSearchDirectoryWalker(matchString);
	}

}
