package main.java.values.lists;

/**
 * Interpreter for Imperative/Functional Language
 * 
 * Interpretation and Compilation of Programming Languages
 * 
 * Faculty of Science and Technology of New University of Lisbon
 * (FCT NOVA | FCT/UNL)
 * 
 * Integrated Master of Computer Science and Engineering
 * (BSc. + MSc. Bologna Degree)
 * 
 * Academic Year 2019/2020
 * 
 */

import java.util.List;

import main.java.values.atomics.IValue;

public class VList implements IValue {
	
	private List<IValue> listValues;
	
	
	public VList(List<IValue> listValues) {
		
		this.listValues = listValues;
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public List<IValue> getListValues() {
		return this.listValues;
	}

}
