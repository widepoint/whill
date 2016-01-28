package co.uk.widepoint.filesearch.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class RegexFileSearchDirectoryWalkerTest
{

	private RegexFileSearchDirectoryWalker directoryWalker;
	private File file;

	@Before
	public void setUp()
	{
		file = new File("filename.txt");

	}

	@Test
	public void test_matching_filename()
	{
		/*
		 * works because the . in the regex just matches the . in the filename
		 */
		directoryWalker = new RegexFileSearchDirectoryWalker("filename.txt");
		assertTrue(directoryWalker.isFilenameMatch(file));
	}

	@Test
	public void test_matching_filename_prefix()
	{
		directoryWalker = new RegexFileSearchDirectoryWalker("f.*");
		assertTrue(directoryWalker.isFilenameMatch(file));
	}

	@Test
	public void test_matching_filename_suffix()
	{
		directoryWalker = new RegexFileSearchDirectoryWalker(".*\\.txt");
		assertTrue(directoryWalker.isFilenameMatch(file));
	}

	@Test
	public void test_invalid_filename_suffix()
	{
		directoryWalker = new RegexFileSearchDirectoryWalker(".*\\.doc");
		assertFalse(directoryWalker.isFilenameMatch(file));
	}

	@Test
	public void test_complex_filename_match()
	{
		directoryWalker = new RegexFileSearchDirectoryWalker(".i[ljk]en[a-z]..\\.txt");
		assertTrue(directoryWalker.isFilenameMatch(file));
	}

}
