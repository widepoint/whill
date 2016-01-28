package co.uk.widepoint.filesearch.validation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FileSearchValidatorTest
{
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private ParameterValidator validator;
	
	@Before
	public void setUp() {
		validator = new FileSearchValidator();
	}

	@Test
	public void test_invalid_file_name_flag() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("First parameter must be the filename flag -f");
		validator.validateParameters(new String[] {"-X","filename","directory"});
		
	}
	
	@Test
	public void test_blank_file_name() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Second parameter (filename) cannot be an empty string");
		validator.validateParameters(new String[] {"-f"," ","directory"});
		
	}
	
	@Test
	public void test_blank_start_directory() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Third parameter (start directory) cannot be an empty string");
		validator.validateParameters(new String[] {"-f","pom.xml"," "});
		
	}
	
	@Test
	public void test_invalid_start_directory() {
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("The directory you have supplied does not exist or is not a valid directory");
		validator.validateParameters(new String[] {"-f","pom.xml","awubble"});
		
	}
	
	@Test
	public void test_start_directory_is_file() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("The directory you have supplied does not exist or is not a valid directory");
		validator.validateParameters(new String[] {"-f","pom.xml","src/test/resources/invaliddirectory.test"});
		
	}
	
	@Test
	public void test_valid_parameters() {
		validator.validateParameters(new String[] {"-f","pom.xml","src/test/resources"});
	}

}
