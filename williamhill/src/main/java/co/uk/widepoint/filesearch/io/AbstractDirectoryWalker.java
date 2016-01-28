package co.uk.widepoint.filesearch.io;

import java.io.File;

/**
 * 
 * AbstractDirectoryWalker 
 * 
 * @author David Harvey
 * 
 * Copyright Widepoint Computer Services Ltd 2016
 *
 */

public abstract class AbstractDirectoryWalker
{
 	
	public void traverseDirectory(File directory)
	{

		File[] files = directory.listFiles();

		for (File file : files)
		{
			if (file.isFile())
			{
				if (isFilenameMatch(file))
				{
					System.out.println(file.getPath());
				}

			} else if (file.isDirectory())
			{
				getDirectoryWalker().traverseDirectory(file);
			}

		}

	}

	public AbstractDirectoryWalker()
	{
	}
	
	protected abstract boolean isFilenameMatch(File file);

	/** 
	 * To support recursion a directory walker needs to be able to call recursivley into an instance of the correct type
	 * @return
	 */
	protected abstract DirectoryWalker getDirectoryWalker();

}
