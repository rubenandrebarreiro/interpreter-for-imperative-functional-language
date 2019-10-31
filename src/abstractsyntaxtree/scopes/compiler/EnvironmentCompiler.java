package abstractsyntaxtree.scopes.compiler;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentCompiler {
	private Map<String, Integer> offsetLocalizations;
	
	public EnvironmentCompiler() {
		this.offsetLocalizations = new HashMap<String, Integer>();
	}
	
	
}
