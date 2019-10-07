package values.atomic;

public class VInt implements IValue {

	private int value;
	
	public VInt(int value) {
		this.value = value;
	}
	
	int getValue() {
		return this.value;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}