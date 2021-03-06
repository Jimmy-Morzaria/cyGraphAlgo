/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface PrimMST {

	public PrimMSTStats findTree(CyNetwork network, WeightFunction function);
	
}
