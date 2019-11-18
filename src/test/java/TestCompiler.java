package test.java;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.interpreter.parser.*;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.values.exceptions.TypeErrorException;

@RunWith(Parameterized.class)
public class TestCompiler {
	
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
			{ "3>4", "0"}, // #18
			{ "10>8", "1"}, // #19
			{ "10>10", "0"}, // #20
			{ "10>=10", "1"}, // #21
			{ "10<10", "0"}, // #22
			{ "10<=10", "1"}, // #23
			{ "4>3", "1"} // #24 
		});
	}

	//@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	//@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	public TestCompiler(String input, String expected) {
		this.input = new ByteArrayInputStream( (input + "\n").getBytes() );
		this.expected = expected;
	}

	private String getFiles(){
		
		StringBuilder result = new StringBuilder();
		File folder = new File("./");
		
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".j");
			}
		});
		
		for (File file : listOfFiles) {
			result.append((file.getPath() + " "));
		}
		
		return result.toString();
	}
	
	@Test
	public void compiler() throws ASTInvalidIdentifierException, TypeErrorException, ParseException, IOException, InterruptedException {
		
		InterpreterParser.testCompiler(input);
		
		Process jasminProc = Runtime.getRuntime().exec("java -jar ./res/jasmin.jar " + getFiles());
		jasminProc.waitFor();
		
		Process javaProc = Runtime.getRuntime().exec("java CompilerParser");
		javaProc.waitFor();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(javaProc.getInputStream()));
		String line = in.readLine();
		
		javaProc.waitFor();

		assertEquals(expected, line);
	}
}
