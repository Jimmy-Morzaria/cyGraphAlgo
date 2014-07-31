/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 * 
 */
public interface FloydWarshallShortestPathFinder {

	public FloydWarshallStats findPath(CyNetwork network, boolean directed,
			WeightFunction function);
}
