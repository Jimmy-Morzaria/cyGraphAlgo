/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface FloydWarshallShortestPathFinder {

	public FloydWarshallStats findPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function);
}
