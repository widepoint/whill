package co.uk.widepoint.filesearch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * Test the main entry class into the directory search utility 
 * 
 * @author David Harvey
 * 
 * Copyright Widepoint Computer Services Ltd 2016
 *
 */
public class FileSearcherTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private FileSearcher fileSearcher;
	
	@Before
	public void setUp() {
		fileSearcher = new FileSearcher();
	}
	
	/*
	 * Test the basic number of parameters being passed. More detailed validation tests are in the test classes for the specific parameter validators. 
	 */
	
	@Test
	public void test_with_no_parameters() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid number of parameters");
		fileSearcher.runSearch(null);	
	}
	
	@Test
	public void test_with_invalid_parameters_1() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid number of parameters");
		fileSearcher.runSearch(new String [] {"-f"});	
	}
	
	@Test
	public void test_with_invalid_parameters_2() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid number of parameters");
		fileSearcher.runSearch(new String [] {"-f", "\"pom.xml\""});	
	}
	
	@Test
	public void test_file_name_search() {
		fileSearcher.runSearch(new String [] {"-f", ".*java", "."});	
	}
	
	
	@Test
	public void test_content_match_search() {
		fileSearcher.runSearch(new String [] {"-f", ".*java", "-p", "4.12", "."});	
	}

}
