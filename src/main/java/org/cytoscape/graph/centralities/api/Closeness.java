/**
 * 
 */
package org.cytoscape.graph.centralities.api;

import org.cytoscape.graph.algorithms.api.DijkstraStats;
import org.cytoscape.graph.algorithms.api.WeightFunction;
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
