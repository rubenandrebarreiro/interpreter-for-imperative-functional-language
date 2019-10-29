package abstractsyntaxtree.scopes.structures;

import java.util.List;

public class HeapFrame {

	private HeapFrame staticLinkAncestorHeapFrame;
	
	public HeapFrame(HeapFrame staticLinkAncestorHeapFrame, List<String> fieldLabels) {
		this.staticLinkAncestorHeapFrame = staticLinkAncestorHeapFrame;
		
	}
	
	public HeapFrame getStaticLinkAncestorHeapFrame() {
		return this.staticLinkAncestorHeapFrame;
	}
	
	public boolean isHeapFrameRoot() {
		return this.staticLinkAncestorHeapFrame == null ? true : false;
	}
	
	public int getLevelHeapFrameRoot() {
		int currentLevel = 0;
		HeapFrame currentHeapFrame = this;
		
		while(currentHeapFrame.getStaticLinkAncestorHeapFrame() != null) {
			
			currentHeapFrame = currentHeapFrame.getStaticLinkAncestorHeapFrame();
			
			currentLevel++;
		}
		
		return currentLevel;
	}
}
