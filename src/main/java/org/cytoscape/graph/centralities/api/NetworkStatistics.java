/**
 * 
 */
package org.cytoscape.graph.centralities.api;

import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface NetworkStatistics {

	public NetworkStatsResults computeAll(CyNetwork network, boolean directed, WeightFunction function);
	
}
