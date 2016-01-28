package co.uk.widepoint.filesearch.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FileSearchDirectoryWalkerTest
{
	
	private FileSearchDirectoryWalker directoryWalker;
	private File file;
	
	@Before
	public void setUp() {
		directoryWalker = new FileSearchDirectoryWalker("filename.txt");
	}

	@Test
	public void test_matching_filename() {
		file = new File("filename.txt");
		assertTrue(directoryWalker.isFilenameMatch(file));	
	}
	
	@Test
	public void test_non_matching_filename() {
		file = new File("filenamextxt");
		assertFalse(directoryWalker.isFilenameMatch(file));	
	}
	
}
