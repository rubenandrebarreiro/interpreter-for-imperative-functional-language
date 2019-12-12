package main.java.values.lists;

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
