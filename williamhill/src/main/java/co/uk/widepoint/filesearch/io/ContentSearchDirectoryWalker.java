package co.uk.widepoint.filesearch.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import co.uk.widepoint.filesearch.exception.FileSearcherException;

/**
 * 
 * ContentSearchDirectoryWalker
 * 
 * This does not use regex matching but only does need to match on both filename
 * AND content with the matched file.
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */

public class ContentSearchDirectoryWalker extends FileSearchDirectoryWalker implements DirectoryWalker
{

	protected String contentMatch;

	public ContentSearchDirectoryWalker(String matchString, String contentMatch)
	{
		super(matchString);
		this.contentMatch = contentMatch;

	}

	/**
	 * 
	 * Try and match the filename and then also on expected content within the
	 * file
	 * 
	 * @param filename
	 * @return
	 */
	@Override
	protected boolean isFilenameMatch(File file)
	{
		if (super.isFilenameMatch(file))
		{
			return isContentMatch(file);
		} else
		{
			return false;
		}
	}

	/**
	 * Read through the file line by line and delegate content matching to the
	 * lineMatch method
	 * 
	 * @param file
	 * @return
	 */
	protected boolean isContentMatch(File file)
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null)
			{
				if (lineMatch(line))
				{
					return true;
				}
			}
		} catch (IOException e)
		{
			throw new FileSearcherException("Fatal error reading file " + file.getName(), e);
		} finally
		{
			try
			{
				br.close();
			} catch (IOException e)
			{
			} // ignore failed close

		}
		return false;
	}

	/**
	 * Check if the content string we have been asked to look for is contained
	 * in the line from the file
	 * 
	 * @param line
	 * @return
	 */
	protected boolean lineMatch(String line)
	{
		return line.indexOf(contentMatch) > -1;
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
		return new ContentSearchDirectoryWalker(matchString, contentMatch);
	}

}
