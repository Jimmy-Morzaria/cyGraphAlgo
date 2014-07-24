/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import org.cytoscape.graph.algorithms.cyGraphAlgo.Callback;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class BfsCallback implements Callback {

	private CyNode target;

	public BfsCallback(CyNode target) {

		this.target = target;

	}

	@Override
	public boolean notify(CyNode node, int time, int dist) {
		// TODO Auto-generated method stub
		return node == target;
	}

}
