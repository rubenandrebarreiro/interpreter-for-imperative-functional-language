package values.atomic;

public class VCell implements IValue {

	IValue value;
	
	public VCell(IValue value) {
		this.value = value;
	}
	
	public IValue get() {
		return this.value;
	}
	
	public void set(IValue value) {
		this.value = value;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
