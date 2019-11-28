package main.java.values.atomics;

/**
 * Class for the VCell, implementing IValue interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VCell implements IValue<Object> {

	/**
	 * 
	 */
	private IValue<?> value;
	
	/**
	 * 
	 * 
	 * @param value
	 */
	public VCell(IValue<?> value) {
		this.value = value;
	}
	
	public IValue<?> get() {
		return this.value;
	}
	
	public void set(IValue<?> value) {
		this.value = value;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}
}