package co.uk.widepoint.filesearch.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class RegexContentSearchDirectoryWalkerTest
{

	private RegexContentSearchDirectoryWalker directoryWalker;
	private File file;

	@Before
	public void setUp()
	{

	}

	@Test
	public void test_content_match_start_of_line()
	{
		directoryWalker = new RegexContentSearchDirectoryWalker("filename.txt", "^matchme.*$");
		assertTrue(directoryWalker.lineMatch("matchme at the start of line"));
	}

	@Test
	public void test_content_match_end_of_line()
	{
		directoryWalker = new RegexContentSearchDirectoryWalker("filename.txt", ".*matchme$");
		assertTrue(directoryWalker.lineMatch("at end of line matchme"));
		assertFalse(directoryWalker.lineMatch("at end of line matchme\n"));
	}

	@Test
	public void test_content_match_middle_of_line()
	{
		directoryWalker = new RegexContentSearchDirectoryWalker("filename.txt", ".*matchme.*");
		assertTrue(directoryWalker.lineMatch("in the middle matchme as well"));
	}

	@Test
	public void test_complex_match_middle_of_line()
	{
		directoryWalker = new RegexContentSearchDirectoryWalker("filename.txt", ".*m[a-z]t..me.*");
		assertTrue(directoryWalker.lineMatch("in the middle matchme as well"));
	}

	@Test
	public void test_real_file_match()
	{
		directoryWalker = new RegexContentSearchDirectoryWalker("matchfile.txt", ".*matchme.*");
		assertTrue(directoryWalker.isFilenameMatch(new File("src/test/resources/matchfile.txt")));
	}

}
