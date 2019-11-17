package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import main.java.interpreter.parser.*;
import main.java.values.exceptions.TypeErrorException; 

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
		
		return Arrays.asList(new Object[][] {     
			
			// Arithmetic operations
			{ "2+2", "4" }, // #0
			{ "4-2", "2" }, // #1
			{ "-2+12", "10" }, // #2
			{ "5*5", "25" }, // #3
			{ "6/2", "3" }, // #4
			{ "-5*2", "-10" }, // #5
			{ "3*2/4", "0" }, // #6
			{ "(3*2)/4", "1" }, // #7
			{ "(20+5000)*0+3-1+3*(-5*(-1))", "17"}, // #8
			{ "(20+5000)*0+((3-1+3*(-5*(-1))))", "17"}, // #9
			
			// Let operations
			{ "Let x=2 in x end", "2"}, // #10
			{ "Let x=2 in 5 end", "5"}, // #11
			{ "Let x=2 y=5 in x*y end", "10"}, // #12
			{ "Let x=10 y=5 z=x+y in z*((-2)+60) end", "870"}, // #13
			{ "Let x=5 in Let y=5 in x*y end end", "25" }, // #14
			{ "Let x=5 in Let y=5 in Let x=2 in y-x end end end", "3" }, // #15
			{ "Let x=2 y=3 in Let z=x+y in Let q=10 x=10 e=q+x in x+e-(z*2) end end end", "20" },  // #16 //10+20-(5*2)
			{ "Let x=2 y=3 in Let x=20 in x+y end +x end", "25" }, // #17
			
			// Relational operations
			{ "3>4", "false"}, // #18
			{ "10>8", "true"}, // #19
			{ "10>10", "false"}, // #20
			{ "10>=10", "true"}, // #21
			{ "10<10", "false"}, // #22
			{ "10<=10", "true"}, // #23
			{ "4>3", "true"} // #24
			
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
