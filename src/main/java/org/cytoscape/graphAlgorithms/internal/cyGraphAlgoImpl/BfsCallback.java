/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.Callback;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class BfsCallback implements Callback{
	
	private CyNode target;
	
	public BfsCallback(CyNode target){
		
		this.target = target;
		
	}

	@Override
	public boolean notify(CyNode node, int time, int dist) {
		// TODO Auto-generated method stub
		return node == target;
	}

}
