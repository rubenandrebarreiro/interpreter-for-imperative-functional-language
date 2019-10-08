import java.util.HashMap;
import java.util.Map;

public class Environment {

	private Environment ancestor;
	private Map<String, Integer> map;
	
	public Environment(Environment ancestor) {
		map = new HashMap<String, Integer>();
		this.ancestor = ancestor;
	}
	
	public int find(String id) {
		int value = map.get(id);
//		if(value == null) {
//			//Exception
//		}
//		else
			return value;
	}
	
	public void assoc(String id, int value)
	{
		if(map.containsKey(id)) {
			//Make exception here. Only one value per ID?
			//Can't overwrite?
		} else {
			map.put(id, value);
		}
	}
	
	public Environment beginScope() {
		return new Environment(this);
	};
	
	public Environment endScope() {
		return ancestor;
	};
	
}
