/**
 * 
 */
package org.cytoscape.graph.api.centralities;

import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface NetworkStatistics {

	public NetworkStatsResults computeAll(CyNetwork network, boolean directed, WeightFunction function);
	
}
