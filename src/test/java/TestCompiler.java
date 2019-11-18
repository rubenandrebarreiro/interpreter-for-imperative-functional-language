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
		return DataArguments.data();
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

		if(expected.equals("true") || expected.equals("false"))
			expected = translateBoolean(expected);
		assertEquals(expected, line);
	}
	
	public String translateBoolean(String bool) {
		
		if(bool.equals("true"))
			return "1";
		else return "0";
	}
	
}
