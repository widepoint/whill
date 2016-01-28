package co.uk.widepoint.filesearch.validation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ContentSearchValidatorTest
{
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private ParameterValidator validator;
	
	@Before
	public void setUp() {
		validator = new ContentSearchValidator();
	}

	@Test
	public void test_invalid_file_name_flag() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("First parameter must be the filename flag -f");
		validator.validateParameters(new String[] {"-X","filename","-X","contentPattern", "directory"});
		
	}
	
	@Test
	public void test_blank_file_name() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Second parameter (filename) cannot be an empty string");
		validator.validateParameters(new String[] {"-f"," ","-X","contentPattern", "directory"});
		
	}
	
	@Test
	public void test_invalid_content_match_flag() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Third parameter must be the content match flag -p");
		validator.validateParameters(new String[] {"-f","pom.xml","-X","contentPattern", "directory"});
		
	}
	
	@Test
	public void test_blank_content_pattern() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Fourth parameter (content pattern) cannot be an empty string");
		validator.validateParameters(new String[] {"-f","pom.xml","-p"," ", "directory"});
		
	}
	
	@Test
	public void test_blank_start_directory() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Fifth parameter (start directory) cannot be an empty string");
		validator.validateParameters(new String[] {"-f","pom.xml","-p","contentPattern", " "});
		
	}
	
	@Test
	public void test_invalid_start_directory() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("The directory you have supplied does not exist or is not a valid directory");
		validator.validateParameters(new String[] {"-f","pom.xml","-p","contentPattern", "awwuble"});
		
	}
	
	@Test
	public void test_start_directory_is_file() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("The directory you have supplied does not exist or is not a valid directory");
		validator.validateParameters(new String[] {"-f","pom.xml","-p","contentPattern","src/test/resources/invaliddirectory.test"});
		
	}
	
	@Test
	public void test_valid_parameters() {
		validator.validateParameters(new String[] {"-f","pom.xml","-p","contentPattern","src/test/resources"});
	}

}
