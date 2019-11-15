package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.interpreter.parser.InterpreterParser;
import main.java.interpreter.parser.ParseException;
import main.java.values.exceptions.TypeErrorException;

@RunWith(Parameterized.class)
public class CompilerTester {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private String expected;
	private ByteArrayInputStream input;

	@Parameters
	public static Collection<Object[]> arithmetic() {
		return Arrays.asList(new Object[][] {     
			{ "2+2", "4" },
			{ "4-2", "2" },
			{ "-2+12", "10" },
			{ "5*5", "25" }, 
			{ "6/2", "3" },
			{ "-5*2", "-10" },
			{ "3*2/4", "0" },
			{ "(3*2)/4", "1" },
			{ "(20+5000)*0+3-1+3*(-5*(-1))", "17"}
		});
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

	public CompilerTester(String input, String expected) {
		this.input = new ByteArrayInputStream((input+"\n").getBytes());
		this.expected = expected;
	}

	@Test
	public void test1() throws ASTInvalidIdentifierException, TypeErrorException, ParseException, IOException {
		InterpreterParser.test(input);
		Runtime.getRuntime().exec("java -jar res/jasmin.jar");
		setUpStreams();
		Runtime.getRuntime().exec("java CompilerParser");
		assertEquals(expected, outContent.toString().trim());
		restoreStreams();
	}
}
