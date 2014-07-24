/**
 * 
 */
package org.cytoscape.graph.api.centralities;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface ClusteringCoefficient {
	
	public double getClusteringCoefficient(CyNetwork network, CyNode node, boolean directed);
	
}
