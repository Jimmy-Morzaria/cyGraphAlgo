/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface BellmanFordShortestPathFinder {

	/**
	 * @param network
	 * @param source
	 * @param directed
	 * @param function 
	 * @return
	 */
	public BellmanFordStats findPath(CyNetwork network, CyNode source, boolean directed,
			WeightFunction function);
}
