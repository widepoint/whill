package co.uk.widepoint.filesearch.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectoryWalkerFactoryTest
{

	@Test
	public void test_noregex_filename_scenario()
	{
		System.setProperty("regex.supported", "false");
		assertTrue(DirectoryWalkerFactory.getInstance().getDirectoryWalker("filePattern", null) instanceof FileSearchDirectoryWalker);
	}

	@Test
	public void test_noregex_content_search_scenario()
	{
		System.setProperty("regex.supported", "false");
		assertTrue(DirectoryWalkerFactory.getInstance().getDirectoryWalker("filePattern",
				"contentPattern") instanceof ContentSearchDirectoryWalker);
	}

	@Test
	public void test_regex_filename_scenario()
	{
		System.clearProperty("regex.supported");
		assertTrue(DirectoryWalkerFactory.getInstance().getDirectoryWalker("filePattern", null) instanceof RegexFileSearchDirectoryWalker);
	}

	@Test
	public void test_regex_content_search_scenario()
	{
		System.clearProperty("regex.supported");
		assertTrue(DirectoryWalkerFactory.getInstance().getDirectoryWalker("filePattern",
				"contentPattern") instanceof RegexContentSearchDirectoryWalker);
	}

}
