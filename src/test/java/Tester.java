package test.java;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.interpreter.parser.*; 

public class Tester {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

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
	
	public void testSkel(String file, String solution) {
		String[] args = null;
		final InputStream original = System.in;
		FileInputStream fips = null;
		try {
			fips = new FileInputStream(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setIn(fips);
		InterpreterParser.mainTest(args);
		setUpStreams();
		assertEquals(solution, outContent.toString().trim());
		restoreStreams();
	}
	
	@Test
	public void sumTests() {
		for (int i = 0; i < 1; i++) {
			testSkel("res/tests/Basic Arithmetic/test1.txt", "2");
		}
	}
}
