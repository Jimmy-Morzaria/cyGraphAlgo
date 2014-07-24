/**
 * 
 */
package org.cytoscape.graph.api.centralities;

import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraStats;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface Closeness {

	public double getCloseness(CyNetwork network, CyNode node, boolean directed, WeightFunction function);
	
	public double getCloseness(CyNetwork network, DijkstraStats dStats);
}
