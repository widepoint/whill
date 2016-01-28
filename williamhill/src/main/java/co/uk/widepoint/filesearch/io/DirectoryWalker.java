package co.uk.widepoint.filesearch.io;

import java.io.File;

/**
 * 
 * Interface DirectoryWalker
 * 
 * Concrete implementations of this will either match on filename or regex and
 * will either check contents of a file or just be happy with a filename match.
 * 
 * Effectively using Strategy to apply different approaches to matching
 * 
 * @author David Harvey
 * 
 *         Copyright Widepoint Computer Services Ltd 2016
 *
 */
public interface DirectoryWalker
{

	/**
	 * Traverse the supplied directory and either check for files to see if the
	 * name matches requirements OR recursively call DirectoryWalker for
	 * directories
	 * 
	 * @param directory
	 */
	public void traverseDirectory(File directory);

}
