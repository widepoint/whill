package co.uk.widepoint.filesearch.io;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ContentSearchDirectoryWalkerTest
{

	private ContentSearchDirectoryWalker directoryWalker;
	private File file;

	@Before
	public void setUp()
	{
		directoryWalker = new ContentSearchDirectoryWalker("filename.txt", "matchme");
	}

	@Test
	public void test_content_match_start_of_line()
	{
		assertTrue(directoryWalker.lineMatch("matchme at the start of line"));
	}

	@Test
	public void test_content_match_end_of_line()
	{
		assertTrue(directoryWalker.lineMatch("at end of line matchme"));
		assertTrue(directoryWalker.lineMatch("at end of line matchme\n"));
	}

	@Test
	public void test_content_match_middle_of_line()
	{
		assertTrue(directoryWalker.lineMatch("in the middle matchme as well"));
	}

	@Test
	public void test_real_file_match()
	{
		directoryWalker = new ContentSearchDirectoryWalker("matchfile.txt", "matchme");
		assertTrue(directoryWalker.isFilenameMatch(new File("src/test/resources/matchfile.txt")));
	}

}
