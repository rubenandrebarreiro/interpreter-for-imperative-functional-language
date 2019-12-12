package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.interpreter.parser.*;
import main.java.values.utils.exceptions.TypeErrorException; 

@RunWith(Parameterized.class)
public class TestInterpreter {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private String expected;
	private ByteArrayInputStream input;

	@Parameters
	public static Collection<Object[]> data() {
		return DataArguments.data();
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	public TestInterpreter(String input, String expected) {
		this.input = new ByteArrayInputStream( (input + "\n").getBytes() );
		this.expected = expected;
	}

	@Test
	public void interpreter() throws ASTInvalidIdentifierException, TypeErrorException, ParseException {
		InterpreterParser.testInterpreter(input);
		assertEquals(expected, outContent.toString().trim());
	}
}
