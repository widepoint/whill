package co.uk.widepoint.filesearch.output;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ErrorWriterTest
{

	@Test
	public void test_error_message_formatting() throws UnsupportedEncodingException
	{

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ErrorWriter errorWiter = new ErrorWriter(os);
		Exception e = new IllegalArgumentException("Bad things have happened!");
		errorWiter.writeError("FIRST BIT", e);
		String output = new String(os.toByteArray());
		assertEquals("FIRST BIT : ERROR - Bad things have happened!\n", output);

	}
}
