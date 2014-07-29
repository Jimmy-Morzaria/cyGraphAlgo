/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface KruskalMST {

	public KruskalMSTStats findTree(CyNetwork network, WeightFunction function);
	
}
